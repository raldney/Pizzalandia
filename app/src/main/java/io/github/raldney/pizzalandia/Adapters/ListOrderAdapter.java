package io.github.raldney.pizzalandia.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import io.github.raldney.pizzalandia.Models.Order;
import io.github.raldney.pizzalandia.R;

/**
 * Created by raldney on 06/12/2017.
 */

public class ListOrderAdapter extends ArrayAdapter<Order> {
    private Context context;
    private List<Order> orders = null;

    public ListOrderAdapter(Context context, List<Order> orders) {
        super(context, 0, orders);
        this.orders = orders;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Order order = orders.get(position);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_list_order, null);


        TextView orderDateTextView = (TextView) view.findViewById(R.id.order_date_textview);
        orderDateTextView.setText(order.getCreate_at().toString());
        Locale ptBr = new Locale("pt", "BR");
        String textPrice = NumberFormat.getCurrencyInstance(ptBr).format(order.getValue());
        TextView textViewPrice = (TextView) view.findViewById(R.id.order_value_textview);
        textViewPrice.setText(textPrice);

        return view;
    }
}