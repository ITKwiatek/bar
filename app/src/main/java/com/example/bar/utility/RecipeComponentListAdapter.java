package com.example.bar.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bar.R;
import com.example.bar.model.Recipe;
import com.example.bar.model.RecipeComponent;

import java.util.ArrayList;

public class RecipeComponentListAdapter extends ArrayAdapter<Recipe> {
    public RecipeComponentListAdapter(@NonNull Context context, ArrayList<Recipe> recipe) {
        super(context, R.layout.component_list_item, recipe);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Recipe recipe = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_list_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.recipe_image);
        TextView name = convertView.findViewById(R.id.recipe_name);
        TextView description = convertView.findViewById(R.id.recipe_description);

        name.setText(recipe.getName());
        description.setText(recipe.getDescription());
//
//        RecipeImageHelper imageHelper = new RecipeImageHelper();
//        imageView.setImageResource(imageHelper.getResourceIdByNumber(recipe.getType()));

        return convertView;
    }
}
