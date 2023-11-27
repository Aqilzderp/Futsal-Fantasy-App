package com.example.projectbaru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class d5 extends AppCompatActivity implements View.OnClickListener {
    private CardView match, booking, booksponsor, Back2;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d5);
        match = (CardView) findViewById(R.id.matchHistory);
        booking = (CardView) findViewById(R.id.booking);
        booksponsor = (CardView) findViewById(R.id.booksponsor);
        Back2 = (CardView) findViewById(R.id.Back2);

        match.setOnClickListener((View.OnClickListener) this);
        booking.setOnClickListener((View.OnClickListener) this);
        booksponsor.setOnClickListener((View.OnClickListener) this);
        Back2.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        if ((v.getId()) == R.id.matchHistory) {
            i = new Intent(this, match.class);
            startActivity(i);
        } else if ((v.getId()) == R.id.booking) {
            i = new Intent(this, history.class);
            startActivity(i);

        } else if ((v.getId()) == R.id.booksponsor) {
            i = new Intent(this, booksponsor.class);
            startActivity(i);

        } else if ((v.getId()) == R.id.Back2) {
            i = new Intent(this, MainActivity2.class);
            startActivity(i);

        }
    }
}