package com.example.recyclator.recyclator;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.recyclator.recyclator.Companies.Companypresenter;
import com.example.recyclator.recyclator.Companies.ICompanyContract;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment implements ICompanyContract.ICompanyView {

    //code fathy
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBarCompany)
    ProgressBar ProgressBarRequest;

    //private RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
    String url = "https://desolate-chamber-62168.herokuapp.com/public/search/cairo";
    private ICompanyContract.ICompanyPresenter mCompanyPresenter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        /*
        if (getArguments()!=null){

            int id=getArguments().getInt("userId");
            String city = getArguments().getString("city");
            if(city == null)
            {
                city="cairo";
            }
             url ="https://desolate-chamber-62168.herokuapp.com/public/search/"+city;
            mCompanyPresenter.getCompanies(recyclerView, getContext() , city);
        }else {

             url ="https://desolate-chamber-62168.herokuapp.com/public/search/cairo";
            mCompanyPresenter.getCompanies(recyclerView, getContext() , "cairo");

        }
        */


        View view = inflater.inflate(R.layout.fragment_list_companies, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCompanyPresenter = new Companypresenter(this);


        if (getArguments() != null) {
            int id = getArguments().getInt("userId");
            String city = getArguments().getString("city");
            if (city == null) {
                city = "cairo";
            }
            url = "https://desolate-chamber-62168.herokuapp.com/public/search/cairo";
            mCompanyPresenter.getCompanies(recyclerView, getActivity().getApplicationContext(), "cairo");


        } else {

            url = "https://desolate-chamber-62168.herokuapp.com/public/search/cairo";
            mCompanyPresenter.getCompanies(recyclerView, getActivity().getApplicationContext(), "cairo");


        }


        ImageView addRequst = (ImageView) view.findViewById(R.id.addRequst);
        addRequst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), uploadTrashActivity.class));
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCompanyPresenter.onDestroy();
    }

    @Override
    public void showProgress() {

        ProgressBarRequest.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

        ProgressBarRequest.setVisibility(View.GONE);
    }

    @Override
    public void showAlert(String message) {
        StyleableToast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT, R.style.mytoast).show();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
