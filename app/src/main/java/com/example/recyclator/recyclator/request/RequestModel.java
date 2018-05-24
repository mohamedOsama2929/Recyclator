package com.example.recyclator.recyclator.request;

import java.util.ArrayList;
import java.util.List;

public class RequestModel implements IRequestContract.IRequestModel {

    private ArrayList<Request> requests;

    @Override
    public List<Request> downloadRequests(onRequestFinishedListener listener) {

        requests = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            requests.add(new Request("" + i, " " + i, " " + i, " " + i, "" + i));
        }

        if (requests != null){
            listener.onSuccess();
        }
        else {
            listener.onFailure("There Are No Requests");
        }

        return requests;
    }
}
