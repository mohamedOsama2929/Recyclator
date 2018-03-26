package com.example.recyclator.recyclator.signIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.recyclator.recyclator.MapsActivity;
import com.example.recyclator.recyclator.R;
import com.example.recyclator.recyclator.SignUp;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements ISignInContract.ISignInView{

    private EditText txtMailEdit, txtPasswordEdit;
    private Button btnSignin;
    private ProgressBar progressBarSignIn;

    private ISignInContract.ISignInPresenter mSignInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        mSignInPresenter = new SignInPresenter(this);

        txtMailEdit = (EditText) findViewById(R.id.txtMailEdit);
        txtPasswordEdit = (EditText) findViewById(R.id.txtPasswordEdit);
        btnSignin = (Button) findViewById(R.id.btnSignin);
        progressBarSignIn = (ProgressBar) findViewById(R.id.progressBarSignIn);

    }

    @Override
    public void showProgress() {
        progressBarSignIn.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBarSignIn.setVisibility(View.GONE);
    }

    @Override
    public void setUserNameError() {
        txtMailEdit.setError("UserName Is Empty");
    }

    @Override
    public void setPasswordError() {
        txtPasswordEdit.setError("Password Is Empty");
    }

    @Override
    public void navigateToMatin() {
        startActivity(new Intent(SignInActivity.this,MapsActivity.class));
    }

    @Override
    public void showAlert(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSignInPresenter.onDestroy();
    }

    //------------------------------------------------------------------------------------------------------------

    @OnClick(R.id.txtSignup)
    void goToSignUp() {
        Intent i = new Intent(getApplicationContext(), SignUp.class);
        startActivity(i);
    }

    @OnClick(R.id.btnSignin)
    void goTOMap(){

        mSignInPresenter.validateCred(txtMailEdit.getText().toString().trim(), txtPasswordEdit.getText().toString().trim());
    }

}
