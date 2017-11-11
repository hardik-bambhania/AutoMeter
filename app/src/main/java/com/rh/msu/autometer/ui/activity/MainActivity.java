package com.rh.msu.autometer.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rh.msu.autometer.R;
import com.rh.msu.autometer.ui.fragment.AutoMapFragment;
import com.rh.msu.autometer.ui.fragment.DriverDetailsFragment;
import com.rh.msu.autometer.ui.fragment.MeterReadingFragment;
import com.rh.msu.autometer.ui.fragment.TripManagerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMapFragment();
        loadDriverDetailsFragment();
        loadTripManagerFragment();
        loadMeterReadingFragment();

    }

    private void loadMapFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AutoMapFragment mapFragment = new AutoMapFragment();
        fragmentTransaction.add(R.id.layout_map, mapFragment, "hi");
        fragmentTransaction.commit();

    }


    private void loadDriverDetailsFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DriverDetailsFragment driverDetailsFragment = new DriverDetailsFragment();
        fragmentTransaction.add(R.id.layout_driver_details, driverDetailsFragment, "hi1");
        fragmentTransaction.commit();
    }

    private void loadTripManagerFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TripManagerFragment tripManagerFragment = new TripManagerFragment();
        fragmentTransaction.add(R.id.layout_trip_manage, tripManagerFragment, "hi2");
        fragmentTransaction.commit();
    }

    private void loadMeterReadingFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MeterReadingFragment meterReadingFragment = new MeterReadingFragment();
        fragmentTransaction.add(R.id.layout_meter_reading, meterReadingFragment, "hi3");
        fragmentTransaction.commit();
    }
}
