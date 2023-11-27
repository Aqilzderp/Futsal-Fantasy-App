package com.example.projectbaru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class aboutMenu extends AppCompatActivity implements View.OnClickListener {
    private CardView AB1, AB2;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_menu);
        AB1 = (CardView) findViewById(R.id.About1);
        AB2 = (CardView) findViewById(R.id.About2);

        AB1.setOnClickListener((View.OnClickListener) this);
        AB2.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        if ((v.getId()) == R.id.About1) {
            i = new Intent(this, Ab1.class);
            startActivity(i);
        } else if ((v.getId()) == R.id.About2) {
            i = new Intent(this, d2.class);
            startActivity(i);

        }
    }
}