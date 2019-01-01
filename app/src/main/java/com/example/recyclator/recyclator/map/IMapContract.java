package com.example.recyclator.recyclator.map;

import android.content.Context;
import android.content.res.Resources;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

public interface IMapContract {


    interface IMapView {

        void showPermissionDialog();
        void buildGPSAlert();
        void showGPSAlert();
        void showLocation(LatLng myLocation);
        void showLocationButton();
        void hideLocationButton();

        void showDirections(PolylineOptions polygonOptions, double lat, double lng);
        void DirectonError();
        void LocationError();
    }

    interface IMapPresenter {

        void requestLocation(Context context, ImapModel.IDeviceListner deviceListner, Resources resources);

        void requestPermission(Context context, Resources resources);

        void setPermisionOk(Context context, Resources resources);

        void requestDirection(double mylat, double mylng, double targetLat, double targetLng, Context context
                , Resources resources, ImapModel.IDirectionListner directionListner);
        void onDestroy();
    }

    interface ImapModel {

        void checkLocationPermission(Context context, IPermissonListner permissonListner, Resources resources);

        void checkGPSEnable(Context context, IGPSListner gpsListner, IDeviceListner deviceListner, Resources resources);

        void getDeviceLocation(Context context, IDeviceListner deviceListner, Resources resources);

        void getDirections(double mylat, double mylng, double targetLat, double targetLng, Context context
                , Resources resources, IDirectionListner directionListner);

        interface IPermissonListner {

            void permissonAccept(Context context, Resources resources);

            void permissionDeny(Context context, Resources resources);
        }
        interface IGPSListner {

            void gpsProvided(Context context, Resources resources);
            void gpsNotPrvided(Context context);
        }
        interface IDeviceListner {

            void successLocation(Context context, LatLng myLocation, Resources resources);
            void filureLocation(Context context);
        }
        interface IDirectionListner {

            void successDirection(Context context, PolylineOptions polygonOptions, double targetLat, double targetLng);

            void getdistance(double distance);
            void filureDirection(Context context);
        }
    }
}
