package com.rh.msu.autometer;

import android.app.Application;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Hardik on 8/3/2017.
 */
public class TripApplication extends Application {

    private static Bus bus;
    private static TripApplication tripApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        tripApplication = this;
    }

    public static TripApplication getInstance() {
        return tripApplication;
    }


    public Bus getEventBus() {
        if (bus == null) {
            bus = new Bus(ThreadEnforcer.MAIN);
        }

        return bus;
    }
}
