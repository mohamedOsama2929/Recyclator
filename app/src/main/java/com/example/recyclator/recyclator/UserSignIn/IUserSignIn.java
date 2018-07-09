package com.example.recyclator.recyclator.UserSignIn;

import android.content.Context;

import com.example.recyclator.recyclator.signIn.ISignInContract;

public interface IUserSignIn {
    interface ISignInView{

        void showProgress();
        void hideProgress();
        void setUserNameError();
        void setPasswordError();
        void navigateToMatin();
        void showAlert(String message);
        void setUserId(String user_id);
    }

    interface ISignInPresenter{

        void validateCred(Context context, String username, String password);
        void onDestroy();

    }

    interface ISignModel{

        void login(Context context, String username, String password, IUserSignIn.ISignModel.onUserLoginFinishedListener listener);


        interface onUserLoginFinishedListener{

            void onUserNameError();
            void onPasswordError();
            void onUsergetId(String id);
            void onSuccess();
            void onFailure(String message);
        }


    }
}
