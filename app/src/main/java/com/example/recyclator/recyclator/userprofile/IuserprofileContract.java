package com.example.recyclator.recyclator.userprofile;

import android.media.Image;
import android.net.Uri;

import java.net.URI;

public interface IuserprofileContract {

     interface IuserprofileView{
         void setuserImage(Uri image);
       void   setuserName(String name);
       void   setuserLocation(String location);
       void   setuserEmail(String email);
       void   setuserNumber(String number);
       void   setuserRate(int Rate);

    }

     interface IuserprofilePresente{
         void callprese();

         void onDestroy();

     }

     interface IuserprofileModel{
         void downloadnameema(listner listner);

         interface listner{
             void onuserImagedownload(Uri image);
             void onusernamedownload(String name);
             void onuserlocationdownloaded(String locatoin);
             void onuserEmaildownloaded(String Email);
             void onuserNumberdownloaded(String Number);
             void onuserRatedownloaded(int Rate);
         }



     }
}
