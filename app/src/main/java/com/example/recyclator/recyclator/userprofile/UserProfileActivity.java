package com.example.recyclator.recyclator.userprofile;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.recyclator.recyclator.R;

import butterknife.ButterKnife;

public class UserProfileActivity extends AppCompatActivity implements IuserprofileContract.IuserprofileView {
  private  TextView username,userlocation,userEmail,userNumber;
   private RatingBar userRating;
   private ImageView imageView;

 private  IuserprofileContract.IuserprofilePresente muserprofilePresente;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        ButterKnife.bind(this);

        muserprofilePresente=new userproflePresenter(this);


        username=(TextView) findViewById(R.id.user);
        imageView=(ImageView)findViewById(R.id.iv) ;
        userlocation=(TextView) findViewById(R.id.location);
        userEmail=(TextView) findViewById(R.id.useremail);
        userNumber=(TextView) findViewById(R.id.usernumber);
        userRating=(RatingBar) findViewById(R.id.userrate);

        int s = getIntent().getIntExtra("userId", 0);
        Log.i("idfrom", "onCreate: " + s);

        muserprofilePresente.callprese(this,s);

    }


    @Override
    public void setuserImage(Uri image) {
        imageView.setImageURI(image);

    }

    @Override
    public void setuserName(String name) {
        username.setText(name);
    }

    @Override
    public void setuserLocation(String location) {

        userlocation.setText(location);
    }
    @Override
    public void setuserEmail(String email) {
        userEmail.setText(email);
    }

    @Override
    public void setuserNumber(String number) {
        userNumber.setText(number);

    }

    @Override
    public void setuserRate(int Rate) {
        userRating.setRating(Rate);

    }
}
