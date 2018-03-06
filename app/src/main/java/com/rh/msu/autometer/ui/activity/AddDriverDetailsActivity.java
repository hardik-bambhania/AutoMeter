package com.rh.msu.autometer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rh.msu.autometer.R;
import com.rh.msu.autometer.bl.PreferenceManager;

public class AddDriverDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mEdTxtOwnerName;
    EditText mEdTxtDriverName;
    EditText mEdTxtVehicleNumber;
    EditText mEdTxtLicenceNumber;
    EditText mEdTxtDiverMobileNumber;
    EditText mEdTxtUniqueAutoNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver_details);

        Button btnSave = (Button) findViewById(R.id.btn_done);
        btnSave.setOnClickListener(this);


        mEdTxtOwnerName = (EditText) findViewById(R.id.edtxt_owner_name);
        mEdTxtDriverName = (EditText) findViewById(R.id.edtxt_driver_name);
        mEdTxtDiverMobileNumber = (EditText) findViewById(R.id.edtxt_driver_number);
        mEdTxtLicenceNumber = (EditText) findViewById(R.id.edtxt_licence_number);
        mEdTxtVehicleNumber = (EditText) findViewById(R.id.edtxt_vehicle_number);
        mEdTxtUniqueAutoNumber = (EditText) findViewById(R.id.edtxt_auto_unique_number);
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_done) {
            if (isDataValid()) {
                PreferenceManager preferenceManager = PreferenceManager.getInstance(AddDriverDetailsActivity.this);
                preferenceManager.putOwnerName(mEdTxtOwnerName.getText().toString().trim());
                preferenceManager.putDriverMobileNumber(mEdTxtDiverMobileNumber.getText().toString().trim());
                preferenceManager.putDriverName(mEdTxtDriverName.getText().toString().trim());
                preferenceManager.putLicenceNumber(mEdTxtLicenceNumber.getText().toString().trim());
                preferenceManager.putVehicleNumber(mEdTxtVehicleNumber.getText().toString().trim());
                preferenceManager.putUniqueAutoNumber(mEdTxtUniqueAutoNumber.getText().toString().trim());

                Intent intent = new Intent(AddDriverDetailsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Snackbar.make(view, R.string.fill_all_details, Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private boolean isDataValid() {
        return !(TextUtils.isEmpty(mEdTxtOwnerName.getText().toString())
                || TextUtils.isEmpty(mEdTxtDriverName.getText().toString())
                || TextUtils.isEmpty(mEdTxtDiverMobileNumber.getText().toString())
                || TextUtils.isEmpty(mEdTxtLicenceNumber.getText().toString())
                || TextUtils.isEmpty(mEdTxtUniqueAutoNumber.getText().toString())
                || TextUtils.isEmpty(mEdTxtVehicleNumber.getText().toString()));
    }
}
