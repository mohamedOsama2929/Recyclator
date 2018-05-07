package com.example.recyclator.recyclator;

public class Request {
    String name;
    String kind;
    String location;
    String quantity;
    String rate;

    public Request(String name, String location, String kind, String quantity, String rate) {
        this.name = name;
        this.kind = kind;
        this.location = location;
        this.quantity = quantity;
        this.rate = rate;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}




