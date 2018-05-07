package com.example.recyclator.recyclator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private List<History> histories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        histories = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            histories.add(new History(" " + i, " " + i, " " + i, " " + i, ""));
        }
        historyAdapter = new HistoryAdapter(this, histories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(historyAdapter);


    }
}