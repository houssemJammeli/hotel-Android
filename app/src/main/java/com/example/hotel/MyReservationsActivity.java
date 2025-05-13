package com.example.hotel;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class MyReservationsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReservationAdapterClient adapter;
    private ArrayList<Reservation> reservationList;
    private FirebaseFirestore db;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations); // tu vas le créer aussi

        recyclerView = findViewById(R.id.recycler_my_reservations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reservationList = new ArrayList<>();
        adapter = new ReservationAdapterClient(reservationList);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            db.collection("reservations")
                    .whereEqualTo("userId", user.getUid())
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        reservationList.clear();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                            Reservation res = doc.toObject(Reservation.class);
                            res.setId(doc.getId());
                            reservationList.add(res);
                        }
                        adapter.notifyDataSetChanged();
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Erreur de chargement", Toast.LENGTH_SHORT).show());
        }
    }
}
