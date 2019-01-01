package com.example.recyclator.recyclator.UserSignIn;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserSignInModel implements IUserSignIn.ISignModel {
    String url = "https://desolate-chamber-62168.herokuapp.com/public/signin";


    @Override
    public void login(Context context, final String username, final String password, final onUserLoginFinishedListener listener) {
        if (TextUtils.isEmpty(username)) {
            listener.onUserNameError();
            return;
        } else if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            return;
        }
        else if(username !=null && password !=null) {

            Map<String, String> params = new HashMap();
            params.put("Email", username);
            params.put("Password", password);


            JSONObject parameters = new JSONObject(params);

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    String jsonObject = response.optString("id").toString();
                    int t = Integer.parseInt(jsonObject);
                    Log.i("Post Response", "onResponse: " + jsonObject + response.toString());
                    listener.onUsergetId(t);

                    //Iterate the jsonArray and print the info of JSONObjects
                    listener.onSuccess();
                    //TODO: handle success
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Post Response", "onRefuse: " + error.toString());
                    error.printStackTrace();
                    //TODO: handle failure
                }
            });

            Volley.newRequestQueue(context).add(jsonRequest);
        }else {
            listener.onFailure("invalid cradintls");
        }
    }
}


