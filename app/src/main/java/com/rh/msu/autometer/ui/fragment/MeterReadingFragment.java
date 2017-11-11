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
    TextView mTxtFare;

    public MeterReadingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meter_reading, container, false);
        mTxtDistance = (TextView) view.findViewById(R.id.txt_distance);
        mTxtFare = (TextView) view.findViewById(R.id.txt_fare);
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

        displayDistance(distance);

        displayFare(distance);
    }

    /**
     * Display distance
     *
     * @param distance distance in meter
     */
    private void displayDistance(Double distance) {

        distance = distance / 1000;   //Convert into KM

        NumberFormat formatter = new DecimalFormat("#0.0");
        if (distance > 0) {
            mTxtDistance.setText("" + formatter.format(distance));
        }
    }


    /**
     * Dis[lay fare
     *
     * @param distance distance in meter
     */
    private void displayFare(double distance) {

        double fare = calculateFare(distance);

        NumberFormat formatter = new DecimalFormat("#0.0");
        mTxtFare.setText("" + formatter.format(fare));

    }

    private double calculateFare(double distance) {
        double fare = 12;

        double baseDistance = 1200;

        double traveled = distance - baseDistance;


        if (traveled < 0) {
            fare = 12;
        } else {
            fare = fare + (traveled / 200) + 1.60;
        }

        return fare;
    }

}
