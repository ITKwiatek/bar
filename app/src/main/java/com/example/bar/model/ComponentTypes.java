package com.example.bar.model;

import java.util.ArrayList;
import java.util.Arrays;

public class ComponentTypes {
    public final static String alcohol = "ALCOHOL";
    public final static String noAlcohol = "NO_ALCOHOL";
    public final static String fruit = "FRUIT";
    public ArrayList<String> getAllComponentTypes() {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(alcohol, noAlcohol, fruit));
        return arrayList;
    }
}
