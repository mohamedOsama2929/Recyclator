package com.example.recyclator.recyclator.map;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.recyclator.recyclator.map.IMapContract.IMapPresenter;
import com.example.recyclator.recyclator.map.IMapContract.IMapView;
import com.example.recyclator.recyclator.map.IMapContract.ImapModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapPresenter implements IMapPresenter
        , ImapModel.IPermissonListner
        , ImapModel.IGPSListner
        , ImapModel.IDeviceListner
        , ImapModel.IDirectionListner {

    IMapView mapView;
    ImapModel mapModel;
    Context context;

    public MapPresenter(IMapView mapView, Context context) {
        this.mapView = mapView;
        mapModel = new MapModel();
        this.context = context;
    }

    @Override
    public void requestLocation(Context context, ImapModel.IDeviceListner deviceListner, Resources resources) {
        mapModel.getDeviceLocation(context, this, resources);
    }

    @Override
    public void requestPermission(Context context, Resources resources) {
        mapModel.checkLocationPermission(context, this, resources);
    }

    @Override
    public void setPermisionOk(Context context, Resources resources) {
        mapModel.checkGPSEnable(context, this, this, resources);

    }

    @Override
    public void requestDirection(double mylat, double mylng, double targetLat, double targetLng, final Context context
            , Resources resources, final ImapModel.IDirectionListner directionListner) {

        mapModel.getDirections(mylat, mylng, targetLat, targetLng, context, resources, this);
    }

    @Override
    public void onDestroy() {
        if (mapView != null) {
            mapView = null;
        }

    }

    @Override
    public void permissonAccept(Context context, Resources resources) {
        mapModel.checkGPSEnable(context, this, this, resources);
    }

    @Override
    public void permissionDeny(Context context, Resources resources) {

        mapView.showPermissionDialog();
        mapModel.checkLocationPermission(context, this, resources);
    }

    @Override
    public void gpsProvided(Context context, Resources resources) {
        requestLocation(context, this, resources);
        mapView.showLocationButton();
    }

    @Override
    public void gpsNotPrvided(Context context) {
        mapView.buildGPSAlert();
        mapView.showGPSAlert();
    }

    @Override
    public void successLocation(Context context, LatLng myLocation, Resources resources) {
        mapView.showLocationButton();
        mapView.showLocation(myLocation);
        Log.i("locs", "successLocation: sss ");

        List<LatLng> userLocations = new ArrayList<LatLng>();

        userLocations.add(new LatLng(31.0423043, 31.351929499999983));//حاسبات و معلومات
        userLocations.add(new LatLng(30.97849209999999, 31.17318649999993));//شكري القوتلى
        userLocations.add(new LatLng(30.98009360000001, 31.169607100000007));//محب
        userLocations.add(new LatLng(30.9690276, 31.168711799999983));//محطة قطار المحلة الكبري
        userLocations.add(new LatLng(30.9630125, 31.161517600000025));//سكة طنطا

        for (int i = 0; i < userLocations.size() - 1; i++) {
            requestDirection(userLocations.get(i).latitude, userLocations.get(i).longitude, userLocations.get(i + 1).latitude, userLocations.get(i + 1).longitude, context, resources, this);
        }
    }

    @Override
    public void filureLocation(Context context) {
        mapView.LocationError();
    }

    @Override
    public void successDirection(Context context, PolylineOptions polygonOptions, double targetLat, double targetLng) {
        mapView.showDirections(polygonOptions, targetLat, targetLng);
    }

    @Override
    public void getdistance(double distance) {


    }

    @Override
    public void filureDirection(Context context) {
        mapView.DirectonError();
    }
}
