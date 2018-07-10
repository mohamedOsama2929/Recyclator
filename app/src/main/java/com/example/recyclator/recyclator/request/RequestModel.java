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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestModel implements IRequestContract.IRequestModel {

    String url = "http://desolate-chamber-62168.herokuapp.com/public/company/requests";
    private ArrayList<Request> requests;

    @Override
    public List<Request> downloadRequests(final Context context, final onRequestFinishedListener listener, int comp_ID) {

        requests = new ArrayList<>();

        Map<String, Integer> params = new HashMap();
        params.put("id", 1);

        JSONArray parameters = null;
        try {
            parameters = new JSONArray(params);
        } catch (JSONException e) {

        }

        //Log.i("loc", "downloadRequests: params "+parameters.toString());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Method.POST, url, parameters,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.i("Response" ,response.toString());

                        try{
                            // Loop through the array elements

                            for(int i=0 ;i<response.length() ;i++){
                                // Get current json object
                                JSONObject student = response.getJSONObject(i);
                                Log.i("Student", "onResponse: "+student.toString());

                                // Get the current student (json object) data
                                int user_id = student.getInt("User_ID");
                                String name = student.getString("Name");
                                String quantity = student.getInt("quantity") + " Kg";
                                JSONObject pivot = student.getJSONObject("pivot");
                                int company_id = pivot.getInt("company_id");
                                int request_id = pivot.getInt("request_id");
                                String kind = "Plastic";

                                Log.i("userss", "onResponse: " + pivot.toString());
                                //String rate = student.getString("rate");

                                requests.add(new Request(name, "mahalla" + i, kind, quantity, 3, user_id, context));

                            }

                            if (requests.size() != 0){

                                Log.i("Requests ", "downloadRequests: "+requests.size());
                                listener.onSuccess();
                            }
                            else {
                                listener.onFailure("There Are No Requests");
                            }

                        }catch (JSONException e){
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
