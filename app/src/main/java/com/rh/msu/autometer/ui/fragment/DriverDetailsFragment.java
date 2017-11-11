package com.rh.msu.autometer.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rh.msu.autometer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DriverDetailsFragment extends Fragment {


    public DriverDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_details, container, false);
        return view;
    }

}
