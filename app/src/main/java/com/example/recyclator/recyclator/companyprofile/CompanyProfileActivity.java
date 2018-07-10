package com.example.recyclator.recyclator.companyprofile;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.recyclator.recyclator.R.id;
import com.example.recyclator.recyclator.R.layout;
import com.example.recyclator.recyclator.companyprofile.IcompanyprofileContract.IcompanyPresenter;
import com.example.recyclator.recyclator.companyprofile.IcompanyprofileContract.IcompanyView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyProfileActivity extends AppCompatActivity implements IcompanyView {


    @BindView(id.iv)
    ImageView imageView;
    @BindView(id.company)
    TextView coName;
    @BindView(id.desc)
    TextView coDesc;
    @BindView(id.location)
    TextView coLocation;
    @BindView(id.coemail)
    TextView coEmail;
    @BindView(id.conumber)
    TextView coNumber;
    @BindView(id.rate)
    RatingBar coRate;
    @BindView(id.paymentmethod)
    TextView copaymentmethod;
    @BindView(id.requestBtn)
    Button requestBtn;

    @BindView(id.coquantity)
    TextView coQuantity;

    IcompanyPresenter mcompanyPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.company_profile);
        ButterKnife.bind(this);

        this.mcompanyPresenter = new companyprofilePresenter(this);
        int companyId = getIntent().getIntExtra("EXTRA_SESSION_ID", 0);
        int userId = getIntent().getIntExtra("userId", 0);
        Log.i("idAPI", "onCreate: value : " + companyId);

        if (userId > 0) {
            requestBtn.setVisibility(View.VISIBLE);
        } else {
            requestBtn.setVisibility(View.GONE);
        }

        mcompanyPresenter.callprese(this, companyId);

        //Button requestBtn = (Button) findViewById(id.requestBtn);
        //requestBtn.bringToFront();

    }

    @Override
    public void setcoImage(Uri image) {

    }

    @Override
    public void setcoName(String name) {
        this.coName.setText(name);

    }

    @Override
    public void setcoDesc(String desc) {
        this.coDesc.setText(desc);

    }

    @Override
    public void setcoLocation(String location) {
        this.coLocation.setText(location);

    }

    @Override
    public void setcoEmail(String email) {
        this.coEmail.setText(email);

    }

    @Override
    public void setcorNumber(String number) {
        this.coNumber.setText(number);

    }

    @Override
    public void setcoPayment(String payment) {
        this.copaymentmethod.setText(payment);

    }

    @Override
    public void setcoRate(int rate) {
        this.coRate.setRating(rate);
    }

    @Override
    public void setcoQuantity(String quantity) {
        this.coQuantity.setText(quantity);

    }
}
