package com.example.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SelectServiceActivity extends AppCompatActivity {

    private CheckBox checkboxSpa, checkboxBreakfast, checkboxLunch, checkboxDinner, checkboxWifi, checkboxParking;
    private Button confirmServiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_service);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        String selectedRoom = getIntent().getStringExtra("selected_room");


        checkboxSpa = findViewById(R.id.checkbox_spa);
        checkboxBreakfast = findViewById(R.id.checkbox_breakfast);
        checkboxLunch = findViewById(R.id.checkbox_lunch);
        checkboxDinner = findViewById(R.id.checkbox_dinner);
        checkboxWifi = findViewById(R.id.checkbox_wifi);
        checkboxParking = findViewById(R.id.checkbox_parking);
        confirmServiceBtn = findViewById(R.id.btn_confirm_service);

        confirmServiceBtn.setOnClickListener(v -> {
            ArrayList<String> selectedServices = new ArrayList<>();

            if (checkboxSpa.isChecked()) selectedServices.add("Spa - $30");
            if (checkboxBreakfast.isChecked()) selectedServices.add("Breakfast - $10");
            if (checkboxLunch.isChecked()) selectedServices.add("Lunch - $15");
            if (checkboxDinner.isChecked()) selectedServices.add("Dinner - $20");
            if (checkboxWifi.isChecked()) selectedServices.add("Wifi - $5");
            if (checkboxParking.isChecked()) selectedServices.add("Parking - $8");


            Intent summaryIntent = new Intent(SelectServiceActivity.this, ReservationSummaryActivity.class);
            summaryIntent.putExtra("selected_room", selectedRoom);
            summaryIntent.putStringArrayListExtra("selected_services", selectedServices);
            startActivity(summaryIntent);
        });
    }
}
