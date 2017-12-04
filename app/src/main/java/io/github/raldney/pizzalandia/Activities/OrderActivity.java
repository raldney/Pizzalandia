package io.github.raldney.pizzalandia.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.raldney.pizzalandia.Adapters.ListPizzaAdapter;
import io.github.raldney.pizzalandia.Models.Pizza;
import io.github.raldney.pizzalandia.R;

/**
 * Created by raldney on 03/12/2017.
 */

public class OrderActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ListView lista = (ListView) findViewById(R.id.pizzas_list);
        List<Pizza> pizzas = allPizzas();
        final ListPizzaAdapter pizzaAdapter = new ListPizzaAdapter(this,  pizzas);
        lista.setAdapter(pizzaAdapter);

        lista.setOnItemClickListener(new  AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Pizza pizza = pizzaAdapter.getItem(position);
                Toast.makeText(OrderActivity.this,pizza.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Exemplo qualquer de devolução de uma lista de cursos.
     * Para esse exemplo será considerado um hard coded.
     *
     * @return lista com todos os cursos
     */
    private List<Pizza> allPizzas() {
        return new ArrayList<>(Arrays.asList(
                new Pizza("Portuguesa", 40.00, null),
                new Pizza("Peperoni", 30.00, null),
                new Pizza("Mussarela", 20.00, null)));
    }
}
