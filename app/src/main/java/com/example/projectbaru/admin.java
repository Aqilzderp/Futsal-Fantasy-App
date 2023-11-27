package com.example.projectbaru;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin extends AppCompatActivity {
    EditText inputEmail, inputPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().hide();

        inputEmail = findViewById(R.id.fstTxt);
        inputPass = findViewById(R.id.txtPwd);
        btnLogin = findViewById(R.id.btnLogin);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performAdminLogin();
            }
        });
    }

    private void performAdminLogin() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPass.getText().toString().trim();

        // Hardcoded admin credentials
        String adminEmail = "aqil03@live.com";
        String adminPassword = "hoho0303";

        if (TextUtils.equals(email, adminEmail) && TextUtils.equals(password, adminPassword)) {
            // Admin login successful
            sendAdminToAdminActivity();
        } else {
            // Admin login failed
            Toast.makeText(admin.this, "Invalid admin credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendAdminToAdminActivity() {
        Intent intent = new Intent(admin.this, adminActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
