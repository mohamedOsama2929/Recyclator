package com.example.recyclator.recyclator.Companies;

public class Company {
    int id;
    String image;
    double rate;
    String name;
    String bio;
    String location;


    // String quantity;


    public Company(int id, String image, double rate, String name, String bio, String location) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.bio = bio;
        this.location = location;
        // this.quantity = quantity;
        this.rate = rate;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /*public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
*/
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

}




