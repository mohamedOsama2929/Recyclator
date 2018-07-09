package com.example.recyclator.recyclator.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recyclator.recyclator.R;
import com.example.recyclator.recyclator.placepicActvity;

import butterknife.ButterKnife;
import butterknife.OnClick;

//implement it's own interface to use it's method
public class SignUpActivity extends AppCompatActivity implements ISignUpContract.IsignUpView {

    private EditText txtMailEdit, txtPasswordEdit, txtCompanyNameEdit, txtLocationEdit, txtPhoneNumberEdit;
    private Button btnSignUp;

    private ISignUpContract.IsignUpPresenter mSignUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mSignUpPresenter = new SignUpPresenter(this);

        txtCompanyNameEdit = (EditText) findViewById(R.id.txtUserMailEdit);
        txtPasswordEdit = (EditText) findViewById(R.id.txtUserPasswordEdit);
        txtMailEdit = (EditText) findViewById(R.id.txtSignin);
        txtPhoneNumberEdit = (EditText) findViewById(R.id.txtHaveAccount);
        txtLocationEdit = (EditText) findViewById(R.id.txtSignup);

        btnSignUp = (Button) findViewById(R.id.btnSignup);
    }

    @OnClick(R.id.btnSignup)
    void gotoMap() {
        mSignUpPresenter.validateCred(this,txtCompanyNameEdit.getText().toString().trim(),
                txtPasswordEdit.getText().toString().trim(), txtMailEdit.getText().toString().trim(),
                txtPhoneNumberEdit.getText().toString().trim(), txtLocationEdit.getText().toString().trim());

    }
    @OnClick(R.id.txtSignup)
    void  gotofrag(){
        Intent i = new Intent(getApplicationContext(), placepicActvity.class);
        startActivity(i);

    }

    @Override
    public void setUserNameError() {
        txtCompanyNameEdit.setError("Company Name is empty");
    }

    @Override
    public void setPasswordError() {
        txtPasswordEdit.setError("Password Is empty!");

    }

    @Override
    public void setEmailError() {
        txtMailEdit.setError("E-mail is empty!");
    }

    @Override
    public void setPhoneError() {
        txtPhoneNumberEdit.setError("Phone num is empty!");
    }

    @Override
    public void setLocationError() {
        txtLocationEdit.setError("Location is empty!");

    }

    @Override
    public void setSucsses() {

    }
    @Override
    public void showAlert(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSignUpPresenter.onDestroy();
    }


}
