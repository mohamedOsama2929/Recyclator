package com.example.recyclator.recyclator;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recyclator.recyclator.companyprofile.CompanyProfileActivity;
import com.example.recyclator.recyclator.map.MapsActivity;
import com.example.recyclator.recyclator.request.RequestActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompanyOptionActivity extends AppCompatActivity {

    int company_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_options_company);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        company_id = intent.getIntExtra("EXTRA_SESSION_ID", 0);

    }

    @OnClick(R.id.profileCompany)
    void goToProfile() {

        Intent intent = new Intent(this, CompanyProfileActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", company_id);
        startActivity(intent);
    }

    @OnClick(R.id.requestsCompany)
    void goToRequests() {

        Intent intent = new Intent(this, RequestActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", company_id);
        startActivity(intent);
    }

    @OnClick(R.id.mapCompany)
    void goToMap() {

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", company_id);
        startActivity(intent);
    }

    @OnClick(R.id.logoutCompany)
    void goToLogOut() {
/*
        Intent intent = new Intent(this, CompanyProfileActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", company_id);
        startActivity(intent);
*/
    }
}
