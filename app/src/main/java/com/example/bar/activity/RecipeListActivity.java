package com.example.bar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bar.R;
import com.example.bar.databinding.ActivityComponentListBinding;
import com.example.bar.databinding.ActivityRecipeListBinding;
import com.example.bar.model.Component;
import com.example.bar.model.Recipe;
import com.example.bar.utility.ComponentListAdapter;
import com.example.bar.utility.QueryHelper;
import com.example.bar.utility.RecipeListAdapter;

import java.util.ArrayList;

public class RecipeListActivity extends AppCompatActivity {
    ActivityRecipeListBinding binding;
    ArrayList<Recipe> recipeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        GetData();
        AdaptModelToView();
    }

    private void AdaptModelToView() {
//        ComponentListAdapter componentListAdapter = new ComponentListAdapter(ComponentListActivity.this, componentList);
        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(RecipeListActivity.this, recipeArrayList);
        binding.lvComponentList.setAdapter(recipeListAdapter);
    }

    private void GetData() {
        try {
            QueryHelper queryHelper = new QueryHelper();
            recipeArrayList = queryHelper.getRecipeList();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}