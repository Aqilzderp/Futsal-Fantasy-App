package com.example.projectbaru;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Defend extends AppCompatActivity {

    private Button Attacker, backPlayer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defend);
        Attacker=findViewById(R.id.Attacker);
        backPlayer=findViewById(R.id.backPlayer);
        Attacker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Defend.this,Attacker.class);
                startActivity(intent);
            }
        });

        backPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Defend.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}