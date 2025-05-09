package com.example.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectRoomActivity extends AppCompatActivity {

    private Button ReservationBtn;
    private CheckBox checkboxRoom1, checkboxRoom2, checkboxRoom3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_room);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        checkboxRoom1 = findViewById(R.id.checkbox_room1);
        checkboxRoom2 = findViewById(R.id.checkbox_room2);
        checkboxRoom3 = findViewById(R.id.checkbox_room3);
        ReservationBtn = findViewById(R.id.btn_confirm_room);

        ReservationBtn.setOnClickListener(v -> {
            String selectedRoom = null;

            if (checkboxRoom1.isChecked()) {
                selectedRoom = "Simple Room - $100";
            } else if (checkboxRoom2.isChecked()) {
                selectedRoom = "Standard Room - $150";
            } else if (checkboxRoom3.isChecked()) {
                selectedRoom = "Luxury Room - $200";
            }

            if (selectedRoom == null) {
                Toast.makeText(this, "Please select a room first", Toast.LENGTH_SHORT).show();
                return;
            }


            Intent reservationIntent = new Intent(SelectRoomActivity.this, SelectServiceActivity.class);
            reservationIntent.putExtra("selected_room", selectedRoom);
            startActivity(reservationIntent);
        });
    }
}
