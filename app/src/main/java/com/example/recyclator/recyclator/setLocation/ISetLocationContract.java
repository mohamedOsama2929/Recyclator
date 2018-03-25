package com.example.recyclator.recyclator.setLocation;

import android.content.Context;

/**
 * Created by osos on 3/20/18.
 */

public interface ISetLocationContract {


    interface ISetLocationView{

         void buildGPSAlert();
         void showGPSAlert();
         void hideGPSAlert();
         void showPermissionDialog();
         void showPrgrassBar();
         void hidePrgrassBar();
         void setLocationData(String city,String area);
         void requestNavigateToCompanies();
         void errorLocationNotFound();

    }

    interface ISetlocationPresenter {

         void requestLocationData(Context context);
         void requestPermission(Context context);
         void setPermisionOk(Context context);
         void gpsDialogClick(Context context);
         void gpsDialogCanel(Context context);
         void onDestroy();
    }

    interface ISetLocationModel{

         void checkLocationPermission(Context context,IPermissonListner listner);

        void checkGPSEnable(Context context, IGPSListner listner, ILocationListner locationListner);

         void getLocationData(Context context,ILocationListner listner);


        interface IPermissonListner{

            void permissonAccept(Context context);
            void permissionDeny(Context context);
        }
        interface  IGPSListner{

            void gpsProvided(Context context);
            void gpsNotPrvided(Context context);

        }
        interface ILocationListner {

            void onSuccess(String city,String area);
            void noLocationData();
            void onFailure();

        }


    }



}
