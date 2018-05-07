package com.example.recyclator.recyclator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
public class MainActivity extends AppCompatActivity {
    TextView toolbarTitle;
    TextView t2;

    @BindView(R.id.bottom_bar)
    BottomNavigationView navigation;
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
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        t2 = (TextView) findViewById(R.id.addrec);


        ButterKnife.bind(this);

       /* Typeface tf=Typeface.createFromAsset(getAssets(),"Roboto-Light_1.ttf");
        Typeface tf2=Typeface.createFromAsset(getAssets(),"Roboto-Regular.ttf");
        toolbarTitle.setTypeface(tf);
        t2.setTypeface(tf2);*/

        navigation.setOnNavigationItemSelectedListener(navigation1);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, new HomeFragment()).commit();
    }

    public void switchToFragment1() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, new optionFragment()).commit();
    }

    public void switchToFragment2() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, new HistoryFragment()).commit();
    }


}
