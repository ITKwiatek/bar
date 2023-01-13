package com.example.bar.utility;

import android.widget.TextView;

import com.example.bar.model.Component;
import com.example.bar.model.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryHelper {
    ConnectionHelper connectionHelper;
    Connection connection;

    public QueryHelper() {
        connectionHelper = new ConnectionHelper();
        connection = connectionHelper.conclass();
    }

    public boolean AddComponent(Component component) throws Exception {
        try {
            if (connection != null) {
                String query = "INSERT INTO component(name, description, type) " +
                        "VALUES ('" + component.getName() +
                        "', '" + component.getDescription() +
                        "', '" + component.getType() + "')";
                Statement st = connection.createStatement();
                int ex = st.executeUpdate(query);
                if (ex == 1)
                    return true;
                else
                    return false;
            }
        } catch (Exception e) {
            throw new Exception("No DB connection " + e.getMessage());
        }
        return false;
    }

    public int AddRecipe(Recipe recipe) throws Exception {
        try {
            if (connection != null) {
                String query = "INSERT INTO recipe (name, description) " +
                        "VALUES ('" + recipe.getName() +
                        "', '" + recipe.getDescription() + "')";
                PreparedStatement st = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                int ex = st.executeUpdate();

                ResultSet rs = st.getGeneratedKeys();
                try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        recipe.setRecipe_id(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating recipe failed.");
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("No DB connection " + e.getMessage());
        }
        return recipe.getRecipe_id();
    }

    public boolean AddRecipeComponent(String[] components, int recipe_id) throws Exception {
        boolean inserted = false;
        ArrayList<Integer> componentIdList = getComponentsIdsByNames(components);
        try {
            if (connection != null) {
                for (int i = 0; i < componentIdList.size(); i++) {
                    String query = "INSERT INTO recipe_component (recipe_id, component_id) " +
                            "VALUES (" + recipe_id + ", " + componentIdList.get(i) + ")";
                    PreparedStatement st = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    st.executeUpdate();

                    ResultSet rs = st.getGeneratedKeys();
                    try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            inserted = true;
                        } else {
                            throw new SQLException("Creating recipe_component failed.");
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("No DB connection " + e.getMessage());
        }
        return inserted;
    }

    public ArrayList<Component> getComponentList() throws Exception {
        ArrayList<Component> componentList = new ArrayList<Component>();
        ;
        try {
            if (connection != null) {
                String query = "Select * from component";
                Statement st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(query);

                while (resultSet.next()) {
                    Component c = new Component(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                    componentList.add(c);
                }
            }
        } catch (Exception e) {
            throw new Exception("No DB connection " + e.getMessage());
        }
        return componentList;
    }

    public String[] getComponentNames() throws Exception {
        ArrayList<String> recipes = new ArrayList<>();
        try {
            if (connection != null) {
                String query = "Select name from component";
                Statement st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(query);

                while (resultSet.next()) {
                    recipes.add(resultSet.getString(1));
                }
            }
        } catch (Exception e) {
            throw new Exception("No DB connection " + e.getMessage());
        }
        return recipes.toArray(new String[recipes.size()]);
    }

    public ArrayList<Recipe> getRecipeList() throws Exception {
        ArrayList<Recipe> componentList = new ArrayList<Recipe>();
        try {
            if (connection != null) {
                String query = "Select name, description from recipe";
                Statement st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(query);

                while (resultSet.next()) {
                    Recipe r = new Recipe(resultSet.getString(1), resultSet.getString(2));
                    componentList.add(r);
                }
            }
        } catch (Exception e) {
            throw new Exception("No DB connection " + e.getMessage());
        }
        return componentList;
    }


    public ArrayList<Recipe> getRecipesBasedOnComponents(TextView tvSelectedComponents) {
        return new ArrayList<>();
    }

    private ArrayList<Integer> getComponentsIdsByNames(String[] componentNames) throws Exception {
        ArrayList<Integer> ids = new ArrayList<>();
        try {
            if (connection != null) {
                List<String> namesInList = Arrays.asList(componentNames);
                String namesInQuery = String.join("','", namesInList).replaceAll("\\s+","");
                String query = "Select component_id from component where name in ('" + namesInQuery + "')";
                PreparedStatement st = connection.prepareStatement(query);
                ResultSet resultSet = st.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    ids.add(id);
                }
            }
        } catch (Exception e) {
            throw new Exception("No DB connection " + e.getMessage());
        }
        return ids;
    }
}
