package com.example.recyclator.recyclator.request;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestModel implements IRequestContract.IRequestModel {

    private ArrayList<Request> requests;

    String url = "https://desolate-chamber-62168.herokuapp.com/public/request";

    @Override
    public List<Request> downloadRequests(final Context context, final onRequestFinishedListener listener, final int comp_ID) {

        requests = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.i("Response", response.toString());
                        try {
                            // Loop through the array elements
                            for (int i = 0; i < response.length(); i++) {
                                // Get current json object
                                JSONObject student = response.getJSONObject(i);
                                Log.i("Student", "onResponse: " + student.toString());// Get the current student (json object) data
                                int user_id = student.getInt("User_ID");
                                String name = student.getString("Name");
                                String quantity = student.getInt("quantity") + " Kg";
                                JSONObject users = student.getJSONObject("user");
                                double longtude = users.getDouble("width");
                                double latitude = users.getDouble("height");
                                double rate = users.getDouble("rate");
                                String kind = "Plastic";

                                Log.i("userss", "onResponse: " + users.toString());
                                //String rate = student.getString("rate")
                                requests.add(new Request(name, "mahalla" + i, kind, quantity, rate, user_id, context));
                                if (requests.size() != 0) {
                                    listener.onSuccess();
                                    Log.i("Requests ", "downloadRequests: " + requests.size());

                                } else {
                                    listener.onFailure("There Are No Requests");
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Erroe", error.toString());
                    }
                });

        Volley.newRequestQueue(context).add(jsonArrayRequest);

        return requests;
    }
}
