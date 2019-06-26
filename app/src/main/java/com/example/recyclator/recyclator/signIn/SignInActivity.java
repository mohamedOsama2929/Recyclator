package com.example.recyclator.recyclator.signIn;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recyclator.recyclator.CompanyOptionActivity;
import com.example.recyclator.recyclator.R;
import com.example.recyclator.recyclator.placepicActvity;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

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

        txtMailEdit = (EditText) findViewById(R.id.txtUserMailEdit);
        txtPasswordEdit = (EditText) findViewById(R.id.txtUserPasswordEdit);
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

        //startActivity(new Intent(SignInActivity.this, CompanyOptionActivity.class));
    }

    @Override
    public void showAlert(String message) {
        StyleableToast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT,R.style.mytoast).show();
    }

    @Override
    public void setId(int company_id) {
        Log.i("idfromsignin", String.valueOf(company_id));
        Intent intent = new Intent(this, CompanyOptionActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", company_id);
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
        Intent i = new Intent(getApplicationContext(), placepicActvity.class);
        startActivity(i);
    }

    @OnClick(R.id.btnSignin)
    void goTOMap(){

        mSignInPresenter.validateCred(this,txtMailEdit.getText().toString().trim(), txtPasswordEdit.getText().toString().trim());
    }

}