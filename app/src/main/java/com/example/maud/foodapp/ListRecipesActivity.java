package com.example.maud.foodapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import  com.example.maud.foodapp.db.DatabaseHelper;
import  com.example.maud.foodapp.db.RecipeData;
import  com.example.maud.foodapp.model.RecipeDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 */

public class ListRecipesActivity extends Activity {

    private DatabaseHelper dbHelper = null;

    private List<RecipeDto> recipeItemList = null;

    private List<RecipeData> recipeDataList = null;

    /**
     * @param pSavedInstanceState standard parameter for the onCreate method
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_list_recipes);

        dbHelper = new DatabaseHelper(this, "food_database.db", null, 1);
        try {
            recipeDataList = dbHelper.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        recipeItemList = getRecipeInfos(recipeDataList);


    /*    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecipeData recipeData = recipeDataList.get(position);
                RecipeDto recipeDto = new RecipeDto(recipeData.title, null, "1", recipeData.ingredient, recipeData.stape);
                Intent myIntent = new Intent(ListRecipesActivity.this, RecipeDetailsActivity.class);
                myIntent.putExtra("recipe", (Serializable) recipeDto);

                startActivity(myIntent);
            }
        });*/

        // Create the recyclerview.
        RecyclerView carRecyclerView = (RecyclerView)findViewById(R.id.card_view_recycler_list);
        // Create the grid layout manager with 2 columns.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // Set layout manager.
        carRecyclerView.setLayoutManager(gridLayoutManager);

        // Create car recycler view data adapter with car item list.
        RecipeRecyclerViewDataAdapter carDataAdapter = new RecipeRecyclerViewDataAdapter(this, recipeItemList);
        // Set data adapter.
        carRecyclerView.setAdapter(carDataAdapter);

    }





    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * Fill the List sensorInfos with default value
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private List<RecipeDto> getRecipeInfos(List<RecipeData> recipeDataList) {
        List<RecipeDto> recipeInfos = new ArrayList<>();
        for(RecipeData recipeData : recipeDataList){
            //Bitmap picture = byteArrayToImage(recipeData.getPicture());
            recipeInfos.add(new RecipeDto(recipeData.title, null, "1", recipeData.ingredient, recipeData.stape));
        }
        return recipeInfos;
    }

    private Bitmap byteArrayToImage(byte[] byte_image){
        Bitmap bitmap = BitmapFactory.decodeByteArray(byte_image, 0, byte_image.length);
        return bitmap;
        //photo_recipe.setImageBitmap(Bitmap.createScaledBitmap(bitmap, photo_recipe.getWidth(), photo_recipe.getHeight(), false));
    }

    /**
     * Start to listen for new data from each sensor
     */
    @Override
    protected void onResume() {
        super.onResume();
    }


    /**
     * Stop listening for new data from each sensor
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}