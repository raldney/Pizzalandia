package io.github.raldney.pizzalandia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by raldney on 04/12/2017.
 */

public class CreateDatabase extends SQLiteOpenHelper {

    private static final String DATABASE = "pizzalandia.db";
    public static final String PIZZAS_TABLE = "pizzas";
    public static final String ORDER_TABLE = "orders";
    public static final String ORDER_PIZZA_TABLE = ORDER_TABLE +"_"+PIZZAS_TABLE ;
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String CREATE_AT = "create_at";
    public static final String TOTAL_VALUE = "total_value";
    public static final String QUANTITY = "quantity";
    public static final String STATUS = "status";
    private static final int VERSION = 63;

    public CreateDatabase(Context context){
        super(context, DATABASE,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTablePizza(db);
        createTableOrder(db);
        createTableOrderPizza(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PIZZAS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_PIZZA_TABLE);
        onCreate(db);
    }

    public void cleanTable(SQLiteDatabase db, String table){
        db.execSQL("DROP TABLE IF EXISTS " + PIZZAS_TABLE);
        createTablePizza(db);
    }

    private void createTablePizza(SQLiteDatabase db){
        String sql = "CREATE TABLE "+PIZZAS_TABLE+"("
                + ID + " integer primary key,"
                + NAME + " text,"
                + PRICE + " decimal"
                +")";
        db.execSQL(sql);
    }

    private void createTableOrder(SQLiteDatabase db){
        String sql = "CREATE TABLE "+ORDER_TABLE+"("
                + ID + " integer primary key,"
                + CREATE_AT + " date,"
                + STATUS + " integer,"
                + TOTAL_VALUE + " decimal"
                +")";
        db.execSQL(sql);
    }

    private void createTableOrderPizza(SQLiteDatabase db){
        String sql = "CREATE TABLE "+ORDER_PIZZA_TABLE+"("
                + ORDER_TABLE+"_"+ID + " integer,"
                + PIZZAS_TABLE+"_"+ID + " integer,"
                + QUANTITY + " int,"
                +" FOREIGN KEY ("+PIZZAS_TABLE+"_"+ID+") REFERENCES "+PIZZAS_TABLE+"("+ID+"))";
        db.execSQL(sql);
    }
}
