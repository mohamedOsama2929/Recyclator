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


    //this method check the location permission and send the response to the permissonListner
    @Override
    public void checkLocationPermission(Context context, IPermissonListner permissonListner) {

        if (ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i("loc", "getLocationPermission:  permisssion exist ");

            permissonListner.permissonAccept(context);

        } else {

            permissonListner.permissionDeny(context);

        }

    }

    //check GPS if it enable or not
    @Override
    public void checkGPSEnable(Context context, IGPSListner listner, ILocationListner locationListner) {

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Log.i("loc", "checkGPSEnable: GPS Provided ");
            listner.gpsProvided(context);
            getLocationData(context, locationListner); //go to method get location and get the location information

        } else {

            Log.i("loc", "checkGPSEnable: GPS Not Provided ");
            listner.gpsNotPrvided(context);
        }
    }

    //method that get location information and send it to location listner
    @Override
    public void getLocationData(final Context context, final ILocationListner locationListner) {

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            Log.i("loc", "getLocationData: gps and permission malk4 7ega ");

            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            provider = locationManager.getBestProvider(new Criteria(), true);
            Log.i("loc", "provider :" + provider);

            //use network provider instead of GPS provider
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 400, 1, new LocationListener() {
                //when location changed it is not nessesary at my code but it only for no bug
                //it to make location not null
                @Override
                public void onLocationChanged(Location location) {
                    Log.i("loc", "onLocationChanged:  request location");

                    locationManager.removeUpdates(this);
                    //this is the code that get location information
                    double lat = location.getLatitude();
                    double lng = location.getLongitude();

                    //that get location indormation (city=add[2],area=add[1])
                    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(lat, lng, 1);
                        if (addressList != null && addressList.size() > 0) {
                            String address=addressList.get(0).toString();
                            Log.i("loc", address);
                             /*
                              Address[addressLines=[0:"El-Hag Mahmoud, Al Mahalah Al Kubra (Part 2), Al Mahalah Al Kubra, Gharbia Governorate, Egypt"]
                             ,feature=El-Hag Mahmoud,admin=Gharbia Governorate,sub-admin=Al Mahalah Al Kubra,locality=Al Mahalah Al Kubra (Part 2)
                             ,thoroughfare=El-Hag Mahmoud,postalCode=null,countryCode=EG,countryName=Egypt,hasLatitude=true,latitude=30.949367100000003,hasLongitude=true,longitude=31.158021,
                             phone=null,url=null,extras=null]
                            */
                            //this code for splite the address and get city and area only
                            Pattern pattern=Pattern.compile("0:\"(.*?)\"");
                            Matcher matcher=pattern.matcher(address);

                            while (matcher.find()){
                                String addr=matcher.group(1);
                                String[]add=addr.split(",");
                                Log.i("loc",add[2]);
                                //(city=add[2],area=add[1])
                                locationListner.onSuccess(add[2], add[1]);
                            }

                        } else {

                            //address =null (No Address)
                            locationListner.noLocationData();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();

                        locationListner.onFailure();
                    }


                }

                //not used
                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                //not used
                @Override
                public void onProviderEnabled(String provider) {

                }

                //not used
                @Override
                public void onProviderDisabled(String provider) {

                }
            });


            /*
            //code that get location information but id it do not change at this moment
            if (location!=null){

                double lat = location.getLatitude();
                double lng = location.getLongitude();

                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                try {
                    List<Address> addressList = geocoder.getFromLocation(lat, lng, 1);
                    if (addressList != null && addressList.size() > 0) {
                        String address=addressList.get(0).toString();

                         //Log.i("loc", address);
                         //Address[addressLines=[0:"El-Hag Mahmoud, Al Mahalah Al Kubra (Part 2), Al Mahalah Al Kubra, Gharbia Governorate, Egypt"]
                         //,feature=El-Hag Mahmoud,admin=Gharbia Governorate,sub-admin=Al Mahalah Al Kubra,locality=Al Mahalah Al Kubra (Part 2)
                         //,thoroughfare=El-Hag Mahmoud,postalCode=null,countryCode=EG,countryName=Egypt
                         //,hasLatitude=true,latitude=30.949367100000003,hasLongitude=true,longitude=31.158021,
                        // phone=null,url=null,extras=null]

                        //this code for splite the address and get city and area only
                        Pattern pattern=Pattern.compile("0:\"(.*?)\"");
                        Matcher matcher=pattern.matcher(address);

                        while (matcher.find()){
                            String addr=matcher.group(1);
                            String[]add=addr.split(",");

                            Log.i("loc",add[2]);

                            //(city=add[2],area=add[1])
                            locationListner.onSuccess(add[2], add[1]);
                        }

                    } else {

                        //address =null (No Address)
                        locationListner.noLocationData();
                    }
                } catch (IOException e) {
                    e.printStackTrace();

                    locationListner.onFailure();
                }

            }
            */


        } else {
            locationListner.onFailure();
            //gps not enable

        }


    }

}
