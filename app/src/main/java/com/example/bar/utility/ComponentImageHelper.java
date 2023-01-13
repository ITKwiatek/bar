package com.example.bar.utility;

import com.example.bar.R;
import com.example.bar.model.ComponentTypes;

public class ComponentImageHelper implements ImageHelper {
    public int getResourceIdByNumber(String type) {
        int resId;

        switch (type){
            case ComponentTypes.fruit:
                resId = R.drawable.fruit;
                break;
            case ComponentTypes.noAlcohol:
                resId = R.drawable.cola;
                break;
            case ComponentTypes.alcohol:
                resId = R.drawable.alcohol;
                break;
            default:
                resId = R.drawable.question;
                break;
        }
        return resId;
    }
}
