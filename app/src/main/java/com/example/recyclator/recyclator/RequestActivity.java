package com.example.recyclator.recyclator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RequestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestAdapter requestAdapter;
    private ArrayList<Request> requests;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        requests = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            requests.add(new Request("" + i, " " + i, " " + i, " " + i, "" + i));
        }
        requestAdapter = new RequestAdapter(this, requests);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(requestAdapter);


    }
}
