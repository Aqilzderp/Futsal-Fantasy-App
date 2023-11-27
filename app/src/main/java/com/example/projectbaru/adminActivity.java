package com.example.projectbaru;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class adminActivity extends AppCompatActivity {

    private EditText editTextName, editTextPhone, editTextDate;
    private Button btnAddBooking, btnUpdateBooking, btnDeleteBooking;
    private TextView textViewBookingInfo;
    private DatabaseReference databaseReference;

    private View lastSelectedView;

    private String selectedBookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextDate = findViewById(R.id.editTextDate);
        btnAddBooking = findViewById(R.id.btnAddBooking);
        btnUpdateBooking = findViewById(R.id.btnUpdateBooking);
        btnDeleteBooking = findViewById(R.id.btnDeleteBooking);
        textViewBookingInfo = findViewById(R.id.textViewBookingInfo);

        // Find the bookingEntry view for handling clicks
        View bookingEntry = findViewById(R.id.bookingEntry);
        bookingEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBookingEntryClick(view);
            }
        });

        // Reference to the "users" node under the "booking" node
        databaseReference = FirebaseDatabase.getInstance().getReference("booking").child("users");

        // Retrieve and display booking data
        retrieveBookingData();

        btnAddBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBooking();
            }
        });
        final EditText editTextBookingId = findViewById(R.id.editTextBookingId);
        btnUpdateBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the Booking ID from the EditText
                String enteredBookingId = editTextBookingId.getText().toString().trim();

                // Use the enteredBookingId for update
                if (!enteredBookingId.isEmpty()) {
                    selectedBookingId = enteredBookingId;
                    updateBooking();
                } else {
                    // Handle case where Booking ID is empty
                    Toast.makeText(adminActivity.this, "Please enter Booking ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDeleteBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the Booking ID from the EditText
                String enteredBookingId = editTextBookingId.getText().toString().trim();

                // Use the enteredBookingId for deletion
                if (!enteredBookingId.isEmpty()) {
                    selectedBookingId = enteredBookingId;
                    deleteBooking();
                } else {
                    // Handle case where Booking ID is empty
                    Toast.makeText(adminActivity.this, "Please enter Booking ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void retrieveBookingData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder bookingInfo = new StringBuilder();

                for (DataSnapshot bookingSnapshot : dataSnapshot.getChildren()) {
                    String name = bookingSnapshot.child("name").getValue(String.class);
                    String phone = bookingSnapshot.child("phone").getValue(String.class);
                    String date = bookingSnapshot.child("date").getValue(String.class);
                    String bookingId = bookingSnapshot.getKey(); // Get the unique ID of the booking

                    // Append the booking information to the StringBuilder
                    bookingInfo.append("Name: ").append(name).append("\n");
                    bookingInfo.append("Phone: ").append(phone).append("\n");
                    bookingInfo.append("Date: ").append(date).append("\n\n");

                    // Append the booking ID to the layout for each booking entry
                    // This is important for identifying which booking to update or delete
                    bookingInfo.append("Booking ID: ").append(bookingId).append("\n\n");
                }

                // Set the booking information to the TextView
                textViewBookingInfo.setText(bookingInfo.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }

    // Handle booking entry clicks
    public void onBookingEntryClick(View view) {
        TextView bookingIdTextView = view.findViewById(R.id.bookingIdTextView);
        if (bookingIdTextView != null) {
            selectedBookingId = bookingIdTextView.getText().toString().trim();


            // Optional: You can display a message or highlight the selected entry
            displayMessage("Selected Booking ID: " + selectedBookingId);

            // Highlight the selected entry by changing its background color
            view.setBackgroundColor(getResources().getColor(R.color.selectedBookingEntryBackgroundColor));

            // Unhighlight the previously selected entry (if any)
            if (lastSelectedView != null) {
                lastSelectedView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            }
        }

        // Save the currently selected view for future unhighlighting
        lastSelectedView = view;
    }

    // Helper method to display a Toast message
    private void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void addBooking() {
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();

        if (!name.isEmpty() && !phone.isEmpty() && !date.isEmpty()) {
            // Create a unique key for the new booking
            String bookingId = databaseReference.push().getKey();

            // Create a Booking object or use a Map
            Map<String, Object> newBooking = new HashMap<>();
            newBooking.put("name", name);
            newBooking.put("phone", phone);
            newBooking.put("date", date);

            // Save the booking data to the 'users' node with the generated key
            databaseReference.child(bookingId).setValue(newBooking);

            // Optionally, you can clear the input fields
            editTextName.setText("");
            editTextPhone.setText("");
            editTextDate.setText("");

            Toast.makeText(adminActivity.this, "Booking added to the database", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(adminActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateBooking() {
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();

        if (!selectedBookingId.isEmpty() && !name.isEmpty() && !phone.isEmpty() && !date.isEmpty()) {
            // Create a Map to represent updated booking data
            // (You might want to handle this part according to your specific data structure)
            // For simplicity, let's assume we are updating all fields

            databaseReference.child(selectedBookingId).child("name").setValue(name);
            databaseReference.child(selectedBookingId).child("phone").setValue(phone);
            databaseReference.child(selectedBookingId).child("date").setValue(date);

            clearFields();
            Toast.makeText(adminActivity.this, "Booking updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(adminActivity.this, "Please select a booking and fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteBooking() {
        if (!selectedBookingId.isEmpty()) {
            databaseReference.child(selectedBookingId).removeValue();
            clearFields();
            Toast.makeText(adminActivity.this, "Booking deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(adminActivity.this, "Please select a booking to delete", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        editTextName.setText("");
        editTextPhone.setText("");
        editTextDate.setText("");
        selectedBookingId = "";
    }
}

