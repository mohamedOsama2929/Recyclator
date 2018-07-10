package com.example.recyclator.recyclator.setLocation;

import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclator.recyclator.MainActivity;
import com.example.recyclator.recyclator.R;
import com.example.recyclator.recyclator.setLocation.ISetLocationContract.ISetLocationView;
import com.example.recyclator.recyclator.setLocation.ISetLocationContract.ISetlocationPresenter;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

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


    LocationRequest locationRequest;

    int userState = 0;

    private ISetlocationPresenter msetlocationPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
        ButterKnife.bind(this);

        Log.i("loc", "onCreate: ");

        msetlocationPresenter=new SetLocationPresenter(this,this);

        buildGPSAlert();
        // msetlocationPresenter.requestLocationData(this);
        getMyIntent();
    }


    @Override
    public void getMyIntent() {
        Intent i = getIntent();
        userState = i.getIntExtra("userId", 0); //defult guest \
        Log.i("idgetusersignin", "getMyIntent: " + userState);
    }

    @Override
    public void sendMyIntent(int userId) {

    }

    @Override
    public void buildGPSAlert() {

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
    }

    @Override
    public void showGPSAlert() {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                // ...
            }
        });

        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(SetLocationActivity.this,
                                1);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });

    }

    /*
    @Override
    public void hideGPSAlert() {

        alertDialog = alertDialogBuilder.create();
        if (alertDialog.isShowing()){
            Log.i("loc", "hideGPSAlert: dialog is showing ");
            alertDialog.cancel();
            alertDialog.hide();
        }


    }
    */

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
        if (!progressBar.isShown()) {
            Log.i("loc", "showPrgrassBar:  progressBar hiden ");
            progressBar.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void hidePrgrassBar() {

        if (progressBar.isShown()) {
            Log.i("loc", "hidePrgrassBar: progressBar shown  ");
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void setLocationData(String city, String area) {

            txtCity.setText(city);
            txtArea.setText(area);


    }

    @OnClick(R.id.btnShowCompanies)
    @Override
    public void requestNavigateToCompanies() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("userId", userState);
        String city = "cairo";
        i.putExtra("city", city);
        startActivity(i);

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
