package com.example.recyclator.recyclator.signIn;

import android.text.TextUtils;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by amr on 3/26/18.
 */

public class SignInModel implements ISignInContract.ISignModel {
    @Override
    public void login(String username, String password, onLoginFinishedListener listener) {

        if (TextUtils.isEmpty(username)){
            listener.onUserNameError();
        }
        else if (TextUtils.isEmpty(password)){
            listener.onPasswordError();
        }
        else if (username.equals("amr") && password.equals("123456")){
            listener.onSuccess();
        }
        else{
            listener.onFailure("Invalid Credentials");
        }
    }
}
