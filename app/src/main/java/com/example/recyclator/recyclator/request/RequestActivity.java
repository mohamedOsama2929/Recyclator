package com.example.recyclator.recyclator.request;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.recyclator.recyclator.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class RequestActivity extends AppCompatActivity implements IRequestContract.IRequestView {


    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.progressBarRequest)
    ProgressBar ProgressBarRequest;
    //private RecyclerView recyclerView;
    //private RequestAdapter requestAdapter;
    //private ArrayList<Request> requests;

    private IRequestContract.IRequestPresenter mRequestPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);
        ButterKnife.bind(this);

        mRequestPresenter = new RequestPresenter(this);

        mRequestPresenter.getRequests(recyclerView);

        /*
        requests = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            requests.add(new Request("" + i, " " + i, " " + i, " " + i, "" + i));
        }

        requestAdapter = new RequestAdapter(this, requests);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(requestAdapter);
        */
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
        StyleableToast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT,R.style.mytoast).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mRequestPresenter.onDestroy();
    }
/*
    public void print(View view){
        StyleableToast.makeText(getApplicationContext(),"Accept", Toast.LENGTH_SHORT,R.style.mytoast).show();
    }
*/
}
