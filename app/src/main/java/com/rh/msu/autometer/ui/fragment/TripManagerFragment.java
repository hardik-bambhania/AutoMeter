package com.rh.msu.autometer.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rh.msu.autometer.Globle;
import com.rh.msu.autometer.R;
import com.rh.msu.autometer.TripApplication;

public class TripManagerFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match

    private Button mBtnStartStopTrip;

    private static final String START_TRIP = "START TRIP";
    private static final String STOP_TRIP = "STOP TRIP";

    public TripManagerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trip_manager, container, false);
        TripApplication.getInstance().getEventBus().register(this);
        mBtnStartStopTrip = (Button) view.findViewById(R.id.btn_start_stop_trip);
        mBtnStartStopTrip.setOnClickListener(this);
        mBtnStartStopTrip.setText(START_TRIP);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_start_stop_trip) {

            if (mBtnStartStopTrip.getText().toString().equalsIgnoreCase(START_TRIP)) {

                //Start Trip
                mBtnStartStopTrip.setText(STOP_TRIP);
                TripApplication.getInstance().getEventBus().post(Globle.START_TRIP_CLICK);

            } else if (mBtnStartStopTrip.getText().toString().equalsIgnoreCase(STOP_TRIP)) {

                //Stop Trip
                mBtnStartStopTrip.setText(START_TRIP);
                TripApplication.getInstance().getEventBus().post(Globle.STOP_TRIP_CLICK);

            }

        }
    }
}
