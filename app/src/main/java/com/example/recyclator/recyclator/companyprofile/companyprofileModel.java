package com.example.recyclator.recyclator.companyprofile;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class companyprofileModel implements IcompanyprofileContract.IcompanyModel{
    String url="https://desolate-chamber-62168.herokuapp.com/public/companies";
    @Override
    public void downloadinfo(Context context, final int id_company, final listner listner) {
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        // display response
                        Log.i("Response", response.toString());

                        for(int i=0;i<response.length();i++){
                            // Get current json object

                            JSONObject student = null;
                            try {
                                student = response.getJSONObject(i);
                                int ids = student.getInt("id");

                                if (ids == id_company) {
                                    Log.i("fuck",String.valueOf(i));
                                    JSONObject companyprofile=response.getJSONObject(i);
                                    String companyname=companyprofile.getString("Name");
                                    listner.onconamedownload(companyname);

                                    String companyEmail=companyprofile.getString("Email");
                                    listner.oncoEmaildownloaded(companyEmail);
                                    String companyPhone=companyprofile.getString("Phone");
                                    listner.oncoNumberdownloaded(companyPhone);

                                    String companynumber=companyprofile.getString("Email");
                                    String companylocation=companyprofile.getString("LocationTarget");
                                    listner.oncolocationdownloaded(companylocation);
                                    Log.i("fuck",companyname);

                                }
                                Log.i("ssss",String.valueOf(ids));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            // Get the current student (json object) data
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error.Response", error.toString());
                    }
                }
        );
        Volley.newRequestQueue(context).add(getRequest);



        /*
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null,new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                Log.i("sss", "onResponse: "+ id_company+" " +response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ss", "onErrorResponse: "+error.toString());
            }
        });

        Volley.newRequestQueue(context).add(jsonObjectRequest);
*/

        listner.oncoDescdownloaded("Collect Glass Garbage in tuesday");

        listner.oncoPaymentdownloaded("'Master Card");
        listner.oncoQuantitydownloaded("200 Kg");
        listner.oncoRatedownloaded(5);

    }
}
