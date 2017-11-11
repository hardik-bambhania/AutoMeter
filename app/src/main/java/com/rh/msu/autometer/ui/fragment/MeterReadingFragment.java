package com.rh.msu.autometer.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rh.msu.autometer.R;
import com.rh.msu.autometer.TripApplication;
import com.squareup.otto.Subscribe;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeterReadingFragment extends Fragment {

    TextView mTxtDistance;

    public MeterReadingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meter_reading, container, false);
        mTxtDistance = (TextView) view.findViewById(R.id.txt_distance);
        mTxtDistance.setText("0");
        TripApplication.getInstance().getEventBus().register(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Subscribe
    public void getMessage(Double distance) {
        NumberFormat formatter = new DecimalFormat("#0.0");
        if (distance > 0) {
            mTxtDistance.setText("" + formatter.format(distance));
        }
    }

}
