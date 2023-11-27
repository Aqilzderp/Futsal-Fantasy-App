package com.example.projectbaru;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class checkout extends AppCompatActivity {

    private TextView TotalAmount, itemm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_checkout);

        itemm = findViewById(R.id.item);
        TotalAmount = findViewById(R.id.total);

        // Check if the Intent has the required extras
        if (getIntent().hasExtra("total") && getIntent().hasExtra("item")) {
            double total = getIntent().getDoubleExtra("total", 0.00);
            String item = getIntent().getStringExtra("item");

            itemm.setText("Your purchase: " + item);
            TotalAmount.setText("Your Total is RM" + String.format("%.2f", total));
        } else {
            // Handle the case where the extras are not provided
            // You might want to show an error message or take appropriate action
            // For now, let's log an error message
            Log.e("checkout", "Intent is missing required extras");
            // You can also finish the activity or redirect to another activity as needed
            finish();
        }
    }
}

