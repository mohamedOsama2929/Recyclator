package com.example.recyclator.recyclator.setLocation;

/**
 * Created by osos on 3/20/18.
 */
import android.content.Context;

import com.example.recyclator.recyclator.setLocation.ISetLocationContract.*;

public class SetLocationPresenter implements ISetlocationPresenter
        ,ISetLocationModel.ILocationListner
        ,ISetLocationModel.IPermissonListner
        ,ISetLocationModel.IGPSListner {

    ISetLocationView mSetLocationView;
    ISetLocationModel mSetLocationModel;

    public SetLocationPresenter(ISetLocationView mSetLocationView,Context context){

      this.mSetLocationView=mSetLocationView;
      mSetLocationModel=new SetLocationModel();
      mSetLocationModel.checkLocationPermission(context,this);

    }
    @Override
    public void requestLocationData(Context context) {

        mSetLocationModel.checkLocationPermission(context,this);
        mSetLocationModel.checkGPSEnable(context,this);
        mSetLocationModel.getLocationData(context,this);
    }

    @Override
    public void requestPermission(Context context) {

        mSetLocationView.showPermissionDialog();
    }

    @Override
    public void setPermisionOk(Context context) {

        mSetLocationModel.checkGPSEnable(context,this);

    }

    @Override
    public void gpsDialogClick(Context context) {

        mSetLocationModel.checkGPSEnable(context,this);

    }

    @Override
    public void gpsDialogCanel(Context context) {

        mSetLocationModel.checkGPSEnable(context,this);

    }

    @Override
    public void onDestroy() {

        if (mSetLocationView!=null){
            mSetLocationView=null;
        }

    }

    @Override
    public void onSuccess(String city,String area) {

        mSetLocationView.hidePrgrassBar();
        mSetLocationView.setLocationData(city,area);
    }

    @Override
    public void noLocationData() {

        mSetLocationView.hidePrgrassBar();
        mSetLocationView.errorLocationNotFound();
    }

    @Override
    public void onFailure() {

        mSetLocationView.hidePrgrassBar();
        mSetLocationView.errorLocationNotFound();
    }

    @Override
    public void permissonAccept(Context context) {

       mSetLocationModel.checkGPSEnable(context,this);
    }

    @Override
    public void permissionDeny(Context context) {

        mSetLocationView.showPermissionDialog();

    }

    @Override
    public void gpsProvided(Context context) {

        mSetLocationView.hideGPSAlert();
        mSetLocationView.showPrgrassBar();
        mSetLocationModel.getLocationData(context,this);

    }

    @Override
    public void gpsNotPrvided(Context context) {

        mSetLocationView.showGPSAlert();



    }
}
