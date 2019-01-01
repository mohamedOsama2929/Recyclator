package com.example.recyclator.recyclator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.recyclator.recyclator.UserSignIn.UserSignInActivity;
import com.example.recyclator.recyclator.signIn.SignInActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CombanyOrUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combany_or_user);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.userimageButton)
    void goToSetLocationActivity() {
        Intent intent = new Intent(this, UserSignInActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.companyimageButton)
    void goToSignInActivity() {
        Intent intent1 = new Intent(this, SignInActivity.class);
        startActivity(intent1);
    }

}
