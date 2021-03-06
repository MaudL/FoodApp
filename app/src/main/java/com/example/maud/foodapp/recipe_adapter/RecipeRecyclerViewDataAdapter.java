package com.example.maud.foodapp.recipe_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maud.foodapp.R;
import com.example.maud.foodapp.activities.RecipeDetailsActivity;
import com.example.maud.foodapp.model.RecipeDto;

import java.io.Serializable;
import java.util.List;


/**
 *
 * Created by Maud on 13/01/2019.
 */
public class RecipeRecyclerViewDataAdapter extends RecyclerView.Adapter<RecipeRecyclerViewItemHolder> {

    private List<RecipeDto> recipeItemList;
    private Context mContext;

    public RecipeRecyclerViewDataAdapter(Context context, List<RecipeDto> recipeItemList) {
        this.recipeItemList = recipeItemList;
        this.mContext = context;
    }
    private ImageView test;
    @Override
    public RecipeRecyclerViewItemHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        // Get LayoutInflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the RecyclerView item layout xml.
        final View recipeItemView = layoutInflater.inflate(R.layout.activity_card_view_item, parent, false);

        final TextView recipeTitleView = recipeItemView.findViewById(R.id.card_view_image_title);
       // final ImageView recipeImageView = recipeItemView.findViewById(R.id.card_view_image);
        test = recipeItemView.findViewById(R.id.card_view_image);
   /*     recipeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // RecipeDto r = recipeItemList.get(v.);

                Intent myIntent = new Intent(mContext, RecipeDetailsActivity.class);
                mContext.startActivity(myIntent);
            }
        });*/

        // Create and return our custom recipe Recycler View Item Holder object.
        RecipeRecyclerViewItemHolder ret = new RecipeRecyclerViewItemHolder(recipeItemView);
        return ret;
    }
    @Override
    public void onBindViewHolder(RecipeRecyclerViewItemHolder holder, final int position) {
        if (recipeItemList != null) {
            RecipeDto recipeItem = recipeItemList.get(position);

            if (recipeItem != null) {
                holder.getRecipeTitleText().setText(recipeItem.getTitle());
                // holder.getRecipeImageView().setImageResource(recipeItem.getRecipeImageId());
            }
        }
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecipeDto recipeDto = recipeItemList.get(position);

                Intent myIntent = new Intent(mContext, RecipeDetailsActivity.class);
                myIntent.putExtra("recipe", (Serializable) recipeDto);
                mContext.startActivity(myIntent);
            }
        });
    }
    @Override
    public int getItemCount() {
        int ret = 0;
        if(recipeItemList!=null){
            ret = recipeItemList.size();
        }
        return ret;
    }
}
