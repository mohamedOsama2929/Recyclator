package com.example.recyclator.recyclator.request;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public interface IRequestContract {

    interface IRequestView{

        void showProgress();
        void hideProgress();
        void showAlert(String message);
    }

    interface IRequestPresenter{

        void getRequests(Context context, RecyclerView recyclerView, int comp_ID);
        void onDestroy();
    }

    interface IRequestModel{

        interface onRequestFinishedListener{

            void onSuccess();
            void onFailure(String message);
        }

        List<Request> downloadRequests(Context context, onRequestFinishedListener listener, int comp_ID);
    }
}
