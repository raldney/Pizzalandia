package io.github.raldney.pizzalandia.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import io.github.raldney.pizzalandia.Models.Pizza;
import io.github.raldney.pizzalandia.R;

/**
 * Created by raldney on 03/12/2017.
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private Button orderButton, myOrdersButton, aboutUsButton,signOutButton;
    private static final String TAG = "Home";
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Views
        orderButton = (Button)findViewById(R.id.order_button);
        myOrdersButton = (Button)findViewById(R.id.my_orders_button);
        aboutUsButton = (Button)findViewById(R.id.about_us_button);
        signOutButton = (Button)findViewById(R.id.signout_button);

        // Buttons
        orderButton.setOnClickListener(this);
        myOrdersButton.setOnClickListener(this);
        aboutUsButton.setOnClickListener(this);
        signOutButton.setOnClickListener(this);


        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();

        // [END initialize_auth]
        Log.d("USUARIO",mAuth.getCurrentUser().getEmail());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("pizzas");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                List<Pizza> values = (List<Pizza>) dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + values.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.order_button) {
            Toast.makeText(HomeActivity.this, "Entrando em Pedidos",
                    Toast.LENGTH_SHORT).show();
            startNewActivity(OrderActivity.class);
        }else if (i == R.id.signout_button){
            mAuth.signOut();
            startNewActivity(MainActivity.class);
        }
    }
}
