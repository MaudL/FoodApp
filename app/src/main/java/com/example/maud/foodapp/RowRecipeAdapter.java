package com.example.maud.foodapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maud.foodapp.model.RecipeDto;

import java.util.List;

/**
 */
public class RowRecipeAdapter extends ArrayAdapter<RecipeDto> {


    public RowRecipeAdapter(Context context, List<RecipeDto> infos) {
        super(context, 0, infos);
    }

    /**
     * Change the view of a row_sensor to fit his content
     *
     * @param position row's position in the listView
     * @param convertView View used as a template (row_sensor) for each row of the listView
     * @param parent ViewGroup which contains the convertView
     * @return Return a row_sensor view which as been dynamically modified to fit the information to
     * display
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_recipe,parent, false);
        }

        /// get each widgets from the row_sensor template
        SensorViewHolder viewHolder = (SensorViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new SensorViewHolder();
            viewHolder.title = convertView.findViewById(R.id.name_recipe);
            viewHolder.note = convertView.findViewById(R.id.note);
            viewHolder.photo = convertView.findViewById(R.id.img_recipe);

            convertView.setTag(viewHolder);
        }

        RecipeDto recipeInfo = getItem(position);

        if (recipeInfo != null) {
            /// Fill the viewHolder with the information in sensorInfo

            viewHolder.title.setText(recipeInfo.getTitle());
            viewHolder.note.setText(recipeInfo.getMark());
            //viewHolder.photo.setImageDrawable(recipeInfo.getPhoto().getD);
            viewHolder.photo.setImageBitmap(recipeInfo.getPhoto());
        }
        return convertView;
    }

    /**
     *      Contains all the widgets that we want to interact with
     */
    private class SensorViewHolder{
        public TextView title;
        public TextView note;
        public ImageView photo;
    }
}