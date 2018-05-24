package com.example.recyclator.recyclator.companyprofile;

public class companyprofileModel implements IcompanyprofileContract.IcompanyModel{
    @Override
    public void downloadinfo(listner listner) {
        listner.oncoEmaildownloaded("dewa@gmail.com");
        listner.oncoDescdownloaded("company desc");
        listner.oncolocationdownloaded("ellmahla eltr3a");
        listner.onconamedownload("Robcia");
        listner.oncoNumberdownloaded("01127387443");
        listner.oncoPaymentdownloaded("dewa card");
        listner.oncoQuantitydownloaded("200 Kg");
        listner.oncoRatedownloaded(5);

    }
}
