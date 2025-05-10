package com.example.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private List<Reservation> reservationList;
    private OnReservationActionListener onReservationActionListener;

    public ReservationAdapter(List<Reservation> reservationList, OnReservationActionListener listener) {
        this.reservationList = reservationList;
        this.onReservationActionListener = listener;
    }



    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);
        holder.bind(reservation);
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public class ReservationViewHolder extends RecyclerView.ViewHolder {

        TextView roomText, servicesText, totalText, statusText;
        Button acceptButton, rejectButton;

        public ReservationViewHolder(View itemView) {
            super(itemView);
            roomText = itemView.findViewById(R.id.text_room);
            servicesText = itemView.findViewById(R.id.text_services);
            totalText = itemView.findViewById(R.id.text_total);
            statusText = itemView.findViewById(R.id.text_status);
            acceptButton = itemView.findViewById(R.id.btn_accept);
            rejectButton = itemView.findViewById(R.id.btn_reject);
        }

        public void bind(Reservation reservation) {
            roomText.setText(reservation.getRoom());
            if (reservation.getServices() != null && !reservation.getServices().isEmpty()) {
                servicesText.setText("Services :\n- " + String.join("\n- ", reservation.getServices()));
            } else {
                servicesText.setText("Aucun service.");
            }

            totalText.setText("$" + reservation.getTotal());
            statusText.setText(reservation.getStatus());

            acceptButton.setOnClickListener(v -> onReservationActionListener.onAccept(reservation));
            rejectButton.setOnClickListener(v -> onReservationActionListener.onReject(reservation));
        }
    }

    public interface OnReservationActionListener {
        void onAccept(Reservation reservation);
        void onReject(Reservation reservation);
    }
}
