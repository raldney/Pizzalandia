package io.github.raldney.pizzalandia.Presenters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.github.raldney.pizzalandia.CreateDatabase;
import io.github.raldney.pizzalandia.Models.Order;
import io.github.raldney.pizzalandia.Models.Pizza;

/**
 * Created by raldney on 05/12/2017.
 */

public class OrderPresenter extends Presenter {

    private static String TAG = "OrderPresenter: ";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat();


    public OrderPresenter(Context context){
       super(context);
    }

    public List<Order> getOrders(){
        List<Order> list = new ArrayList<Order>();
        String[] columns = new String[]{CreateDatabase.ID, CreateDatabase.NAME, CreateDatabase.PRICE};

        Cursor cursor = db.query(CreateDatabase.ORDER_TABLE, columns, null, null, null, null, CreateDatabase.NAME + " ASC");

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Date convertedDate = new Date();
                try {
                    list.add(new Order(cursor.getInt(0), dateFormat.parse(cursor.getString(1)), cursor.getDouble(2)));
                } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            }while(cursor.moveToNext());
        }

        return(list);
    }

    public Long createOrder(Pizza pizza){
        ContentValues valores;
        long resultado;
        Date currentTime = Calendar.getInstance().getTime();
        valores = new ContentValues();
        valores.put(CreateDatabase.TOTAL_VALUE, "0");
        valores.put(CreateDatabase.CREATE_AT, currentTime.toString());
        resultado = db.insert(CreateDatabase.PIZZAS_TABLE, null, valores);
        Log.d(TAG, "Insert is: " + resultado);
        db.close();

        return resultado;
    }

    public Long insertPizzaOnOrder(Order order, Pizza pizza){
        ContentValues valores;
        long resultado;
        valores = new ContentValues();
        valores.put(CreateDatabase.ORDER_TABLE + "_"+ CreateDatabase.ID , order.getId());
        valores.put(CreateDatabase.PIZZAS_TABLE + "_"+ CreateDatabase.ID, pizza.getId());
        resultado = db.insert(CreateDatabase.ORDER_PIZZA_TABLE, null, valores);
        Log.d(TAG, "Insert is: " + resultado);
        db.close();

        return resultado;
    }

    public Long alterTotalValueOrder(Double pizzaPrice){
    return null;
    }
}
