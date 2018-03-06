package com.rh.msu.autometer.bl;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hardik on 2/20/2018.
 */
public class PreferenceManager {
    private static final String PREFERENCE_NAME = "auto_meter_pref";
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;
    static PreferenceManager mPreferenceManager;

    private static final String KEY_OWNER_NAME = "key_owner_name";
    private static final String KEY_DRIVER_NAME = "key_driver_name";
    private static final String KEY_VEHICLE_NUMBER = "key_vehicle_number";
    private static final String KEY_DRIVER_MOBILE_NUMBER = "key_driver_mobile_number";
    private static final String KEY_LICENCE_NUMBER = "key_licence_number";
    private static final String KEY_UNIQUE_AUTO_NUMBER = "key_unique_number";

    private PreferenceManager(Context context) {
        mPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }


    public static PreferenceManager getInstance(Context context) {

        if (mPreferenceManager == null) {
            mPreferenceManager = new PreferenceManager(context);
        }
        return mPreferenceManager;
    }

    public void putOwnerName(String ownerName) {
        mEditor.putString(KEY_OWNER_NAME, ownerName).commit();
    }

    public String getOwnerName() {
        return mPreferences.getString(KEY_OWNER_NAME, "");
    }

    public void putDriverName(String driverName) {
        mEditor.putString(KEY_DRIVER_NAME, driverName).commit();
    }

    public String getDriverName() {
        return mPreferences.getString(KEY_DRIVER_NAME, "");
    }

    public void putLicenceNumber(String licenceNumber) {
        mEditor.putString(KEY_LICENCE_NUMBER, licenceNumber).commit();
    }

    public String getLicenceNumber() {
        return mPreferences.getString(KEY_LICENCE_NUMBER, "");
    }

    public void putVehicleNumber(String vehicleNumber) {
        mEditor.putString(KEY_VEHICLE_NUMBER, vehicleNumber).commit();
    }

    public String getVehicleNumber() {
        return mPreferences.getString(KEY_VEHICLE_NUMBER, "");
    }

    public void putDriverMobileNumber(String mobileNumber) {
        mEditor.putString(KEY_DRIVER_MOBILE_NUMBER, mobileNumber).commit();
    }

    public String getDriverMobileNumber() {
        return mPreferences.getString(KEY_DRIVER_MOBILE_NUMBER, "");
    }

    public void putUniqueAutoNumber(String number) {
        mEditor.putString(KEY_UNIQUE_AUTO_NUMBER, number).commit();
    }

    public String getUniqueAutoNumber() {
        return mPreferences.getString(KEY_UNIQUE_AUTO_NUMBER, "");
    }


}
