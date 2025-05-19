package com.example.hotel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReservationAdapter adapter;
    private List<Reservation> reservationList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        recyclerView = findViewById(R.id.recycler_reservations);
        db = FirebaseFirestore.getInstance();

        reservationList = new ArrayList<>();
        adapter = new ReservationAdapter(reservationList, new ReservationAdapter.OnReservationActionListener() {
            @Override
            public void onAccept(Reservation reservation) {
                // Mettre à jour le statut de la réservation dans Firebase
                db.collection("reservations").document(reservation.getId())
                        .update("status", "accepted")
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(AdminDashboardActivity.this, "Réservation acceptée", Toast.LENGTH_SHORT).show();
                        });
            }

            @Override
            public void onReject(Reservation reservation) {
                db.collection("reservations").document(reservation.getId())
                        .update("status", "refused")
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(AdminDashboardActivity.this, "Réservation refusée", Toast.LENGTH_SHORT).show();
                        });
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadReservations();
    }

    private void loadReservations() {
        db.collection("reservations")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                            Reservation reservation = document.toObject(Reservation.class);
                            reservation.setId(document.getId());
                            reservationList.add(reservation);
                        }
                        adapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(AdminDashboardActivity.this, "Erreur de chargement", Toast.LENGTH_SHORT).show();
                });
    }
}
