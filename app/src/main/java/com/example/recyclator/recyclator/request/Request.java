package com.example.recyclator.recyclator.request;

import android.content.Context;

public class Request {
    String name;
    String kind;
    String location;
    String quantity;
    double rate;
    double latitude;
    double longtude;
    int user_id;
    Context context;

    public Request(String name, String location, String kind, String quantity, double rate, double latitude, double longtude , int user_id ,Context context) {
        this.name = name;
        this.kind = kind;
        this.location = location;
        this.quantity = quantity;
        this.rate = rate;
        this.latitude = latitude;
        this.longtude = longtude;
        this.user_id = user_id;
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtude() {
        return this.longtude;
    }

    public void setLongtude(double longtude) {
        this.longtude = longtude;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}




