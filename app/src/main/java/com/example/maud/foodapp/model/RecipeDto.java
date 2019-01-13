package com.example.maud.foodapp.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.Serializable;

public class RecipeDto implements Serializable{

    private String title;
    private Bitmap photo;
    private String mark;
    private String ingredient;
    private String stape;



    public RecipeDto(String title, Bitmap photo, String mark, String ingredient, String stape) {
        this.title = title;
        this.photo = photo;
        this.mark = mark;
        this.ingredient = ingredient;
        this.stape = stape;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getStape() {
        return stape;
    }

    public void setStape(String etape) {
        this.stape = stape;
    }

}