package com.example.recyclator.recyclator;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.AvoidType;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.PolylineOptions;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
        , LocationListener {


    static boolean mLocationPermissionGranted;
    LocationManager locationManager;
    String provider;
    Location mLastKnownLocation;
    Location location;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.i("loc", "mMap ready ");

        updateLocationUI();


        getDeviceLocation();

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
    public void onLocationChanged(Location location) {
        /*

        mMap.clear();
        double lat = location.getLatitude();
        double lng = location.getLongitude();

        LatLng sydney = new LatLng(lat, lng);
        Marker marker=mMap.addMarker(new MarkerOptions().position(sydney).title("iam here"));
        animateMarker(marker,sydney,false);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("iam here"));
         mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 50f));

        Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addressList=geocoder.getFromLocation(lat,lng,1);
            if (addressList!=null&&addressList.size()>0){
                Log.i("loc", addressList.get(0).toString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {


    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public void animateMarker(final Marker marker, final LatLng toPosition,
                              final boolean hideMarker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = mMap.getProjection();
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 500;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * toPosition.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                } else {
                    if (hideMarker) {
                        marker.setVisible(false);
                    } else {
                        marker.setVisible(true);
                    }
                }
            }
        });
    }

    private void getLocationPermission() {

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i("loc", "getLocationPermission:  permisssion exist ");
            mLocationPermissionGranted = true;
            checkGPSEnable();
            updateLocationUI();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                    checkGPSEnable();

                }
            }
        }
        updateLocationUI();
    }


    private void updateLocationUI() {
        if (mMap == null) {
            Log.i("loc", "mMap equl null: ");
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                Log.i("loc", "mlocation permission true: ");
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);


            } else {
                Log.i("loc", "mlocation permissoin false: ");
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                location = null;
                getLocationPermission();


            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @SuppressLint("MissingPermission")
    private void getDeviceLocation() {
        if (mLocationPermissionGranted) {


            Log.i("loc", "getDeviceLocation: ");

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            provider = locationManager.getBestProvider(new Criteria(), false);
            Log.i("loc", "provider :" + provider);

            location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                StyleableToast.makeText(getApplicationContext(), "mLastKnownLocation", Toast.LENGTH_LONG, R.style.mytoast).show();
            } else {
                StyleableToast.makeText(getApplicationContext(), "no mLastKnownLocation", Toast.LENGTH_LONG, R.style.mytoast).show();

            }
            if (location != null) {
                double lat = location.getLatitude();
                double lng = location.getLongitude();
                getDirection(lat, lng);
                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(lat, lng);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 50f));
            }
            locationManager.requestLocationUpdates(provider, 400, 1, this);

        }
    }


    @SuppressLint("MissingPermission")
    void checkGPSEnable() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        assert locationManager != null;
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            StyleableToast.makeText(this, "GPS is Enabled in your Device", Toast.LENGTH_SHORT, R.style.mytoast).show();
        } else {
            showGpsDisabledAlertToUser();
        }
    }

    private void showGpsDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Gps is Disabled in your Device would you like to enable Gps?")
                .setCancelable(false)
                .setPositiveButton("Goto Setting Page To Enable Gps", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(callGPSSettingIntent);
                    }
                });
        alertDialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    void getDirection(double lat, double lng) {

        GoogleDirection.withServerKey(getResources().getString(R.string.google_maps_key))
                .from(new LatLng(lat, lng))
                .to(new LatLng(31.042282, 31.352055))
                .avoid(AvoidType.FERRIES)
                .avoid(AvoidType.HIGHWAYS)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        if (direction.isOK()) {
                            // Do something
                            Log.i("loc", "onDirectionSuccess: success ");

                            Leg leg = direction.getRouteList().get(0).getLegList().get(0);
                            ArrayList<LatLng> directionPositionList = leg.getDirectionPoint();
                            PolylineOptions polygonOptions = DirectionConverter.createPolyline(MapsActivity.this, directionPositionList, 5, Color.RED);
                            mMap.addPolyline(polygonOptions);


                        } else {
                            // Do something
                            Log.i("loc", "onDirectionSuccess: no ");
                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {
                        // Do something
                        Log.i("loc", "onDirectionFailure: ");
                    }
                });

    }


}
