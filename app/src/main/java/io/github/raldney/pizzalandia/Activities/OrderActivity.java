package io.github.raldney.pizzalandia.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import io.github.raldney.pizzalandia.Adapters.ListPizzaAdapter;
import io.github.raldney.pizzalandia.Models.Pizza;
import io.github.raldney.pizzalandia.Presenters.PizzaPresenter;
import io.github.raldney.pizzalandia.R;

/**
 * Created by raldney on 03/12/2017.
 */

public class OrderActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PizzaPresenter pizzaPresenter = new PizzaPresenter(this);

        setContentView(R.layout.activity_order);
        ListView lista = (ListView) findViewById(R.id.pizzas_list);
        List<Pizza> pizzas = pizzaPresenter.getPizzas();
        final ListPizzaAdapter pizzaAdapter = new ListPizzaAdapter(this,  pizzas);
        lista.setAdapter(pizzaAdapter);

        lista.setOnItemClickListener(new  AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                PizzaPresenter crud = new PizzaPresenter(getBaseContext());
                Pizza pizza = pizzaAdapter.getItem(position);
                Toast.makeText(OrderActivity.this,pizza.getName(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
