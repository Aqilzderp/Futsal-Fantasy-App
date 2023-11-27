package com.example.projectbaru;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class history extends AppCompatActivity {

    private EditText editTextTeamName, editTextPhoneNumber, editTextBookingDate;
    private Button btnSend;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        editTextTeamName = findViewById(R.id.edit_text_to);
        editTextPhoneNumber = findViewById(R.id.edit_text_subject);
        editTextBookingDate = findViewById(R.id.edit_text_message);
        btnSend = findViewById(R.id.button_send);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("booking");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBookingDataToDatabase();
            }
        });
    }

    private void sendBookingDataToDatabase() {
        String teamName = editTextTeamName.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String bookingDate = editTextBookingDate.getText().toString().trim();

        if (!TextUtils.isEmpty(teamName) && !TextUtils.isEmpty(phoneNumber) && !TextUtils.isEmpty(bookingDate)) {
            // Create a unique key for the new booking (optional)
            String bookingId = databaseReference.child("users").push().getKey();

            // Create a Map to represent booking data
            Map<String, Object> bookingData = new HashMap<>();
            bookingData.put("name", teamName);
            bookingData.put("phone", phoneNumber);
            bookingData.put("date", bookingDate);

            // Save the booking data to the 'users' node with the generated key (or without if not using a key)
            databaseReference.child("users").child(bookingId).setValue(bookingData);

            // Optionally, you can clear the input fields
            editTextTeamName.setText("");
            editTextPhoneNumber.setText("");
            editTextBookingDate.setText("");

            Toast.makeText(history.this, "Booking added to the database", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(history.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
}