package io.github.raldney.pizzalandia.Presenters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private static String[] columns = new String[]{CreateDatabase.ID, CreateDatabase.CREATE_AT, CreateDatabase.TOTAL_VALUE, CreateDatabase.STATUS};


    public OrderPresenter(Context context) {
        super(context);
    }

    public Order getOrder(){
        String column = CreateDatabase.STATUS + " = ?";
        String[] arg = { "0" }; //PEDIDO EM ABERTO
        Cursor cursor = db.query(CreateDatabase.ORDER_TABLE, columns, column, arg, null, null, CreateDatabase.CREATE_AT + " ASC");
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            try {
                return (new Order(cursor.getInt(0), dateFormat.parse("21/05/1992"), cursor.getDouble(2), cursor.getInt(3)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

            Log.d(TAG, "Get is: " + cursor.getCount());

        return null;
    }

    public List<Order> getOrders() {
        List<Order> list = new ArrayList<Order>();

        Cursor cursor = db.query(CreateDatabase.ORDER_TABLE, columns, null, null, null, null, CreateDatabase.CREATE_AT + " ASC");

        if (cursor.getCount() > 0) {
            Log.d(TAG, "Insert is: " + cursor.getCount());
            cursor.moveToFirst();
            do {
                Date convertedDate = new Date();
                try {
                    list.add(new Order(cursor.getInt(0), dateFormat.parse("21/05/1992"), cursor.getDouble(2),cursor.getInt(3)));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        return (list);
    }

    public Long createOrder(Pizza pizza) {
        ContentValues values;
        long resultado;
        Order order = new Order();
        values = new ContentValues();
        values.put(CreateDatabase.TOTAL_VALUE, order.getValue());
        values.put(CreateDatabase.CREATE_AT, order.getCreate_at().toString());
        values.put(CreateDatabase.STATUS, order.getStatus());
        resultado = db.insert(CreateDatabase.ORDER_TABLE, null, values);
        Log.d(TAG, "Insert is: " + resultado);

        return resultado;
    }

    public Long insertPizzaInOrder(Order order, Pizza pizza, Integer quantity) {
        ContentValues values;
        long resultado;
        values = new ContentValues();
        values.put(CreateDatabase.ORDER_TABLE + "_" + CreateDatabase.ID, order.getId());
        values.put(CreateDatabase.PIZZAS_TABLE + "_" + CreateDatabase.ID, pizza.getId());
        values.put(CreateDatabase.QUANTITY, quantity);
        resultado = db.insert(CreateDatabase.ORDER_PIZZA_TABLE, null, values);
        Log.d(TAG, "Insert is: " + resultado);
        alterTotalValueOrder(order,pizza.getPrice());

        return resultado;
    }

    public int alterTotalValueOrder(Order order,Double pizzaPrice) {

        ContentValues values = new ContentValues();
        values.put(CreateDatabase.TOTAL_VALUE, order.getValue() + pizzaPrice);

        String selection = CreateDatabase.ID + " = ?";
        String[] selectionArgs = { order.getId().toString()};

        int count = db.update(
                CreateDatabase.ORDER_TABLE,
                values,
                selection,
                selectionArgs);

        return count;
    }
}
