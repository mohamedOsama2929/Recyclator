package com.example.recyclator.recyclator.SignUp;

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

/**
 * Created by dewidar on 3/26/18.
 */

public class SignUpModel implements ISignUpContract.IsignUpModel {

    String url="https://desolate-chamber-62168.herokuapp.com/public/company/signup";

    @Override
    public void Signup(Context context, final String username, final String password, final String email, final String phone, final String location, final onSignupFinishedListener listener) {

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
        } else if (TextUtils.isEmpty(location)) {
            listener.onLocationError();
            return;
        } else if(username != null   && password !=null && email !=null && phone !=null && location !=null) {

            Map<String, String> params = new HashMap();

            params.put("Name", username);
            params.put("Bio", "biobb");
            params.put("Email", email);
            params.put("Phone", phone);
            params.put("Password", password);
            params.put("Image", "k.jpg");
            params.put("district", "mahalla");
            params.put("LocationTarget", location);

            JSONObject param = new JSONObject(params);

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, param, new Response.Listener<JSONObject>() {
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
        }
        else {
            listener.onFailure("invalid credentials!");
        }

    }
}
