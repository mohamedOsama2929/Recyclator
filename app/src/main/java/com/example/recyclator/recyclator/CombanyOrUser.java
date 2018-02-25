package com.example.recyclator.recyclator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CombanyOrUser extends AppCompatActivity {

    ImageButton userImage;
    ImageButton companyImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_combany_or_user);

        final Intent intent = new Intent(this, SetLocation.class);
        final Intent intent1 = new Intent(this, SignInActivity.class);

        userImage = (ImageButton) findViewById(R.id.userimageButton);

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        companyImage = (ImageButton) findViewById(R.id.companyimageButton);

        companyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });




    }
}
