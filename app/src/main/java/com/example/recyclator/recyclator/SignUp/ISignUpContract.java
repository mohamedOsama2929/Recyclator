package com.example.recyclator.recyclator.SignUp;

/**
 * Created by dewidar on 3/26/18.
 */

public interface ISignUpContract {

    interface IsignUpView {
        void setUserNameError();

        void setPasswordError();

        void setEmailError();

        void setPhoneError();

        void setLocationError();

        void setSucsses();

        void showAlert(String message);

    }

    interface IsignUpPresenter {
        void validateCred(String username, String password, String email, String phone, String location);
        void onDestroy();

    }

    interface IsignUpModel {
        void Signup(String username, String password, String email, String phone, String location, onSignupFinishedListener listener);

        interface onSignupFinishedListener {

            void onUserNameError();

            void onPasswordError();

            void onEmailError();

            void onPhoneError();

            void onLocationError();

            void onSuccess();

            void onFailure(String message);
        }


    }

}
