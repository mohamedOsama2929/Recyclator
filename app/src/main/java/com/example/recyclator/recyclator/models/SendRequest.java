package com.example.recyclator.recyclator.models;

/**
 * Created by A7med on 23-Jun-18.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendRequest {

    @SerializedName("errors")
    @Expose
    private Errors errors;
    @SerializedName("status")
    @Expose
    private String status;

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}