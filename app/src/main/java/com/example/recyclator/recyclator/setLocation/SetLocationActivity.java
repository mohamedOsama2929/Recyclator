package com.example.recyclator.recyclator.setLocation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclator.recyclator.R;
import com.example.recyclator.recyclator.setLocation.ISetLocationContract.ISetLocationView;
import com.example.recyclator.recyclator.setLocation.ISetLocationContract.ISetlocationPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SetLocationActivity extends AppCompatActivity implements ISetLocationView {

    @BindView(R.id.txtCityEdit)
    TextView txtCity;
    @BindView(R.id.txtAreaEdit)
     TextView txtArea;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.btnShowCompanies)
    Button navBtn;

    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;


    private ISetlocationPresenter msetlocationPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
        ButterKnife.bind(this);

        Log.i("loc", "onCreate: ");

        msetlocationPresenter=new SetLocationPresenter(this,this);

      //  msetlocationPresenter.requestLocationData(this);

    }


    @Override
    public void buildGPSAlert() {

         alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Gps is Disabled in your Device would you like to enable Gps?")
                .setCancelable(false)
                .setPositiveButton("Goto Setting Page To Enable Gps", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //must at presenter
                        hideGPSAlert();
                        Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(callGPSSettingIntent);

                      //  msetlocationPresenter.gpsDialogClick(getApplicationContext());


                    }
                });
        alertDialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
                hideGPSAlert();
                msetlocationPresenter.gpsDialogCanel(getApplicationContext());

            }
        });
        // alertDialog = alertDialogBuilder.create();
    }

    @Override
    public void showGPSAlert() {

        //Toast.makeText(this, "make GPS Enabled Please ", Toast.LENGTH_SHORT).show();

        buildGPSAlert();
        alertDialog = alertDialogBuilder.create();

        if (!alertDialog.isShowing()){
            Log.i("loc", "showGPSAlert: alertDialog.isShowing ");
            alertDialog.show();
        }


    }

    @Override
    public void hideGPSAlert() {

        buildGPSAlert();
        alertDialog = alertDialogBuilder.create();
        if (alertDialog.isShowing()){
            Log.i("loc", "hideGPSAlert: dialog is showing ");
            alertDialog.cancel();
            alertDialog.hide();
        }


    }

    @Override
    public void showPermissionDialog() {

        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {

       // mLocationPermissionGranted = false;
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    msetlocationPresenter.setPermisionOk(getApplicationContext());

                }else {

                     msetlocationPresenter.requestPermission(getApplicationContext());
                }
            }
        }

    }


    @Override
    public void showPrgrassBar() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePrgrassBar() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setLocationData(String city, String area) {


            txtCity.setText(city);
            txtArea.setText(area);

    }

    @OnClick(R.id.btnShowCompanies)
    @Override
    public void requestNavigateToCompanies() {


    }

    @Override
    public void errorLocationNotFound() {
        Toast.makeText(this, "Location Not Found", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("loc", "onResume:  ");

        msetlocationPresenter.requestLocationData(this);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("loc", "onDestroy: ");
        msetlocationPresenter.onDestroy();

    }

}
