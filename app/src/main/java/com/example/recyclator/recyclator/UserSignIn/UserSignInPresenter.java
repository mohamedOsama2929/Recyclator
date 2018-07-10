package com.example.recyclator.recyclator.UserSignIn;

import android.content.Context;

public class UserSignInPresenter implements IUserSignIn.ISignInPresenter,IUserSignIn.ISignModel.onUserLoginFinishedListener {
    IUserSignIn.ISignInView mISignInView;
    IUserSignIn.ISignModel mISignInModel;

    public UserSignInPresenter(IUserSignIn.ISignInView mISignInView){
        this.mISignInView = mISignInView;
        mISignInModel = new UserSignInModel();
    }

    @Override
    public void validateCred(Context context, String username, String password) {

        if (mISignInView != null) {
            mISignInView.showProgress();
            mISignInModel.login(context,username, password,this);
        }
    }

    @Override
    public void onDestroy() {

        if (mISignInView != null) {
            mISignInView = null;
        }

    }

    @Override
    public void onUserNameError() {

        if (mISignInView != null) {
            mISignInView.hideProgress();
            mISignInView.setUserNameError();
        }
    }

    @Override
    public void onPasswordError() {

        if (mISignInView != null) {
            mISignInView.hideProgress();
            mISignInView.setPasswordError();
        }
    }

    @Override
    public void onUsergetId(int id) {
        if (mISignInView != null) {
            mISignInView.setUserId(id);
        }
        }

    @Override
    public void onSuccess() {

        if (mISignInView != null) {
            mISignInView.hideProgress();
            mISignInView.navigateToMatin();
        }
    }

    @Override
    public void onFailure(String message) {

        if (mISignInView != null) {
            mISignInView.hideProgress();
            mISignInView.showAlert(message);
        }
    }
}
