package com.example.recyclator.recyclator.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.AvoidType;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Info;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.example.recyclator.recyclator.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;


public class MapModel implements IMapContract.ImapModel {

    static boolean mLocationPermissionGranted;
    LocationManager locationManager;
    String provider;
    Location location;

    @Override
    public void checkLocationPermission(Context context, IPermissonListner permissonListner, Resources resources) {

        mLocationPermissionGranted = false;

        if (ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i("locs", "getLocationPermission:  permisssion exist ");
            mLocationPermissionGranted = true;
            permissonListner.permissonAccept(context, resources);

        } else {
            permissonListner.permissionDeny(context, resources);
        }
    }

    @Override
    public void checkGPSEnable(Context context, IGPSListner gpsListner, IDeviceListner deviceListner, Resources resources) {

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Log.i("locs", "checkGPSEnable: GPS Provided ");
            gpsListner.gpsProvided(context, resources);
            //getDeviceLocation(context,deviceListner); //go to method get location and get the location information
        } else {
            Log.i("locs", "checkGPSEnable: GPS Not Provided ");
            gpsListner.gpsNotPrvided(context);
        }
    }

    @Override
    public void getDeviceLocation(Context context, IDeviceListner deviceListner, Resources resources) {

        if (mLocationPermissionGranted) {

            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            provider = locationManager.getBestProvider(new Criteria(), false);
            Log.i("locs", "provider :" + provider);

            if (ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                double lat = location.getLatitude();
                double lng = location.getLongitude();
                Log.i("locs", "getDeviceLocation: " + lat);
                // Add a marker in Sydney and move the camera
                LatLng myLocation = new LatLng(lat, lng);
                if (myLocation != null)
                    deviceListner.successLocation(context, myLocation, resources);
            }
        }
    }

    @Override
    public void getDirections(double mylat, double mylng, final double targetLat, final double targetLng, final Context context
            , Resources resources, final IDirectionListner directionListner) {

        Log.i("locs", "getDirections: Direction enable");
        GoogleDirection.withServerKey(resources.getString(R.string.google_maps_key))
                .from(new LatLng(mylat, mylng))
                .to(new LatLng(targetLat, targetLng))
                .transportMode(TransportMode.DRIVING)
                .avoid(AvoidType.FERRIES)
                .avoid(AvoidType.HIGHWAYS)
                .alternativeRoute(true)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        Log.i("loc", "onDirectionSuccess: " + direction.getStatus());
                        if (direction.isOK()) {
                            // Do something
                            Log.i("loc", "onDirectionSuccess: success ");
                            Leg leg = direction.getRouteList().get(0).getLegList().get(0);

                            Info distanceInfo = leg.getDistance();
                            double distance = Double.parseDouble(distanceInfo.getValue());
                            directionListner.getdistance(distance);

                            Log.i("loc", "onDirectionSuccess: " + distance);
                            List<Step> stepList = direction.getRouteList().get(0).getLegList().get(0).getStepList();
                            ArrayList<PolylineOptions> polylineOptionList = DirectionConverter.createTransitPolyline(context, stepList, 5, Color.RED, 3, Color.BLUE);
                            for (PolylineOptions polylineOption : polylineOptionList) {
                                directionListner.successDirection(context, polylineOption, targetLat, targetLng);
                            }


                        } else {
                            // Do something
                            Log.i("loc", "onDirectionSuccess: no ");
                            directionListner.filureDirection(context);

                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {
                        // Do something
                        Log.i("loc", "onDirectionFailure: ");
                        directionListner.filureDirection(context);
                    }
                });

    }
}
