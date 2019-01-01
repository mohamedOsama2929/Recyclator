package com.example.recyclator.recyclator.cotrollers.network_controllers;

import com.example.recyclator.recyclator.models.History;
import com.example.recyclator.recyclator.models.Material;
import com.example.recyclator.recyclator.models.SendRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by A7med on 23-Jun-18.
 */

public interface Service {


    //server
    String BASE_URL = "https://desolate-chamber-62168.herokuapp.com/public/";

    @GET("material")
    Call<List<Material>> getAllMaterial();

    @GET("user/{id}/approvedrequests")
    Call<List<History>> getApprovedRequests(@Path("id") int userId);

    @FormUrlEncoded
    @POST("request")
    Call<SendRequest> sendRequest(@Field("Image") String Image,
                                  @Field("User_ID") int userID,
                                  @Field("Material_ID") int materialID,
                                  @Field("Name") String name,
                                  @Field("quantity") int quantity,
                                  @Field("SuggetedPrice") int price);

    class Fetcher {
        private static Service service = null;

        public static Service getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(BASE_URL)
                        .build();
                service = retrofit.create(Service.class);
            }
            return service;
        }
    }
}
