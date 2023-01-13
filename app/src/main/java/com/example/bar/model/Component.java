package com.example.bar.model;

public class Component {
    public Component(int component_id, String name, String description, String type) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.component_id = component_id;
    }

    public Component(String name, String description, String type) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getComponent_id() {
        return component_id;
    }

    public void setComponent_id(int component_id) {
        this.component_id = component_id;
    }

    String name, type, description;
    int component_id;
}
