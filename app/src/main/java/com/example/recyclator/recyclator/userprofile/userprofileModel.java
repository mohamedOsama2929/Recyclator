package com.example.recyclator.recyclator.userprofile;

public class userprofileModel implements IuserprofileContract.IuserprofileModel {


    @Override
    public void downloadnameema( listner listner) {
        listner.onuserEmaildownloaded("dewa@gmail.com");
        listner.onusernamedownload("M.dewidar");
        listner.onuserlocationdownloaded("elmahla sh,eltr3a");
        listner.onuserNumberdownloaded("01127387443");
        listner.onuserRatedownloaded(5);
    }
}
