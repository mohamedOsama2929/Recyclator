package com.example.recyclator.recyclator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.textView7)
    void goToSignUp() {
        Intent i = new Intent(getApplicationContext(), SignUp.class);
        startActivity(i);
    }
}
