package io.github.raldney.pizzalandia.Models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by raldney on 04/12/2017.
 */

public class Order {

    protected Integer id ;
    protected Date create_at = Calendar.getInstance().getTime();
    protected Double total_value = 0.0;
    protected int status = 0;

    public Order(){}

    public Order(Integer id, Date create_at, Double total_value, Integer status) {
        this.id = id;
        this.create_at = create_at;
        this.total_value = total_value;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Double getValue() {
        return total_value;
    }

    public void setValue(Double total_value) {
        this.total_value = total_value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
