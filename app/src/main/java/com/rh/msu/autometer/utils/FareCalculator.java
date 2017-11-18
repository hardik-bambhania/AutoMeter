package com.rh.msu.autometer.utils;

/**
 * Created by Hardik on 11/18/2017.
 */
public class FareCalculator {

    public static double calculateFare(double distance) {
        double fare = 12;

        double baseDistance = 1000;

        double traveled = distance - baseDistance;


        if (traveled < 0) {
            fare = 12;
        } else {
            fare = fare + ((int) (traveled / 200)) * 1.60;
        }

        return fare;
    }
}
