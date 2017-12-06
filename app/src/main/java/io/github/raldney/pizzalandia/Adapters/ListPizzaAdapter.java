package io.github.raldney.pizzalandia.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;
 import	java.text.NumberFormat;

import io.github.raldney.pizzalandia.Models.Pizza;
import io.github.raldney.pizzalandia.R;

/**
 * Created by raldney on 03/12/2017.
 */

public class ListPizzaAdapter extends ArrayAdapter<Pizza> {

    private Context context;
    private List<Pizza> pizzas = null;

    public ListPizzaAdapter(Context context,  List<Pizza> pizzas) {
        super(context,0, pizzas);
        this.pizzas = pizzas;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Pizza pizza = pizzas.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_list_pizza, null);


        TextView namePizzaTextView = (TextView) view.findViewById(R.id.name_pizza_textview);
        namePizzaTextView.setText(pizza.getName());
        Locale ptBr = new Locale("pt", "BR");
        String textPrice = NumberFormat.getCurrencyInstance(ptBr).format(pizza.getPrice());
        TextView textViewPrice = (TextView)view.findViewById(R.id.price_pizza_textview);
        textViewPrice.setText(textPrice);

        return view;
    }
}