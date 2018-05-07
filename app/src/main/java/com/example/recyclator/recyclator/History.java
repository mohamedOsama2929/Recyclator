package com.example.recyclator.recyclator;

public class History {

    String date;
    String company;
    String location;
    String quantity;
    String rate;

    public History(String date, String company, String location, String quantity, String rate) {
        this.date = date;
        this.company = company;
        this.location = location;
        this.quantity = quantity;
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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


