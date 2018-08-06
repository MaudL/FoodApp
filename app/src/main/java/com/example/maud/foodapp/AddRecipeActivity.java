package com.example.maud.foodapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maud.foodapp.db.DatabaseHelper;
import com.example.maud.foodapp.db.RecipeData;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.Calendar;

public class AddRecipeActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper = null;
    private EditText add_ingredient;
    private EditText add_stape;
    private TextView my_recipe;
    private Button btn_add_ingredient;
    private Button btn_add_stape;
    private Button btn_save_recipe;
    private Button btn_add_photo;
    private ImageView photo_recipe;
    int i = 0;

    public final static int GALLERY = 1;
    public final static int CAMERA = 0;
    public final static String IMAGE_DIRECTORY = "";

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        LinearLayout linearLayoutVertIng = findViewById(R.id.layout_vert_ing);
        LinearLayout linearLayoutVertStape = findViewById(R.id.layout_vert_stape);

        initComponents();

        final String nameRecipeExtra = getIntent().getStringExtra("nameRecipe") ;
        my_recipe.setText(nameRecipeExtra);


        clickAddIngredient(linearLayoutVertIng);
        clickAddStape(linearLayoutVertStape);
        clickAddPhoto();
        dbHelper = new DatabaseHelper(this, "food_database.db", null, 1);

        clickAddSave(nameRecipeExtra);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)
                    this, Manifest.permission.CAMERA)) {


            } else {
                ActivityCompat.requestPermissions((Activity) this,
                        new String[]{Manifest.permission.CAMERA},
                        200);
            }

        }
    }

    private void initComponents(){
        my_recipe = findViewById(R.id.display_title);
        add_ingredient = findViewById(R.id.add_ingredient);
        add_stape = findViewById(R.id.add_stape);
        btn_add_ingredient = findViewById(R.id.btn_add_ingredient);
        btn_add_stape = findViewById(R.id.btn_add_stape);
        btn_save_recipe = findViewById(R.id.btn_save);
        btn_add_photo = findViewById(R.id.btn_add_photo);
        photo_recipe = findViewById(R.id.recipe_photo);
    }

    private void clickAddIngredient(final LinearLayout linearLayoutVert){
        btn_add_ingredient.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                String ingredient = add_ingredient.getText().toString();
                add_ingredient.setText(""); // Vide l'editText
                TextView textView = new TextView(getApplicationContext());
                textView.setText("-" + ingredient + "\r\n");
                textView.setTextColor(R.color.colorPrimaryDark);

                final LinearLayout linearLayoutHor = new LinearLayout(getApplicationContext());
                linearLayoutHor.addView(textView);
                Button button = new Button(getApplicationContext());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        linearLayoutVert.removeView(linearLayoutHor);
                    }
                });
                linearLayoutHor.addView(button);
                linearLayoutVert.addView(linearLayoutHor);
            }
        });
    }

    private void clickAddStape(final LinearLayout linearLayoutVert) {
        btn_add_stape.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                i += 1;
                String ingredient = add_stape.getText().toString();
                TextView textView = new TextView(getApplicationContext());
                textView.setText("Etape" + i + "\r\n" + ingredient + "\r\n");
                textView.setTextColor(R.color.colorPrimaryDark);
                final LinearLayout linearLayoutHor = new LinearLayout(getApplicationContext());
                linearLayoutHor.addView(textView);
                Button button = new Button(getApplicationContext());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        linearLayoutVert.removeView(linearLayoutHor);
                    }
                });
                linearLayoutHor.addView(button);
                linearLayoutVert.addView(linearLayoutHor);

                add_stape.setText("");
            }
        });
    }

    private void clickAddPhoto() {
        btn_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showPictureDialog();
            }
        });
    }

    public void choosePhotoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    photo_recipe.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddRecipeActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            photo_recipe.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(AddRecipeActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this, new String[]{f.getPath()}, new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void clickAddSave(final String nameRecipe){


        btn_save_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final byte[] photo_blob = serializeObject(photo_recipe);
                RecipeData recipeData = new RecipeData(nameRecipe, add_ingredient.getText().toString(), add_stape.getText().toString(), null);

                try {
                    dbHelper.createOrUpdate(recipeData);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Intent myIntent = new Intent(AddRecipeActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }


    /**
     * Serialize an object
     *
     * @param object Object to serialize
     * @return A byte array
     */
    protected byte[] serializeObject(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutput output = new ObjectOutputStream(byteArrayOutputStream);
            output.writeObject(object);
            output.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            Log.e("Serialization", "Error", e);
            return null;
        }
    }

    /**
     * Deserialize a byte array
     *
     * @param b The byte array to deserialize
     * @return The deserialized object
     */
    protected Object deserializeObject(byte[] b) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(b));
            Object object = inputStream.readObject();
            inputStream.close();
            return object;
        } catch (ClassNotFoundException e) {
            Log.e("Deserialization", "Error", e);
            return null;
        } catch (IOException e) {
            Log.e("Deserialization", "Error io", e);
            return null;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }
}