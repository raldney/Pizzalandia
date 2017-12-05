package io.github.raldney.pizzalandia.Presenters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.github.raldney.pizzalandia.CreateDatabase;
import io.github.raldney.pizzalandia.Models.Pizza;

import static android.content.ContentValues.TAG;
import static io.github.raldney.pizzalandia.CreateDatabase.TABELA;

/**
 * Created by raldney on 04/12/2017.
 */

public class PizzaPresenter {
     private SQLiteDatabase db;
     private CreateDatabase database;

    public PizzaPresenter(Context context){
        database = new CreateDatabase(context);
        db = database.getWritableDatabase();
    }

    public List<Pizza> getPizzas(){
            List<Pizza> list = new ArrayList<Pizza>();
            String[] columns = new String[]{"id", "name", "price"};

            Cursor cursor = db.query(TABELA, columns, null, null, null, null, "name ASC");

            if(cursor.getCount() > 0){
                cursor.moveToFirst();

                do{
                    list.add(new Pizza(cursor.getLong(0),cursor.getString(1),cursor.getDouble(2)));

                }while(cursor.moveToNext());
            }

            return(list);
    }

    public void createPizzas(){
        final FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = firebase.getReference("pizzas");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ContentValues valores;
                long resultado;
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                List<HashMap<String,Object>> pizzas = (List<HashMap<String,Object>>) dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + pizzas.get(0));

                database.cleanTable(db,TABELA);
                for(int x = 0; x < pizzas.size(); x++){
                    valores = new ContentValues();
                    valores.put(CreateDatabase.ID, Long.parseLong(pizzas.get(x).get("id").toString()));
                    valores.put(CreateDatabase.NAME, pizzas.get(x).get("name").toString());
                    valores.put(CreateDatabase.PRICE, pizzas.get(x).get("price").toString());
                    resultado = db.insert(TABELA, null, valores);
                    Log.d(TAG, "Inserior is: " + resultado);
                }

                db.close();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }
}
