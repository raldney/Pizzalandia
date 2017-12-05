package io.github.raldney.pizzalandia.Models;

/**
 * Created by raldney on 03/12/2017.
 */

public class Pizza {


    private long id;
    private String name;
    private Double price;

    public Pizza(){}

    public Pizza(long id,String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long _id) {
        this.id = id;
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

}
