package io.github.raldney.pizzalandia.Presenters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import io.github.raldney.pizzalandia.CreateDatabase;

/**
 * Created by raldney on 05/12/2017.
 */

public class Presenter {
    protected SQLiteDatabase db;
    protected CreateDatabase database;
    Presenter(Context context){
        database = new CreateDatabase(context);
        db = database.getWritableDatabase();
    }

}
