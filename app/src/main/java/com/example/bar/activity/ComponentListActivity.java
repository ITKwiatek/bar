package com.example.bar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bar.databinding.ActivityComponentListBinding;
import com.example.bar.model.Component;
import com.example.bar.utility.ComponentListAdapter;
import com.example.bar.utility.QueryHelper;

import java.util.ArrayList;

public class ComponentListActivity extends AppCompatActivity {
    ActivityComponentListBinding binding;
    ArrayList<Component> componentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComponentListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        GetData();
        AdaptModelToView();
    }

    private void AdaptModelToView() {
        ComponentListAdapter componentListAdapter = new ComponentListAdapter(ComponentListActivity.this, componentList);
        binding.lvComponentList.setAdapter(componentListAdapter);
    }

    private void GetData() {
        try {
            QueryHelper queryHelper = new QueryHelper();
            componentList = queryHelper.getComponentList();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}