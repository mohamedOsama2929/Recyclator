package com.example.recyclator.recyclator.Companies;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public interface ICompanyContract {

    interface ICompanyView {

        void showProgress();

        void hideProgress();

        void showAlert(String message);
    }

    interface ICompanyPresenter {
        void getCompanies(RecyclerView recyclerView, Context context, String city);

        void onDestroy();
    }

    interface ICompanyModel {

        List<Company> downloadCompanies(ICompanyContract.ICompanyModel.onCompanyFinishedListener listener, Context context, String city);

        interface onCompanyFinishedListener {

            void onSuccess();

            void onFailure(String message);
        }
    }
}
