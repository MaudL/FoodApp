package com.example.maud.foodapp.recipe_adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maud.foodapp.R;

/**
 *
 * Created by Maud on 13/01/2019.
 */
public class RecipeRecyclerViewItemHolder extends RecyclerView.ViewHolder {

    private TextView recipeTitleText = null;

    private ImageView recipeImageView = null;

    public RecipeRecyclerViewItemHolder(View itemView) {
        super(itemView);

        if(itemView != null)
        {
            recipeTitleText = (TextView)itemView.findViewById(R.id.card_view_image_title);

            recipeImageView = (ImageView)itemView.findViewById(R.id.card_view_image);
        }
    }

    public TextView getRecipeTitleText() {
        return recipeTitleText;
    }

    public ImageView getRecipeImageView() {
        return recipeImageView;
    }
}
