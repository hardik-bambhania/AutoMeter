package com.rh.msu.autometer.utils;

import org.junit.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Hardik on 11/18/2017.
 */
public class FareCalculatorTest {

    @Test
    public void testCalculateFare() throws Exception {

        NumberFormat formatter = new DecimalFormat("#0.0");

        for (double distance = 1000; distance < 50000; distance = distance + 100) {
            double fare = FareCalculator.calculateFare(distance);

            double printDist = distance / 1000;   //Convert into KM
            System.out.println("Result Dis : " + formatter.format(printDist) + "(Km.) ,Fare : " + formatter.format(fare) + "Rs.");
        }


    }
}