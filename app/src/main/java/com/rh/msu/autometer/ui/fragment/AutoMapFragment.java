package com.rh.msu.autometer.ui.fragment;

import android.Manifest;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;
import com.rh.msu.autometer.Globle;
import com.rh.msu.autometer.R;
import com.rh.msu.autometer.TripApplication;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

public class AutoMapFragment extends Fragment {

    private static final int PERMISSION_REQUEST = 123;
    private MapView mapView;
    private GoogleMap mMap;

    private ArrayList<LatLng> mPoints = new ArrayList<>();
    Polyline mPolyline;

    LatLng mStartLetLong;
    LatLng mCurrentLetLong;
    LatLng mLastKnownLetLong;


    Double mDistance = 0.0;


    LocationManager mLocationManager;
    boolean isTripStarted = false;


    public AutoMapFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = (MapView) view.findViewById(R.id.map_auto);
        mapView.onCreate(savedInstanceState);
        TripApplication.getInstance().getEventBus().register(this);
        initMap();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private void initMap() {
        try {
            MapsInitializer.initialize(getActivity().getApplication());
            mapView.getMapAsync(onMapReadyCallback);
        } catch (Exception e) {
            Log.e(AutoMapFragment.class.getName(), e.getMessage(), e);
        }
    }


    private final OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            if (mMap != null && checkPermission()) {
                displayMyCurrentLocation();
            }
        }
    };

    @SuppressWarnings("MissingPermission")
    /**
     * Zoom to your current location
     */
    private void displayMyCurrentLocation() {

        mMap.setMyLocationEnabled(true);
        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 2000, 0, locationListener);
    }

    /**
     * Check Location permission
     *
     * @return
     */
    private boolean checkPermission() {

        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST);
            return false;
        } else if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST);
            return false;
        } else if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        checkPermission();
    }

    private void calculateDistance(LatLng currentLatLong) {
        //Calculating the distance in meters

        if (currentLatLong != null && mLastKnownLetLong != null) {
            Double distance = SphericalUtil.computeDistanceBetween(mLastKnownLetLong, currentLatLong);
            mDistance = mDistance + distance;
            TripApplication.getInstance().getEventBus().post(mDistance);
            mLastKnownLetLong = currentLatLong;
        }
    }


    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LatLng latLng = new LatLng(latitude, longitude); //you already have this
            Log.d("Location", "Location : " + latLng.toString());
            Toast.makeText(getActivity(), "Location : " + latLng.toString(), Toast.LENGTH_LONG).show();

            mCurrentLetLong = latLng;

            if (isTripStarted) {
                mPoints.add(latLng); //added
                redrawLine();
                calculateDistance(latLng);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void redrawLine() {

        mMap.clear();  //clears all Markers and Polylines

        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(mCurrentLetLong, 16);
        mMap.animateCamera(yourLocation);

        PolylineOptions options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        for (int i = 0; i < mPoints.size(); i++) {
            LatLng point = mPoints.get(i);
            options.add(point);
        }

        mPolyline = mMap.addPolyline(options); //add Polyline

        if (mStartLetLong != null) {
            mMap.addMarker(new MarkerOptions().position(mStartLetLong)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        }


    }

    @Subscribe
    public void getMessage(String message) {
        switch (message) {
            case Globle.START_TRIP_CLICK:
                isTripStarted = true;
                Toast.makeText(getActivity().getApplicationContext(), "Start", Toast.LENGTH_LONG).show();
                new StartTripTask().execute();
                break;
            case Globle.STOP_TRIP_CLICK:
                isTripStarted = false;
                Toast.makeText(getActivity().getApplicationContext(), "Stop", Toast.LENGTH_LONG).show();
                mMap.addMarker(new MarkerOptions().position(mCurrentLetLong)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(mStartLetLong);
                builder.include(mCurrentLetLong);
                LatLngBounds bounds = builder.build();
                int padding = 128; // offset from edges of the map in pixels
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                mMap.animateCamera(cameraUpdate);
                break;
            default:
                break;
        }
    }

    class StartTripTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getContext());
            dialog.setTitle("Processing");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @SuppressWarnings("MissingPermission")
        @Override
        protected Void doInBackground(Void... params) {
           /* Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_HIGH);
            String provider = mLocationManager.getBestProvider(criteria, true);
            Location location = mLocationManager.getLastKnownLocation(provider);

            if (location != null) {
                location = mLocationManager.getLastKnownLocation(provider);
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                mStartLetLong = new LatLng(latitude, longitude);
                mLastKnownLetLong = new LatLng(latitude, longitude);
                return null;
            }*/

            while (mCurrentLetLong == null) {

            }

            mStartLetLong = mCurrentLetLong;
            mLastKnownLetLong = mCurrentLetLong;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();

            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(mStartLetLong, 16);
            mMap.addMarker(new MarkerOptions().position(mStartLetLong)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.animateCamera(yourLocation);
        }
    }
}
