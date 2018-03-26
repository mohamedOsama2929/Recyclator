package com.example.recyclator.recyclator.signIn;

/**
 * Created by amr on 3/26/18.
 */

public class SignInPresenter implements ISignInContract.ISignInPresenter, ISignInContract.ISignModel.onLoginFinishedListener {

    ISignInContract.ISignInView mISignInView;
    ISignInContract.ISignModel mISignInModel;

    public SignInPresenter(ISignInContract.ISignInView mISignInView){
        this.mISignInView = mISignInView;
        mISignInModel = new SignInModel();
    }

    @Override
    public void validateCred(String username, String password) {

        if (mISignInView != null) {
            mISignInView.showProgress();
            mISignInModel.login(username, password, this);
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
