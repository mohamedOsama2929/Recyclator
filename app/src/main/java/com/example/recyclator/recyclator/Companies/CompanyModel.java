package com.example.recyclator.recyclator.Companies;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CompanyModel implements ICompanyContract.ICompanyModel {

    private static final String TAG = "CompanyModel";
    ;
    String Api = "https://desolate-chamber-62168.herokuapp.com/public";
    String Key = "/search/cairo";
    String url1 = Api + Key;
    private ArrayList<Company> companies = new ArrayList<Company>();
    private RequestQueue mRequestQueue;

    @Override
    public List<Company> downloadCompanies(final onCompanyFinishedListener listener, Context context, String city) {

        mRequestQueue = Volley.newRequestQueue(context);
        String url = url1;
        JsonArrayRequest jo = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listener.onSuccess();
                        ;
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                // Get current json object
                                JSONObject company = response.getJSONObject(i);

                                Log.i("suc", "onResponse: rfgfgfs");
                                // Get the current student (json object) data
                                int id = company.getInt("id");
                                String name = company.getString("Name");
                                String location = company.getString("LocationTarget");
                                double rate = company.getDouble("rate");
                                String bio = company.getString("Bio");
                                String image = company.getString("Image");

                                // Display the formatted json data in text view
                                companies.add(new Company(id, image, rate, name, bio, location));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("suc", "onResponse: errrrrror");
                listener.onFailure("Failed");
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });

        mRequestQueue.add(jo);

        return companies;

    }
}