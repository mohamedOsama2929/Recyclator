package com.example.recyclator.recyclator.request;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public interface IRequestContract {

    interface IRequestView{

        void showProgress();
        void hideProgress();
        void showAlert(String message);
    }

    interface IRequestPresenter{

        void getRequests(Context context,RecyclerView recyclerView);
        void onDestroy();
    }

    interface IRequestModel{

        interface onRequestFinishedListener{

            void onSuccess();
            void onFailure(String message);
        }

        List<Request> downloadRequests(Context context, onRequestFinishedListener listener);
    }
}
