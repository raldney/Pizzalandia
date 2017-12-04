package io.github.raldney.pizzalandia.Models;

import java.util.List;

/**
 * Created by raldney on 03/12/2017.
 */

public class Pizza {
    private String name;
    private Double price;
    private List<String> ingredients;

    public Pizza(){}

    public Pizza(String name, Double price, List<String> ingredients) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }


}
