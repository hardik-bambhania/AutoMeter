package com.rh.msu.autometer.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.rh.msu.autometer.R;
import com.rh.msu.autometer.bl.PreferenceManager;

public class SplashActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkPermission();
    }

    /**
     * Check Location permission
     *
     * @return
     */
    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            moveForward();

        } else {
            //Ask permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    }, PERMISSION_REQUEST);
        }
    }

    private void moveForward() {
        if (isDriverLoggedIn()) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashActivity.this, AddDriverDetailsActivity.class);
            startActivity(intent);
        }

        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        checkPermission();
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
