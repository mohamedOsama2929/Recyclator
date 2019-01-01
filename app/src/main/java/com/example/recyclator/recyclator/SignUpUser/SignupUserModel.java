package com.example.recyclator.recyclator.SignUpUser;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupUserModel implements ISignUpUserContract.IsignUpUserModel {

    String url = "https://desolate-chamber-62168.herokuapp.com/public/signup";

    @Override
    public void Signup(Context context, String username, String lastName, String password, String email, String phone, final onSignupUserFinishedListener listener) {

        if (TextUtils.isEmpty(username)) {
            listener.onUserNameError();
            return;
        } else if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            return;
        } else if (TextUtils.isEmpty(email)) {
            listener.onEmailError();
            return;
        } else if (TextUtils.isEmpty(phone)) {
            listener.onPhoneError();
            return;
        } else if (TextUtils.isEmpty(lastName)) {
            listener.onUserLastNameError();
            return;
        } else if (username != null && password != null && email != null && phone != null && lastName != null) {

            Map<String, String> params = new HashMap();


            params.put("FirstName", username);
            params.put("LastName", lastName);
            params.put("Email", email);
            params.put("Phone", phone);
            params.put("Password", password);
            params.put("Image", "k.jpg");
            params.put("district", "mahalla");

            JSONObject parameters = new JSONObject(params);

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.i("Post Response", "onResponse: " + response.toString());

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

        } else {
            listener.onFailure("invalid credentials!");
        }
    }
}
