package com.example.maud.foodapp.db;

import android.widget.ImageView;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Blob;
import java.util.UUID;

@DatabaseTable(tableName = "recipe_data")
public class RecipeData {

    /**
     * Title field
     */
    @DatabaseField(columnName = "title")
    public String title;

    /**
     * Ingredient field
     */
    @DatabaseField(columnName = "ingredient")
    public String ingredient;

    /**
     * Stape field
     */
    @DatabaseField(columnName = "stape")
    public String stape;

    /**
     * Stape field
     */
    @DatabaseField(columnName = "picture", dataType = DataType.SERIALIZABLE)
    public byte[] picture;

    /**
     * Id field
     */
    @DatabaseField(generatedId = true)
    private long _id;

    public RecipeData() {}


    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public RecipeData(String title, String ingredient, String stape, byte[] picture){
        this.title = title;
        this.ingredient = ingredient;
        this.stape = stape;
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public long get_id() {
        return _id;
    }

    public String getStape() {
        return stape;
    }

    public void setStape(String stape) {
        this.stape = stape;
    }


}