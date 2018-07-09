package com.example.recyclator.recyclator.SignUpUser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.recyclator.recyclator.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserSignUp extends AppCompatActivity implements ISignUpUserContract.IsignUpUserView {
    private EditText txtMailEdit, txtPasswordEdit, txtUserFirstNameEdit,txtUserLastNameEdit, txtPhoneNumberEdit;
    private Button btnSignUp;
    private ISignUpUserContract.IsignUpUserPresenter misignUpUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        ButterKnife.bind(this);

        misignUpUserPresenter=new SignUPUserPresenter(this);

        txtUserFirstNameEdit = (EditText) findViewById(R.id.txtUserMailEdit);
        txtUserLastNameEdit = (EditText) findViewById(R.id.txtHaveAccount);

        txtPasswordEdit = (EditText) findViewById(R.id.txtUserPasswordEdit);
        txtMailEdit = (EditText) findViewById(R.id.txtSignin);
        txtPhoneNumberEdit = (EditText) findViewById(R.id.txtSignup);

        btnSignUp=(Button) findViewById(R.id.signupuserbtn);
    }
    @OnClick(R.id.signupuserbtn)
    void gotomap(){
        misignUpUserPresenter.validateCred(this,txtUserFirstNameEdit.getText().toString().trim(),
                 txtUserLastNameEdit.getText().toString().trim(),
                txtPasswordEdit.getText().toString().trim(), txtMailEdit.getText().toString().trim(),
                txtPhoneNumberEdit.getText().toString().trim());
    }



    @Override
    public void setUserNameError() {
        txtUserFirstNameEdit.setError("First Name is empty!");

    }

    @Override
    public void setUserlastNameError() {
        txtUserLastNameEdit.setError("Last Name is empty!");

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
    public void setSucsses() {

    }

    @Override
    public void showAlert(String message) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        misignUpUserPresenter.onDestroy();
    }
}
