package com.example.projectbaru;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity5 extends AppCompatActivity {

    private ImageButton imgbtn1;
    private Button button, Home;
    private CheckBox checkBox1;
    private int  quantity1;
    private double total;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main5);


        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);

        button = (Button) findViewById(R.id.button);
        Home = (Button) findViewById(R.id.buttonBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item1 = "";
                String item = "";
                double total = 0.0;

                // Remove the following line to avoid redeclaration
                // int quantity1 = Integer.parseInt(((EditText) findViewById(R.id.quantity1)).getText().toString());

                // Use the class variable instead
                quantity1 = Integer.parseInt(((EditText) findViewById(R.id.quantity1)).getText().toString());

                if (checkBox1.isChecked()) {
                    total += 50.00 * quantity1;
                    item1 = "FAS Performance Poloshirt ";
                }

                item = item1;

                Intent intent = new Intent(MainActivity5.this, checkout.class);
                intent.putExtra("total", total);
                intent.putExtra("item", item);
                startActivity(intent);
            }
        });

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity5.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }

}
