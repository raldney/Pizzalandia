package io.github.raldney.pizzalandia.Activities;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import io.github.raldney.pizzalandia.Adapters.ListOrderAdapter;
import io.github.raldney.pizzalandia.Models.Order;
import io.github.raldney.pizzalandia.Presenters.OrderPresenter;
import io.github.raldney.pizzalandia.R;

/**
 * Created by raldney on 06/12/2017.
 */

public class MyOrdersActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OrderPresenter orderPresenter = new OrderPresenter(this);

        setContentView(R.layout.activity_myorders);
        ListView lista = (ListView) findViewById(R.id.orders_list);
        List<Order> orders = orderPresenter.getOrders();
        final ListOrderAdapter orderAdapter = new ListOrderAdapter(this,  orders);
        lista.setAdapter(orderAdapter);

    }
}
