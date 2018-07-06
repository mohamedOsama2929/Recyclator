package com.example.recyclator.recyclator.request;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RequestPresenter implements IRequestContract.IRequestPresenter,IRequestContract.IRequestModel.onRequestFinishedListener {

    IRequestContract.IRequestView mIRequestView;
    IRequestContract.IRequestModel mIRequestModel;
    private RequestAdapter requestAdapter;
    private List<Request> result;

    public RequestPresenter(IRequestContract.IRequestView mIRequestView){

        this.mIRequestView = mIRequestView;
        mIRequestModel = new RequestModel();
    }

    @Override
    public void getRequests(Context context, RecyclerView recyclerView) {

        mIRequestView.showProgress();

        if (mIRequestView != null) {

            result = mIRequestModel.downloadRequests(context,this);

            requestAdapter = new RequestAdapter(recyclerView.getContext(), result);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(requestAdapter);
        }
    }

    @Override
    public void onDestroy() {

        if (mIRequestView != null) {
            mIRequestView = null;
        }
    }

    @Override
    public void onSuccess() {

        if (mIRequestView != null) {
            mIRequestView.hideProgress();
        }

    }

    @Override
    public void onFailure(String message) {

        if (mIRequestView != null) {
            mIRequestView.hideProgress();
            mIRequestView.showAlert(message);
        }
    }
}
