package com.example.recyclator.recyclator.companyprofile;

import android.content.Context;
import android.net.Uri;

import com.example.recyclator.recyclator.companyprofile.IcompanyprofileContract.IcompanyView;

public class companyprofilePresenter implements IcompanyprofileContract.IcompanyPresenter ,IcompanyprofileContract.IcompanyModel.listner {

    IcompanyprofileContract.IcompanyModel mcompanyModel;
    IcompanyprofileContract.IcompanyView mcompanyView;

    public companyprofilePresenter(IcompanyView mcompanyView) {
        this.mcompanyView = mcompanyView;
        mcompanyModel=new companyprofileModel();
    }

    @Override

    public void callprese(Context context, int id_company) {
        mcompanyModel.downloadinfo(context,id_company,this);

    }

    @Override
    public void onDestroy() {
        if (mcompanyView != null)
            mcompanyView = null;

    }

    @Override
    public void oncoImagedownload(Uri image) {
        if (mcompanyView != null)
            mcompanyView.setcoImage(image);


    }

    @Override
    public void onconamedownload(String name) {
        if (mcompanyView != null)
            mcompanyView.setcoName(name);

    }

    @Override
    public void oncolocationdownloaded(String locatoin) {
        if (mcompanyView != null)
            mcompanyView.setcoLocation(locatoin);

    }

    @Override
    public void oncoEmaildownloaded(String Email) {
        if (mcompanyView != null)
            mcompanyView.setcoEmail(Email);

    }

    @Override
    public void oncoNumberdownloaded(String Number) {
        if (mcompanyView != null)
            mcompanyView.setcorNumber(Number);

    }

    @Override
    public void oncoRatedownloaded(int Rate) {
        if (mcompanyView != null)
            mcompanyView.setcoRate(Rate);

    }

    @Override
    public void oncoDescdownloaded(String desc) {
        if (mcompanyView != null)
            mcompanyView.setcoDesc(desc);

    }

    @Override
    public void oncoPaymentdownloaded(String payment) {
        if (mcompanyView != null)
            mcompanyView.setcoPayment(payment);

    }

    @Override
    public void oncoQuantitydownloaded(String quantity) {
        if (mcompanyView != null)
            mcompanyView.setcoQuantity(quantity);

    }
}
