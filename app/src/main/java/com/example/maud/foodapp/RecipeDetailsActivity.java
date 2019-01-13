package com.example.maud.foodapp;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import  com.example.maud.foodapp.model.RecipeDto;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {

    private TextView detail_title;
    private TextView detail_ingredient;
    private TextView detail_stape;

    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        //RecipeDto recipeDto = (RecipeDto) getIntent().getSerializableExtra("recipe");

        detail_title = findViewById(R.id.detail_title);
        detail_ingredient = findViewById(R.id.all_ingredients);
        detail_stape = findViewById(R.id.all_stapes);

        /*detail_title.setText(recipeDto.getTitle());
        detail_ingredient.setText(recipeDto.getIngredient());
        detail_stape.setText(recipeDto.getStape());*/
    }
}