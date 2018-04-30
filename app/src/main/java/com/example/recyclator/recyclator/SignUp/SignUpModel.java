package com.example.recyclator.recyclator.SignUp;

import android.text.TextUtils;

/**
 * Created by dewidar on 3/26/18.
 */

public class SignUpModel implements ISignUpContract.IsignUpModel {
    @Override
    public void Signup(String username, String password, String email, String phone, String location, onSignupFinishedListener listener) {
        if (TextUtils.isEmpty(username)) {
            listener.onUserNameError();
        } else if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
        } else if (TextUtils.isEmpty(email)) {
            listener.onEmailError();
        } else if (TextUtils.isEmpty(phone)) {
            listener.onPhoneError();
        } else if (TextUtils.isEmpty(location)) {
            listener.onLocationError();
        } else if (username.equals("dewa") && password.equals("1211")) {
            listener.onSuccess();
        } else {
            listener.onFailure("Invalid Credentials");
        }
    }
}
