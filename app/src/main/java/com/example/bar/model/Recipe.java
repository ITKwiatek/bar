package com.example.bar.model;

public class Recipe {
    private int recipe_id;
    private String name, description;

    public Recipe(int recipe_id, String name, String description) {
        this.recipe_id = recipe_id;
        this.name = name;
        this.description = description;
    }

    public Recipe(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
