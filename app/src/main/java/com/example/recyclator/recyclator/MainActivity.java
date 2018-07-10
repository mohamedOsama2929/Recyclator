package com.example.recyclator.recyclator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.recyclator.recyclator.Companies.ICompanyContract;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    TextView toolbarTitle;
    TextView t2;

    @BindView(R.id.bottom_bar)
    BottomNavigationView navigation;
    // private RequestQueue requestQueue = Volley.newRequestQueue(this);
//    int id = intent.getIntExtra("userId",0);
//    String city = intent.getStringExtra("city");
    Bundle bundle = new Bundle();
    int userState = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener navigation1 = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.option:
                    switchToFragment1();
                    return true;
                case R.id.home:
                    switchToFragment1();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.content, new HomeFragment()).commit();
                    return true;
                case R.id.history:
                    switchToFragment2();
                    FragmentManager manager1 = getSupportFragmentManager();
                    manager1.beginTransaction().replace(R.id.content, new HistoryFragment()).commit();
                    return true;
                case R.id.tips:
                    switchToFragment3();
                    FragmentManager manager2 = getSupportFragmentManager();
                    manager2.beginTransaction().replace(R.id.content, new TipsFragment()).commit();
                    return true;
            }
            return false;
        }
    };
    //code fathy
    private String url = "https://desolate-chamber-62168.herokuapp.com/public/search";
    private ICompanyContract.ICompanyPresenter mCompanyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        t2 = (TextView) findViewById(R.id.addrec);
        getMyIntent();

        userState = getIntent().getIntExtra("userId", 0);
        bundle.putInt("userId", userState);
        Log.i("idfrom", "onCreate: " + userState);
        bundle.putString("city", "cairo");
        // set Fragmentclass Arguments
        HomeFragment homeFragment = new HomeFragment();
        optionFragment optionFragment = new optionFragment();
        optionFragment.setArguments(bundle);
        homeFragment.setArguments(bundle);


        navigation.setOnNavigationItemSelectedListener(navigation1);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, homeFragment).commit();
        //manager.beginTransaction().replace(R.id.content,optionFragment).commit();

    }

    public int getUserId() {
        return userState;
    }

    public void getMyIntent() {

        Intent i = getIntent();
        userState = i.getIntExtra("userId", 0); //defult guest \
        Log.i("idddd", "getMyIntent: " + userState);
    }


    public void switchToFragment1() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, new optionFragment()).commit();
    }

    public void switchToFragment2() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, new HistoryFragment()).commit();
    }

    public void switchToFragment3() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, new TipsFragment()).commit();
    }


}
