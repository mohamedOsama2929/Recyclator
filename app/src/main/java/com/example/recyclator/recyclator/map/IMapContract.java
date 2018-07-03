package com.example.recyclator.recyclator.map;

import android.content.Context;
import android.content.res.Resources;

import com.akexorcist.googledirection.model.Leg;
import com.google.android.gms.maps.model.LatLng;

public interface IMapContract {


    interface IMapView {

        void showPermissionDialog();

        void buildGPSAlert();

        void showGPSAlert();

        void showLocation(LatLng myLocation);

        void showLocationButton();

        void hideLocationButton();

        void showDirections(Leg leg);

        void DirectonError();

        void LocationError();
    }

    interface IMapPresenter {

        void requestLocation(Context context, ImapModel.IDeviceListner deviceListner);

        void requestPermission(Context context);

        void setPermisionOk(Context context);

        void requestDirection(double mylat, double mylng, double targetLat, double targetLng, final Context context
                , Resources resources, final ImapModel.IDirectionListner directionListner);

        void onDestroy();
    }

    interface ImapModel {

        void checkLocationPermission(Context context, IPermissonListner permissonListner);

        void checkGPSEnable(Context context, IGPSListner gpsListner, IDeviceListner deviceListner);

        void getDeviceLocation(Context context, IDeviceListner deviceListner);


        void getDirections(double mylat, double mylng, double targetLat
                , double targetLng, Context context
                , Resources resources, IDirectionListner directionListner);


        interface IPermissonListner {

            void permissonAccept(Context context);

            void permissionDeny(Context context);
        }

        interface IGPSListner {

            void gpsProvided(Context context);

            void gpsNotPrvided(Context context);

        }

        interface IDeviceListner {

            void successLocation(Context context, LatLng myLocation);

            void filureLocation(Context context);
        }

        interface IDirectionListner {

            void successDirection(Context context, Leg leg);

            void filureDirection(Context context);
        }
    }
}
