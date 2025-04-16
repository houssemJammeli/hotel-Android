package com.example.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectRoomActivity extends AppCompatActivity {
    private Button ReservationBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ReservationBtn = findViewById(R.id.makeReservationButton);
        ReservationBtn.setOnClickListener(v -> {
            Intent ReservationIntent = new Intent(SelectRoomActivity.this, SelectServiceActivity.class);
        });
    }
}