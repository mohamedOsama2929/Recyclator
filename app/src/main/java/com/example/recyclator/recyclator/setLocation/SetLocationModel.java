package com.example.recyclator.recyclator.setLocation;

/**
 * Created by osos on 3/20/18.
 */

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.recyclator.recyclator.setLocation.ISetLocationContract.ISetLocationModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetLocationModel implements ISetLocationModel{

    LocationManager locationManager;
    String provider;
    Location location;


    @Override
    public void checkLocationPermission(Context context, IPermissonListner listner) {

        if (ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i("loc", "getLocationPermission:  permisssion exist ");

            listner.permissonAccept(context);


        } else {

            listner.permissionDeny(context);

        }

    }

    @Override
    public void checkGPSEnable(Context context, IGPSListner listner, ILocationListner locationListner) {

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Log.i("loc", "checkGPSEnable: GPS Provided ");
            listner.gpsProvided(context);
            getLocationData(context, locationListner);


            // gpsEnabled = true;
        } else {

            Log.i("loc", "checkGPSEnable: GPS Not Provided ");

            listner.gpsNotPrvided(context);

           // gpsEnabled = false;
        }
    }

    @Override
    public void getLocationData(final Context context, final ILocationListner listner) {


        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            Log.i("loc", "getLocationData: gps and permission malk4 7ega ya 3rs  ");

            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            provider = locationManager.getBestProvider(new Criteria(), true);
            Log.i("loc", "provider :" + provider);

            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 400, 1, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Log.i("loc", "onLocationChanged:  request location");

                    locationManager.removeUpdates(this);
                    double lat = location.getLatitude();
                    double lng = location.getLongitude();
                    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(lat, lng, 1);
                        if (addressList != null && addressList.size() > 0) {
                            String address=addressList.get(0).toString();
                            // Log.i("loc", address);
                            Pattern pattern=Pattern.compile("0:\"(.*?)\"");
                            Matcher matcher=pattern.matcher(address);

                            while (matcher.find()){
                                String addr=matcher.group(1);
                                String[]add=addr.split(",");

                                Log.i("loc",add[2]);

                                listner.onSuccess(add[2], add[1]);
                            }

                        } else {

                            listner.noLocationData();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();

                        listner.onFailure();
                    }


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
            });


            if (location!=null){

                double lat = location.getLatitude();
                double lng = location.getLongitude();

                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                try {
                    List<Address> addressList = geocoder.getFromLocation(lat, lng, 1);
                    if (addressList != null && addressList.size() > 0) {
                        String address=addressList.get(0).toString();
                        // Log.i("loc", address);
                        Pattern pattern=Pattern.compile("0:\"(.*?)\"");
                        Matcher matcher=pattern.matcher(address);

                        while (matcher.find()){
                            String addr=matcher.group(1);
                            String[]add=addr.split(",");

                            Log.i("loc",add[2]);

                            listner.onSuccess(add[2], add[1]);
                        }

                    } else {

                        listner.noLocationData();
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                    listner.onFailure();
                }


            } else {

                // location = null
            }


        } else {

            listner.onFailure();
            //gps not enable

        }


    }

}
