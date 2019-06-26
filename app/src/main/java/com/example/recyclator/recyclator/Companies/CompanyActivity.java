package com.example.recyclator.recyclator.Companies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyclator.recyclator.R;
import com.example.recyclator.recyclator.uploadTrashActivity;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompanyActivity extends AppCompatActivity implements ICompanyContract.ICompanyView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBarCompany)
    ProgressBar ProgressBarRequest;
    Intent intent = getIntent();
    int id = intent.getIntExtra("userId", 0);
    String city = intent.getStringExtra("city");
    private String url = "https://desolate-chamber-62168.herokuapp.com/public/search";
    private RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
    private ICompanyContract.ICompanyPresenter mCompanyPresenter;

    @OnClick(R.id.addrec)
    public void upload(View view) {
        Intent in = new Intent(getApplicationContext(), uploadTrashActivity.class);
        startActivity(in);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_companies);

        ButterKnife.bind(this);


        if (city == null) {
            city = "cairo";
        }

        postcity();

        mCompanyPresenter = new Companypresenter(this);

        mCompanyPresenter.getCompanies(recyclerView, this, city);

    }

    @Override
    public void showProgress() {

        ProgressBarRequest.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        ProgressBarRequest.setVisibility(View.GONE);
    }


    @Override
    public void showAlert(String message) {
        StyleableToast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT, R.style.mytoast).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompanyPresenter.onDestroy();
    }

    void postcity() {

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", "error");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("LocationTarget", city);

                return params;
            }
        };
        requestQueue.add(postRequest);
    }
}
