package com.example.bar.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bar.R;
import com.example.bar.model.Recipe;
import com.example.bar.utility.QueryHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

public class AddRecipeActivity extends AppCompatActivity {

    Button addRecipe;
    TextView tvName;
    TextView tvDescription;
    TextView tvSelectedComponents;
    boolean[] selectedLanguage;
    ArrayList<Integer> selectedComponentsInteger = new ArrayList<>();
    String[] availableComponentArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        QueryHelper helper = new QueryHelper();
        try {
            availableComponentArray = helper.getComponentNames();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // assign variable
        tvSelectedComponents = findViewById(R.id.selected_components);

        // initialize selected language array
        selectedLanguage = new boolean[availableComponentArray.length];

        tvSelectedComponents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(AddRecipeActivity.this);

                // set title
                builder.setTitle("Wybierz potrzebne produkty");

                // set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(availableComponentArray, selectedLanguage, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        // check condition
                        if (b) {
                            // when checkbox selected
                            // Add position  in lang list
                            selectedComponentsInteger.add(i);
                            // Sort array list
                            Collections.sort(selectedComponentsInteger);
                        } else {
                            // when checkbox unselected
                            // Remove position from langList
                            selectedComponentsInteger.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        // use for loop
                        for (int j = 0; j < selectedComponentsInteger.size(); j++) {
                            // concat array value
                            stringBuilder.append(availableComponentArray[selectedComponentsInteger.get(j)]);
                            // check condition
                            if (j != selectedComponentsInteger.size() - 1) {
                                // When j value  not equal
                                // to lang list size - 1
                                // add comma
                                stringBuilder.append(", ");
                            }
                        }
                        // set text on textView
                        tvSelectedComponents.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clearScreen();
                    }
                });
                builder.show();
            }
        });

        tvName = findViewById(R.id.recipe_name);
        tvDescription = findViewById(R.id.recipe_description);
        tvSelectedComponents = findViewById(R.id.selected_components);
        addRecipe = findViewById(R.id.btn_add_recipe);
        addRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tvName.getText().toString();
                String description = tvDescription.getText().toString();
                Recipe recipe = new Recipe(name, description);
                QueryHelper helper = new QueryHelper();
                try {
                    recipe.setRecipe_id(helper.AddRecipe(recipe));
                    boolean added = helper.AddRecipeComponent(tvSelectedComponents.getText().toString().split(","), recipe.getRecipe_id());
                    if(added) {
                        tvName.setText("");
                        tvDescription.setText("");
                        clearScreen();
                        Snackbar.make(view, "Dodano", Snackbar.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void clearScreen() {
        // use for loop
        for (int j = 0; j < selectedLanguage.length; j++) {
            // remove all selection
            selectedLanguage[j] = false;
            // clear language list
            selectedComponentsInteger.clear();
            // clear text view value
            tvSelectedComponents.setText("");
        }
    }
}