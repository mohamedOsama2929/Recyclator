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
    //url of api to get info using id
    String url="https://desolate-chamber-62168.herokuapp.com/public/companies";
    //method to take context from presenter and id and interface listner with method to excute
    @Override
    public void downloadinfo(Context context, final int id_company, final listner listner) {

        //using volley to download info using id
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        // display response
                        Log.i("Response", response.toString());
                        //take array response and make loop on it  to match diserd id opject and
                        //get all info fowlloing that id

                        for(int i=0;i<response.length();i++){
                            // Get current json object
                            //loop on opjects elemnts of that array
                            //in every opject get the id and compare withe or id
                            //if it match get or info
                            JSONObject comobject = null;
                            try {
                                comobject = response.getJSONObject(i);
                                int ids = comobject.getInt("id");

                                if (ids == id_company) {
                                    Log.i("iii",String.valueOf(i));
                                    //using object number and get it's element
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
                                    Log.i("iii",companyname);

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
