package com.example.hotel;

import java.util.ArrayList;

public class Reservation {
    private String id;
    private String room;
    private ArrayList<String> services;
    private int total;
    private String status;

    public Reservation() {

    }

    public Reservation(String room, ArrayList<String> services, int total, String status) {
        this.room = room;
        this.services = services;
        this.total = total;
        this.status = status;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public ArrayList<String> getServices() {
        return services;
    }

    public void setServices(ArrayList<String> services) {
        this.services = services;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
