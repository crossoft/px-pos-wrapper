package com.example.dharmaniz.jposremotemanagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dharmaniz on 31/8/18.
 */

public class RemoteManagementBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpViews();
        setUpEvents();
        setUpDataToViews();
    }

    // Data will be set here
    protected void setUpDataToViews() {
    }

    // Views will be initialized here
    protected void setUpViews() {
    }

    //Events will be captured here
    protected void setUpEvents() {
    }
}
