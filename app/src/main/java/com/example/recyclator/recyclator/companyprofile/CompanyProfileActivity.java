package com.example.recyclator.recyclator.companyprofile;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.recyclator.recyclator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyProfileActivity extends AppCompatActivity implements IcompanyprofileContract.IcompanyView {


    @BindView(R.id.iv)
    ImageView imageView;
    @BindView(R.id.company)
    TextView coName;
    @BindView(R.id.desc)
    TextView coDesc;
    @BindView(R.id.location)
    TextView coLocation;
    @BindView(R.id.coemail)
    TextView coEmail;
    @BindView(R.id.conumber)
    TextView coNumber;
    @BindView(R.id.rate)
    RatingBar coRate;
    @BindView(R.id.paymentmethod)
    TextView copaymentmethod;

    @BindView(R.id.coquantity)
    TextView coQuantity;


    IcompanyprofileContract.IcompanyPresenter mcompanyPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_profile);
        ButterKnife.bind(this);

        mcompanyPresenter = new companyprofilePresenter(this);
        mcompanyPresenter.callprese();

    }

    @Override
    public void setcoImage(Uri image) {

    }

    @Override
    public void setcoName(String name) {
        coName.setText(name);

    }

    @Override
    public void setcoDesc(String desc) {
        coDesc.setText(desc);

    }

    @Override
    public void setcoLocation(String location) {
        coLocation.setText(location);

    }

    @Override
    public void setcoEmail(String email) {
        coEmail.setText(email);

    }

    @Override
    public void setcorNumber(String number) {
        coNumber.setText(number);

    }

    @Override
    public void setcoPayment(String payment) {
        copaymentmethod.setText(payment);

    }

    @Override
    public void setcoRate(int rate) {
        coRate.setRating(rate);

    }

    @Override
    public void setcoQuantity(String quantity) {
        coQuantity.setText(quantity);

    }
}
