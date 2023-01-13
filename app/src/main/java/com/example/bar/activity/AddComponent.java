package com.example.bar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bar.R;
import com.example.bar.model.Component;
import com.example.bar.model.ComponentTypes;
import com.example.bar.utility.QueryHelper;
import com.google.android.material.snackbar.Snackbar;

public class AddComponent extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd;
    TextView tvName;
    TextView tvDescription;
    Spinner spType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_component);
        prepareView();
    }

    private void prepareView() {
        btnAdd = findViewById(R.id.btn_add_component);
        tvName = findViewById(R.id.component_name);
        tvDescription = findViewById(R.id.component_description);
        spType = findViewById(R.id.component_type);
        btnAdd.setOnClickListener(this);
        prepareSpinner();
    }

    @Override
    public void onClick(View view) {
        String name = (String) tvName.getText().toString();
        String description = (String) tvDescription.getText().toString();
        String type = (String) spType.getSelectedItem().toString();
        Component component = new Component(name, description, type);

        boolean added = false;

        QueryHelper helper = new QueryHelper();
        try {
            added = helper.AddComponent(component);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(added) {
            tvName.setText("");
            tvDescription.setText("");
            Snackbar.make(view, "Dodano", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void prepareSpinner() {
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.component_type);
        //create a list of items for the spinner.
        ComponentTypes componentTypes = new ComponentTypes();
        String[] items = componentTypes.getAllComponentTypes().toArray(new String[0]);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }


}