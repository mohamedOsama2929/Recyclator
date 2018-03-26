package com.example.recyclator.recyclator.signIn;

/**
 * Created by amr on 3/26/18.
 */

public interface ISignInContract {

    interface ISignInView{

        void showProgress();
        void hideProgress();
        void setUserNameError();
        void setPasswordError();
        void navigateToMatin();
        void showAlert(String message);
    }

    interface ISignInPresenter{

        void validateCred(String username, String password);
        void onDestroy();

    }

    interface ISignModel{

        interface onLoginFinishedListener{

            void onUserNameError();
            void onPasswordError();
            void onSuccess();
            void onFailure(String message);
        }

        void login(String username, String password, onLoginFinishedListener listener);

    }
}
