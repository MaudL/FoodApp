package com.example.maud.foodapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import  com.example.maud.foodapp.db.DatabaseHelper;
import  com.example.maud.foodapp.db.RecipeData;
import  com.example.maud.foodapp.model.RecipeDto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 */

public class ListRecipesActivity extends Activity {

    private DatabaseHelper dbHelper = null;

    private List<RecipeData> recipeDataList = null;

    /**
     * This activity load the view from the "activity_list_recipes" xml file and load the sensors
     * information to display.
     *
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
        final ListView listView = findViewById(R.id.list_all_recipes);
        List<RecipeDto> recipeInfos = getRecipeInfos(recipeDataList);

        RowRecipeAdapter adapter = new RowRecipeAdapter(ListRecipesActivity.this, recipeInfos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecipeData recipeData = recipeDataList.get(position);
                RecipeDto recipeDto = new RecipeDto(recipeData.title, null, "1", recipeData.ingredient, recipeData.stape);
                Intent myIntent = new Intent(ListRecipesActivity.this, RecipeDetailsActivity.class);
                myIntent.putExtra("recipe", (Serializable) recipeDto);

                startActivity(myIntent);
            }
        });

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
            recipeInfos.add(new RecipeDto(recipeData.title, null, "1", recipeData.ingredient, recipeData.stape));
        }
        return recipeInfos;
    }

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