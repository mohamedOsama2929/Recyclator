package com.example.recyclator.recyclator.map;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.recyclator.recyclator.R;
import com.example.recyclator.recyclator.map.IMapContract.IMapPresenter;
import com.example.recyclator.recyclator.map.IMapContract.IMapView;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
        , IMapView {

    static boolean mLocationPermissionGranted;
    Location location;
    LocationRequest locationRequest;
    private GoogleMap mMap;

    private IMapPresenter mapPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapPresenter = new MapPresenter(this, this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.i("locs", "mMap ready ");

        mapPresenter.requestPermission(this, getResources());

    }
    @Override
    protected void onResume() {
        super.onResume();
        /*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 400,1, this);
        */
    }

    @Override
    protected void onPause() {
        super.onPause();
        // locationManager.removeUpdates(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mapPresenter.requestPermission(this, getResources());
                }
            }
        }
    }

    @Override
    public void showPermissionDialog() {

        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                1);
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
                Toast.makeText(MapsActivity.this, "location done", Toast.LENGTH_SHORT).show();
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
                        resolvable.startResolutionForResult(MapsActivity.this,
                                1);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });

    }

    @Override
    public void showLocation(LatLng myLocation) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 100f));
    }

    @Override
    public void showLocationButton() {

        if (mMap == null) {
            Log.i("loc", "GoogleMap null: ");
            return;
        }
        try {
            Log.i("loc", "mlocation permission true: ");
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            if (location != null) {
                List<LatLng> userLocations = new ArrayList<LatLng>();

                userLocations.add(new LatLng(30.97849209999999, 31.17318649999993));//شكري القوتلى
                userLocations.add(new LatLng(30.98009360000001, 31.169607100000007));//محب
                userLocations.add(new LatLng(30.9690276, 31.168711799999983));//محطة قطار المحلة الكبري
                userLocations.add(new LatLng(30.9630125, 31.161517600000025));//سكة طنطا
                for (int i = 0; i < userLocations.size() - 1; i++) {
                    mapPresenter.requestDirection(location.getLatitude(), location.getLongitude(), userLocations.get(i).latitude, userLocations.get(i).longitude, this, getResources(), (IMapContract.ImapModel.IDirectionListner) this);
                }
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void hideLocationButton() {

        Log.i("locs", "mlocation permissoin false: ");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        location = null;
    }

    @Override
    public void showDirections(PolylineOptions polygonOptions, double lat, double lng) {

        mMap.addPolyline(polygonOptions);
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)));
    }

    @Override
    public void DirectonError() {
        StyleableToast.makeText(this, "Diretion Error", Toast.LENGTH_SHORT, R.style.mytoast).show();
    }

    @Override
    public void LocationError() {
        StyleableToast.makeText(this, "Location Not Found", Toast.LENGTH_SHORT, R.style.mytoast).show();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        // mapPresenter.requestPermission(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapPresenter.onDestroy();
    }
}
