package com.example.dharmaniz.jposremotemanagement.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dharmaniz.jposremotemanagement.R;
import com.example.dharmaniz.jposremotemanagement.RemoteManagementApplication;
import com.example.dharmaniz.jposremotemanagement.RemoteManagementBaseActivity;
import com.example.dharmaniz.jposremotemanagement.RemotePreference.RemoteSharedPreference;
import com.example.dharmaniz.jposremotemanagement.Util.AlertDialogManager;
import com.example.dharmaniz.jposremotemanagement.Util.Constants;
import com.example.dharmaniz.jposremotemanagement.Util.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends RemoteManagementBaseActivity {
    Activity mActivity = LoginActivity.this;
    private TextView forgetTV, logingTV, createTV;
    private EditText passwordET, usernameET;
View view2,view1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (!RemoteSharedPreference.readString(mActivity, RemoteSharedPreference.USER_ID, "").equals("")) {
            Intent mIntent = new Intent(mActivity, HomeActivity.class);
            startActivity(mIntent);
            finish();
        }
    }

    @Override
    protected void setUpViews() {
        forgetTV = findViewById(R.id.forgetTV);
        logingTV = findViewById(R.id.logingTV);
        createTV = findViewById(R.id.createTV);
        passwordET = findViewById(R.id.passwordET);
        usernameET = findViewById(R.id.usernameET);
        view2 = findViewById(R.id.view2);
        view1 = findViewById(R.id.view1);

    }

    @Override
    protected void setUpEvents() {
//        passwordET.setOnTouchListener(new View.OnTouchListener() {
//            @SuppressLint("NewApi")
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                view2.setBackground(getDrawable(R.drawable.stroke_yellow));
//                view1.setBackground(getDrawable(R.drawable.stroke));
//                return true;
//            }
//        });
//
//
//        usernameET.setOnTouchListener(new View.OnTouchListener() {
//            @SuppressLint("NewApi")
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                view1.setBackground(getDrawable(R.drawable.stroke_yellow));
//                view2.setBackground(getDrawable(R.drawable.stroke));
//                return true;
//            }
//        });


        passwordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("NewApi")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                view2.setBackground(getDrawable(R.drawable.stroke_yellow));
                view1.setBackground(getDrawable(R.drawable.stroke));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        usernameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("NewApi")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                view1.setBackground(getDrawable(R.drawable.stroke_yellow));
                view2.setBackground(getDrawable(R.drawable.stroke));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        logingTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernameET.getText().toString().equals("")) {
                    AlertDialogManager.showAlertDialog(mActivity, "", "Please enter your username.");
                } else if (passwordET.getText().toString().equals("")) {
                    AlertDialogManager.showAlertDialog(mActivity, "", "Please enter your password.");
                } else {
                    LoginUserAPI();
                }
            }
        });

    }

    private void LoginUserAPI() {
        Utilities.showProgressDialog(mActivity);
        String url = Constants.Login_;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", "vitoscaletta@gmail.com");
            jsonObject.put("password", "qwerty");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Utilities.hideProgressDialog();
                        try {
                            if (response.getString("Status").equals("1")) {
                                RemoteSharedPreference.writeString(mActivity, RemoteSharedPreference.USER_ID, "abc");
                                Intent mIntent = new Intent(mActivity, HomeActivity.class);
                                startActivity(mIntent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("", response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utilities.hideProgressDialog();
                VolleyLog.d("", "Error: " + error.getMessage());

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
////      Adding request to request queue
        RemoteManagementApplication.getInstance().addToRequestQueue(jsonObjReq, "LoginAPI");
    }
}

