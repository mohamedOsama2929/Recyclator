package com.example.recyclator.recyclator;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclator.recyclator.models.History;

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

        historyAdapter = new HistoryAdapter(this, histories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(historyAdapter);


    }
}