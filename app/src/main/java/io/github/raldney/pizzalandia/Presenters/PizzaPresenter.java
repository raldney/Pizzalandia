package io.github.raldney.pizzalandia.Presenters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

/**
 * Created by raldney on 04/12/2017.
 */

public class PizzaPresenter extends Presenter {

    private static String TAG = "PizzaPresenter: ";

    public PizzaPresenter(Context context){
        super(context);
    }

    public List<Pizza> getPizzas(){
            List<Pizza> list = new ArrayList<Pizza>();
            String[] columns = new String[]{CreateDatabase.ID, CreateDatabase.NAME, CreateDatabase.PRICE};

            Cursor cursor = db.query(CreateDatabase.PIZZAS_TABLE, columns, null, null, null, null, CreateDatabase.NAME + " ASC");

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

                database.cleanTable(db, CreateDatabase.PIZZAS_TABLE);
                for(int x = 0; x < pizzas.size(); x++){
                    valores = new ContentValues();
                    valores.put(CreateDatabase.ID, Long.parseLong(pizzas.get(x).get("id").toString()));
                    valores.put(CreateDatabase.NAME, pizzas.get(x).get("name").toString());
                    valores.put(CreateDatabase.PRICE, pizzas.get(x).get("price").toString());
                    resultado = db.insert(CreateDatabase.PIZZAS_TABLE, null, valores);
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
