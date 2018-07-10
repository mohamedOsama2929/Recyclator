package com.example.recyclator.recyclator.SignUpUser;

import android.content.Context;

public class SignUPUserPresenter implements ISignUpUserContract.IsignUpUserPresenter , ISignUpUserContract.IsignUpUserModel.onSignupUserFinishedListener {

    ISignUpUserContract.IsignUpUserView misignUpUserView;
    ISignUpUserContract.IsignUpUserModel misignUpUserModel;

    public SignUPUserPresenter (ISignUpUserContract.IsignUpUserView misignUpUserView){
        this.misignUpUserView=misignUpUserView;
        misignUpUserModel=new SignupUserModel();

    }

    @Override
    public void validateCred(Context context, String username, String lastName, String password, String email, String phone) {
        misignUpUserModel.Signup(context,username,lastName,password,email,phone,this);

    }

    @Override
    public void onDestroy() {
        if(misignUpUserView !=null){
            misignUpUserView=null;
        }

    }

    @Override
    public void onUserNameError() {
        if (misignUpUserView != null) {
            misignUpUserView.setUserNameError();
        }

    }

    @Override
    public void onUserLastNameError() {
        if (misignUpUserView != null) {
            misignUpUserView.setUserlastNameError();
        }
    }

    @Override
    public void onPasswordError() {
        if (misignUpUserView != null) {
            misignUpUserView.setPasswordError();
        }

    }

    @Override
    public void onEmailError() {
        if (misignUpUserView != null) {
            misignUpUserView.setEmailError();
        }
    }

    @Override
    public void onPhoneError() {
        if (misignUpUserView != null) {
            misignUpUserView.setPhoneError();
        }

    }

    @Override
    public void onSuccess() {
        if (misignUpUserView != null) {
            misignUpUserView.setSucsses();
        }


    }

    @Override
    public void onFailure(String message) {
        if (misignUpUserView != null) {
            misignUpUserView.showAlert(message);
        }

    }
}
