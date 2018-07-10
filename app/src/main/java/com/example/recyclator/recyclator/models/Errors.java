package com.example.recyclator.recyclator.models;

/**
 * Created by A7med on 23-Jun-18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Errors {

    @SerializedName("Name")
    @Expose
    private List<String> name;
    @SerializedName("quantity")
    @Expose
    private List<String> quantity;
    @SerializedName("User_ID")
    @Expose
    private List<String> userID;
    @SerializedName("Material_ID")
    @Expose
    private List<String> materialID;
    @SerializedName("Image")
    @Expose
    private List<String> image;

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<String> quantity) {
        this.quantity = quantity;
    }

    public List<String> getUserID() {
        return userID;
    }

    public void setUserID(List<String> userID) {
        this.userID = userID;
    }

    public List<String> getMaterialID() {
        return materialID;
    }

    public void setMaterialID(List<String> materialID) {
        this.materialID = materialID;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

}
