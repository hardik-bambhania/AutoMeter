package com.rh.msu.autometer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.rh.msu.autometer.R;
import com.rh.msu.autometer.bl.PreferenceManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (isDriverLoggedIn()) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashActivity.this, AddDriverDetailsActivity.class);
            startActivity(intent);
        }

        finish();
    }

    private boolean isDriverLoggedIn() {
        PreferenceManager preferenceManager = PreferenceManager.getInstance(this);

        return !(TextUtils.isEmpty(preferenceManager.getDriverMobileNumber()) ||
                TextUtils.isEmpty(preferenceManager.getDriverName()) ||
                TextUtils.isEmpty(preferenceManager.getLicenceNumber()) ||
                TextUtils.isEmpty(preferenceManager.getOwnerName()) ||
                TextUtils.isEmpty(preferenceManager.getVehicleNumber()));
    }
}
