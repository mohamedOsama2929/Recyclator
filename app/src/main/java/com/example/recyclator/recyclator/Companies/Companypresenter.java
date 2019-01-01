package com.example.recyclator.recyclator.Companies;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class Companypresenter implements ICompanyContract.ICompanyPresenter, ICompanyContract.ICompanyModel.onCompanyFinishedListener {
    ICompanyContract.ICompanyView mICompanyView;
    ICompanyContract.ICompanyModel mICompanyModel;
    private CompaniesAdapter companyAdapter;
    private List<Company> result;


    public Companypresenter(ICompanyContract.ICompanyView mICompanyView) {

        this.mICompanyView = mICompanyView;
        mICompanyModel = new CompanyModel();
    }


    @Override
    public void getCompanies(RecyclerView recyclerView, Context context, String city) {
        if (mICompanyView != null) {
            mICompanyView.showProgress();
            result = mICompanyModel.downloadCompanies(this, context, city);

            companyAdapter = new CompaniesAdapter(recyclerView.getContext(), result);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(companyAdapter);
        }

    }

    @Override
    public void onDestroy() {
        if (mICompanyView != null) {
            mICompanyView = null;
        }

    }

    @Override
    public void onSuccess() {
        if (mICompanyView != null) {
            mICompanyView.hideProgress();
        }

    }

    @Override
    public void onFailure(String message) {
        if (mICompanyView != null) {
            mICompanyView.hideProgress();
            mICompanyView.showAlert(message);
        }

    }
}

