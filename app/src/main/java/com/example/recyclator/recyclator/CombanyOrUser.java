package com.example.recyclator.recyclator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CombanyOrUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combany_or_user);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.userimageButton)
    void goToSetLocationActivity() {
        Intent intent = new Intent(this, SetLocation.class);
        startActivity(intent);
    }

    @OnClick(R.id.companyimageButton)
    void goToSignInActivity() {
        Intent intent1 = new Intent(this, SignInActivity.class);
        startActivity(intent1);
    }

}
