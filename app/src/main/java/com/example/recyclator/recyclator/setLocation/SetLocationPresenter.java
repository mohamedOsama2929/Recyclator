package com.example.recyclator.recyclator.setLocation;

/**
 * Created by osos on 3/20/18.
 */

import android.content.Context;

import com.example.recyclator.recyclator.setLocation.ISetLocationContract.ISetLocationModel;
import com.example.recyclator.recyclator.setLocation.ISetLocationContract.ISetLocationView;
import com.example.recyclator.recyclator.setLocation.ISetLocationContract.ISetlocationPresenter;

public class SetLocationPresenter implements ISetlocationPresenter
        ,ISetLocationModel.ILocationListner
        ,ISetLocationModel.IPermissonListner
        ,ISetLocationModel.IGPSListner {

    ISetLocationView mSetLocationView;
    ISetLocationModel mSetLocationModel;

    public SetLocationPresenter(ISetLocationView mSetLocationView,Context context){

      this.mSetLocationView=mSetLocationView;
      mSetLocationModel=new SetLocationModel();
      //mSetLocationModel.checkLocationPermission(context,this);

    }

    //this method check location permission and id it exist it automaticly by permission Listner
    // go to method check GPS
    //and if GPS provided it automaticly by GPS listner get location data
    @Override
    public void requestLocationData(Context context) {

        mSetLocationModel.checkLocationPermission(context,this);
    }

    @Override
    public void requestPermission(Context context) {

        mSetLocationView.showPermissionDialog();
    }

    @Override
    public void setPermisionOk(Context context) {

        mSetLocationModel.checkGPSEnable(context, this, this);

    }

    @Override
    public void gpsDialogClick(Context context) {

        //mSetLocationModel.checkGPSEnable(context, this, this);

    }

    @Override
    public void gpsDialogCanel(Context context) {

       // mSetLocationModel.checkGPSEnable(context,this);

    }

    @Override
    public void onDestroy() {

        if (mSetLocationView!=null){
            mSetLocationView=null;
        }

    }

    @Override
    public void onSuccess(String city, String area) {
        if (mSetLocationView != null) {

            mSetLocationView.hidePrgrassBar();
            mSetLocationView.setLocationData(city, area);
        }

    }

    @Override
    public void noLocationData() {
        if (mSetLocationView != null) {
            mSetLocationView.hidePrgrassBar();
            mSetLocationView.errorLocationNotFound();
        }
    }

    @Override
    public void onFailure() {
        if (mSetLocationView != null) {
            mSetLocationView.hidePrgrassBar();

            mSetLocationView.errorLocationNotFound();

        }
    }

    @Override
    public void permissonAccept(Context context) {

        mSetLocationModel.checkGPSEnable(context, this, this);
    }

    @Override
    public void permissionDeny(Context context) {
        if (mSetLocationView != null) {
            mSetLocationView.showPermissionDialog();
        }

    }

    @Override
    public void gpsProvided(Context context) {
        if (mSetLocationView != null) {

            mSetLocationView.hideGPSAlert();
            mSetLocationView.showPrgrassBar();
            mSetLocationModel.getLocationData(context, this);
        }

    }

    @Override
    public void gpsNotPrvided(Context context) {
        if (mSetLocationView != null) {

            mSetLocationView.showGPSAlert();
        }



    }
}
