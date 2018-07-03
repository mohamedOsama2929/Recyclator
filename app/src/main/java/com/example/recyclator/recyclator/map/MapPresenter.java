package com.example.recyclator.recyclator.map;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.akexorcist.googledirection.model.Leg;
import com.example.recyclator.recyclator.map.IMapContract.IMapPresenter;
import com.example.recyclator.recyclator.map.IMapContract.IMapView;
import com.example.recyclator.recyclator.map.IMapContract.ImapModel;
import com.google.android.gms.maps.model.LatLng;

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
    public void requestLocation(Context context, ImapModel.IDeviceListner deviceListner) {
        mapModel.getDeviceLocation(context, this);
    }

    @Override
    public void requestPermission(Context context) {
        mapModel.checkLocationPermission(context, this);
    }

    @Override
    public void setPermisionOk(Context context) {
        mapModel.checkGPSEnable(context, this, this);

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
    public void permissonAccept(Context context) {
        mapModel.checkGPSEnable(context, this, this);
    }

    @Override
    public void permissionDeny(Context context) {

        mapView.showPermissionDialog();
        mapModel.checkLocationPermission(context, this);
    }

    @Override
    public void gpsProvided(Context context) {
        requestLocation(context, this);
        mapView.showLocationButton();
    }

    @Override
    public void gpsNotPrvided(Context context) {
        mapView.buildGPSAlert();
        mapView.showGPSAlert();
    }

    @Override
    public void successLocation(Context context, LatLng myLocation) {
        mapView.showLocationButton();
        mapView.showLocation(myLocation);
        Log.i("locs", "successLocation: sss ");
    }

    @Override
    public void filureLocation(Context context) {
        mapView.LocationError();
    }

    @Override
    public void successDirection(Context context, Leg leg) {
        mapView.showDirections(leg);
    }

    @Override
    public void filureDirection(Context context) {
        mapView.DirectonError();
    }
}
