package com.example.bar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bar.R;

public class MainActivity extends AppCompatActivity {

    Button button_add_component;
    Button button_component_list;
    Button button_add_recipe;
    Button button_recipe_list;
    Button button_recipe_based_on_components;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_add_component = findViewById(R.id.button_add_component);
        button_add_component.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityAddComponents();
            }
        });
        button_component_list = findViewById(R.id.button_component_list);
        button_component_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityComponentList();
            }
        });
        button_add_recipe = findViewById(R.id.button_add_recipe);
        button_add_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityAddRecipe();
            }
        });
        button_recipe_list = findViewById(R.id.button_recipe_list);
        button_recipe_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRecipeList();
            }
        });
        button_recipe_based_on_components = findViewById(R.id.button_filter_drink);
        button_recipe_based_on_components.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRecipeBasedOnComponents();
            }
        });
    }

    private void openActivityRecipeBasedOnComponents() {
        Intent intent = new Intent(this, RecipeBasedOnComponentsActivity.class);
        startActivity(intent);
    }

    private void openActivityRecipeList() {
        Intent intent = new Intent(this, RecipeListActivity.class);
        startActivity(intent);
    }

    private void openActivityComponentList() {
        Intent intent = new Intent(this, ComponentListActivity.class);
        startActivity(intent);
    }

    private void openActivityAddComponents() {
        Intent intent = new Intent(this, AddComponent.class);
        startActivity(intent);
    }

    private void openActivityAddRecipe() {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivity(intent);
    }


}