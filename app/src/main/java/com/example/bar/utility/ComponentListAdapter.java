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
import com.example.bar.model.Component;

import java.util.ArrayList;

public class ComponentListAdapter extends ArrayAdapter<Component> {

    public ComponentListAdapter(@NonNull Context context, ArrayList<Component> componentArrayList) {
        super(context, R.layout.component_list_item, componentArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Component component = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.component_list_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.component_image);
        TextView name = convertView.findViewById(R.id.component_name);
        TextView description = convertView.findViewById(R.id.component_description);

        name.setText(component.getName());
        description.setText(component.getDescription());

        ComponentImageHelper imageHelper = new ComponentImageHelper();
        imageView.setImageResource(imageHelper.getResourceIdByNumber(component.getType()));

        return convertView;
    }
}
