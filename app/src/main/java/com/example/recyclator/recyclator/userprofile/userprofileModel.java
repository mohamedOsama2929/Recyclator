package com.example.recyclator.recyclator.userprofile;

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

public class userprofileModel implements IuserprofileContract.IuserprofileModel {


    String url="https://desolate-chamber-62168.herokuapp.com/public/users";
    @Override
    public void downloadnameema(Context context, final int user_id, final listner listner) {

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

                                if (ids == user_id) {
                                    Log.i("fuck",String.valueOf(i));
                                    JSONObject companyprofile=response.getJSONObject(i);
                                    String companyname=companyprofile.getString("FirstName");
                                    String companylastname=companyprofile.getString("LastName");
                                    String username=new StringBuilder()
                                            .append(companyname)
                                            .append(companylastname)
                                            .toString();
                                    listner.onusernamedownload(username);
                                    String companyPhone=companyprofile.getString("Phone");
                                    listner.onuserNumberdownloaded(companyPhone);

                                    String companyEmail=companyprofile.getString("Email");
                                    listner.onuserEmaildownloaded(companyEmail);
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

        listner.onuserRatedownloaded(5);

    }
/*
        postRequest =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("usersinfo", "onResponse: "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ss", "onErrorResponse: "+error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<String, String>();
                map.put("id","id here");

                return map;
            }
        };
        Volley.newRequestQueue(context).add(postRequest);

        listner.onuserEmaildownloaded("dewa@gmail.com");
        listner.onusernamedownload("M.dewidar");
        listner.onuserlocationdownloaded("elmahla sh,eltr3a");
        listner.onuserNumberdownloaded("01127387443");
        listner.onuserRatedownloaded(5);
    }  */
}
