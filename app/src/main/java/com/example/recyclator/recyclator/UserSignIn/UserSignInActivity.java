package com.example.recyclator.recyclator.UserSignIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.recyclator.recyclator.R;
import com.example.recyclator.recyclator.SignUpUser.UserSignUp;
import com.example.recyclator.recyclator.setLocation.SetLocationActivity;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserSignInActivity extends AppCompatActivity implements IUserSignIn.ISignInView {
    private EditText txtMailEdit, txtPasswordEdit;
    private Button btnSignin;
    private ProgressBar progressBarSignIn;

    private IUserSignIn.ISignInPresenter mSignInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_in);
        ButterKnife.bind(this);
        mSignInPresenter = new UserSignInPresenter(this);

        txtMailEdit = (EditText) findViewById(R.id.txtUserMailEdit);
        txtPasswordEdit = (EditText) findViewById(R.id.txtUserPasswordEdit);
        btnSignin = (Button) findViewById(R.id.btnSigninuser);
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
        //    startActivity(new Intent(UserSignInActivity.this,SetLocationActivity.class));
    }

    @Override
    public void showAlert(String message) {
        StyleableToast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT,R.style.mytoast).show();
    }

    @Override
    public void setUserId(int user_id) {
        Intent intent = new Intent(getBaseContext(), SetLocationActivity.class);
        intent.putExtra("userId", user_id);
        Log.i("idfromsignin", "id  " + user_id);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSignInPresenter.onDestroy();
    }

    //------------------------------------------------------------------------------------------------------------

    @OnClick(R.id.txtSignup)
    void goToSignUp() {
        Intent i = new Intent(getApplicationContext(), UserSignUp.class);
        startActivity(i);
    }

    @OnClick(R.id.btnSigninuser)
    void goTOMap(){

        mSignInPresenter.validateCred(this,txtMailEdit.getText().toString().trim(), txtPasswordEdit.getText().toString().trim());
    }
}
