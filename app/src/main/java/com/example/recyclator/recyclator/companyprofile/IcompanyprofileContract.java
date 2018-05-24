package com.example.recyclator.recyclator.companyprofile;

import android.net.Uri;

public interface IcompanyprofileContract {
    interface IcompanyView {

        void setcoImage(Uri image);

        void setcoName(String name);

        void setcoDesc(String desc);

        void setcoLocation(String location);

        void setcoEmail(String email);

        void setcorNumber(String number);

        void setcoPayment(String payment);

        void setcoRate(int rate);

        void setcoQuantity(String quantity);


    }

    interface IcompanyPresenter {
        void callprese();

        void onDestroy();


    }

    interface IcompanyModel {

        void downloadinfo(listner listner);

        interface listner {
            void oncoImagedownload(Uri image);

            void onconamedownload(String name);

            void oncolocationdownloaded(String locatoin);

            void oncoEmaildownloaded(String Email);

            void oncoNumberdownloaded(String Number);

            void oncoRatedownloaded(int Rate);

            void oncoDescdownloaded(String desc);

            void oncoPaymentdownloaded(String payment);

            void oncoQuantitydownloaded(String quantity);
        }

    }
}
