package io.github.raldney.pizzalandia.Models;

import java.util.Date;

/**
 * Created by raldney on 04/12/2017.
 */

public class Order {

    protected Integer id ;
    protected Date create_at;
    protected Double value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Order(Integer id, Date create_at, Double value) {
        this.id = id;
        this.create_at = create_at;
        this.value = value;
    }


}
