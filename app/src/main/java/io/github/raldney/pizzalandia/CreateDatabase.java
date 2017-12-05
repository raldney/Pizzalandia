package io.github.raldney.pizzalandia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by raldney on 04/12/2017.
 */

public class CreateDatabase extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "pizzalandia.db";
    public static final String TABELA = "pizzas";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    private static final int VERSAO = 1;

    public CreateDatabase(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key,"
                + NAME + " text,"
                + PRICE + " decimal"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }

    public void cleanTable(SQLiteDatabase db, String table){
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
