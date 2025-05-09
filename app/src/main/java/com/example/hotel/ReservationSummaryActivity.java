package com.example.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.firebase.firestore.FirebaseFirestore;

public class ReservationSummaryActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_summary);

        db = FirebaseFirestore.getInstance();

        TextView roomTextView = findViewById(R.id.text_selected_room);
        TextView servicesTextView = findViewById(R.id.text_selected_services);
        TextView totalTextView = findViewById(R.id.text_total_price);
        Button sendBtn = findViewById(R.id.btn_send_request);

        String selectedRoom = getIntent().getStringExtra("selected_room");
        ArrayList<String> selectedServices = getIntent().getStringArrayListExtra("selected_services");

        roomTextView.setText("Chambre sélectionnée : " + selectedRoom);

        int roomPrice = 0;
        switch (selectedRoom) {
            case "Simple Room - $100":
                roomPrice = 100;
                break;
            case "Standard Room - $150":
                roomPrice = 150;
                break;
            case "Luxury Room - $200":
                roomPrice = 200;
                break;
        }

        int servicesPrice = 0;
        StringBuilder servicesBuilder = new StringBuilder("Services :\n");

        if (selectedServices != null && !selectedServices.isEmpty()) {
            for (String service : selectedServices) {
                servicesBuilder.append("- ").append(service).append("\n");

                if (service.contains("Spa")) servicesPrice += 30;
                else if (service.contains("Breakfast")) servicesPrice += 10;
                else if (service.contains("Lunch")) servicesPrice += 15;
                else if (service.contains("Dinner")) servicesPrice += 20;
                else if (service.contains("Wifi")) servicesPrice += 5;
                else if (service.contains("Parking")) servicesPrice += 8;
            }
            servicesTextView.setText(servicesBuilder.toString());
        } else {
            servicesTextView.setText("Aucun service sélectionné.");
        }

        int totalPrice = roomPrice + servicesPrice;
        totalTextView.setText("💰 Total Price : $" + totalPrice);


        sendBtn.setOnClickListener(v -> {
            HashMap<String, Object> reservation = new HashMap<>();
            reservation.put("room", selectedRoom);
            reservation.put("services", selectedServices);
            reservation.put("total", totalPrice);
            reservation.put("status", "waiting"); // Par défaut

            db.collection("reservations")
                    .add(reservation)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Réservation envoyée ✅", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Erreur d'envoi ❌", Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
