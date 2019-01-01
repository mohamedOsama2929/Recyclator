package com.example.recyclator.recyclator.userprofile;

import android.content.Context;
import android.net.Uri;

import com.example.recyclator.recyclator.userprofile.IuserprofileContract.IuserprofileView;

public class userproflePresenter implements IuserprofileContract.IuserprofilePresente ,IuserprofileContract.IuserprofileModel.listner{

    IuserprofileContract.IuserprofileModel muserprofilemodel;
    IuserprofileContract.IuserprofileView muserprofileview;

    public userproflePresenter(IuserprofileView muserprofileview) {
        this.muserprofileview = muserprofileview;
        muserprofilemodel=new userprofileModel();
    }

    @Override
    public void callprese(Context context, int user_id) {
        muserprofilemodel.downloadnameema(context,user_id,this);
    }

    @Override
    public void onDestroy() {
        if (muserprofileview !=null){
            muserprofileview=null;
        }
    }


    @Override
    public void onuserImagedownload(Uri image) {

    }

    @Override
    public void onusernamedownload(String name) {
        if (muserprofileview !=null) {
            muserprofileview.setuserName(name);
        }

    }

    @Override
    public void onuserlocationdownloaded(String locatoin) {
        if (muserprofileview !=null) {
            muserprofileview.setuserLocation(locatoin);
        }
    }

    @Override
    public void onuserEmaildownloaded(String Email) {
        if (muserprofileview !=null) {
            muserprofileview.setuserEmail(Email);
        }
    }

    @Override
    public void onuserNumberdownloaded(String Number) {
        if (muserprofileview !=null){
            muserprofileview.setuserNumber(Number);
        }

    }

    @Override
    public void onuserRatedownloaded(int Rate) {
        if (muserprofileview !=null) {
            muserprofileview.setuserRate(Rate);
        }

        }


}
