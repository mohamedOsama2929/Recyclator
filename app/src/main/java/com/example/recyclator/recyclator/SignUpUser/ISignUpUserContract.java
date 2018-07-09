package com.example.recyclator.recyclator.SignUpUser;

import android.content.Context;

import com.android.volley.toolbox.StringRequest;

public interface ISignUpUserContract {
    interface IsignUpUserView {
        void setUserNameError();

        void setUserlastNameError();

        void setPasswordError();

        void setEmailError();

        void setPhoneError();

        void setSucsses();

        void showAlert(String message);

    }

    interface IsignUpUserPresenter {
        void validateCred(Context context, String username, String lastName, String password, String email, String phone);

        void onDestroy();

    }

    interface IsignUpUserModel {
        void Signup(Context context, String username, String lastname, String passowrd, String email, String phone, onSignupUserFinishedListener listener);

        interface onSignupUserFinishedListener {

            void onUserNameError();

            void onUserLastNameError();

            void onPasswordError();

            void onEmailError();

            void onPhoneError();

            void onSuccess();

            void onFailure(String message);
        }


    }

}
