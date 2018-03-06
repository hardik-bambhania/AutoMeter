package com.rh.msu.autometer.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rh.msu.autometer.R;
import com.rh.msu.autometer.bl.PreferenceManager;

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

        PreferenceManager preferenceManager = PreferenceManager.getInstance(getActivity());

        ((TextView) view.findViewById(R.id.txt_driver_name)).setText("Name : " + preferenceManager.getDriverName());
        ((TextView) view.findViewById(R.id.txt_driver_number)).setText("Contact Number : " + preferenceManager.getDriverMobileNumber());
        ((TextView) view.findViewById(R.id.txt_vehicle_number)).setText("Vehicle Number : " + preferenceManager.getVehicleNumber());
        ((TextView) view.findViewById(R.id.txt_unique_auto_number)).setText("Unique Auto Number : " + preferenceManager.getUniqueAutoNumber());
        ((TextView) view.findViewById(R.id.txt_licence_number)).setText("Licence Number : " + preferenceManager.getLicenceNumber());

        return view;
    }

}
