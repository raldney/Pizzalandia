package io.github.raldney.pizzalandia.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.List;

import io.github.raldney.pizzalandia.Adapters.ListPizzaAdapter;
import io.github.raldney.pizzalandia.Models.Order;
import io.github.raldney.pizzalandia.Models.Pizza;
import io.github.raldney.pizzalandia.Presenters.OrderPresenter;
import io.github.raldney.pizzalandia.Presenters.PizzaPresenter;
import io.github.raldney.pizzalandia.R;

/**
 * Created by raldney on 03/12/2017.
 */

public class OrderActivity extends BaseActivity implements View.OnClickListener{

    Order order;
    OrderPresenter orderPresenter;
    PizzaPresenter pizzaPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pizzaPresenter = new PizzaPresenter(this);
        orderPresenter= new OrderPresenter(getBaseContext());


        setContentView(R.layout.activity_order);
        updateValueView();
        ListView lista = (ListView) findViewById(R.id.pizzas_list);
        List<Pizza> pizzas = pizzaPresenter.getPizzas();
        final ListPizzaAdapter pizzaAdapter = new ListPizzaAdapter(this,  pizzas);
        lista.setAdapter(pizzaAdapter);

        lista.setOnItemClickListener(new  AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                order = orderPresenter.getOrder();
                Pizza pizza = pizzaAdapter.getItem(position);
                if(order == null)
                    orderPresenter.createOrder(pizza);
                else
                    orderPresenter.insertPizzaInOrder(order,pizza,1);

                updateValueView();
                Toast.makeText(OrderActivity.this,pizza.getName(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void updateValueView(){
        order = orderPresenter.getOrder();
        if(order != null) {
            TextView order_value = (TextView) findViewById(R.id.value_textview);
            order_value.setText(NumberFormat.getCurrencyInstance(ptBr).format(order.getValue()));
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.cancel_order_Button) {
            Toast.makeText(OrderActivity.this, "Entrando em Pedidos",
                    Toast.LENGTH_SHORT).show();
            startNewActivity(OrderActivity.class);
        }else if (i == R.id.see_order_button){
            Toast.makeText(OrderActivity.this, "Entrando em Meus Pedidos",
                    Toast.LENGTH_SHORT).show();
            startNewActivity(MyOrdersActivity.class);
        }
    }
}
