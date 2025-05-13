package com.example.hotel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReservationAdapterClient extends RecyclerView.Adapter<ReservationAdapterClient.ViewHolder> {

    private List<Reservation> reservationList;

    public ReservationAdapterClient(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation_client, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);
        holder.textRoom.setText("Room: " + reservation.getRoom());

        if (reservation.getServices() != null && !reservation.getServices().isEmpty()) {
            StringBuilder sb = new StringBuilder("Services:\n");
            for (String service : reservation.getServices()) {
                sb.append("- ").append(service).append("\n");
            }
            holder.textServices.setText(sb.toString().trim());
        } else {
            holder.textServices.setText("Services: Aucun");
        }

        holder.textTotal.setText("Total: $" + reservation.getTotal());
        holder.textStatus.setText("Status: " + reservation.getStatus());
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textRoom, textServices, textTotal, textStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textRoom = itemView.findViewById(R.id.text_room);
            textServices = itemView.findViewById(R.id.text_services);
            textTotal = itemView.findViewById(R.id.text_total);
            textStatus = itemView.findViewById(R.id.text_status);
        }
    }
}
