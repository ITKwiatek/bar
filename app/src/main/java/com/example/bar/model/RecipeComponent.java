package com.example.bar.model;

public class RecipeComponent {
    private int recipe_component_id, recipe_id, component_id;

    public RecipeComponent(int recipe_component_id, int recipe_id, int component_id) {
        this.recipe_component_id = recipe_component_id;
        this.recipe_id = recipe_id;
        this.component_id = component_id;
    }

    public int getRecipe_component_id() {
        return recipe_component_id;
    }

    public void setRecipe_component_id(int recipe_component_id) {
        this.recipe_component_id = recipe_component_id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getComponent_id() {
        return component_id;
    }

    public void setComponent_id(int component_id) {
        this.component_id = component_id;
    }
}
