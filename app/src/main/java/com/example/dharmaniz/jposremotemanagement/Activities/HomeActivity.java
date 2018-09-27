package com.example.dharmaniz.jposremotemanagement.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dharmaniz.jposremotemanagement.R;
import com.example.dharmaniz.jposremotemanagement.RemoteManagementBaseActivity;
import com.example.dharmaniz.jposremotemanagement.RemotePreference.RemoteSharedPreference;
import com.example.dharmaniz.jposremotemanagement.Util.AlertDialogManager;

public class HomeActivity extends RemoteManagementBaseActivity {
Activity mActivity =HomeActivity.this;
private WebView webURLWV;
private ProgressBar progressBarPB;
private RelativeLayout logoutRL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Get webview



    }

    @Override
    protected void setUpViews() {
        webURLWV=findViewById(R.id.webURLWV);
        logoutRL=findViewById(R.id.logoutRL);
        progressBarPB=findViewById(R.id.progressBarPB);
        progressBarPB.setVisibility(View.VISIBLE);
        String GoogleDocs = "http://dharmani.com/";
        webURLWV.getSettings().setJavaScriptEnabled(true);
        webURLWV.getSettings().setLoadWithOverviewMode(true);
        webURLWV.getSettings().setUseWideViewPort(true);
        webURLWV.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressBarPB.setVisibility(View.VISIBLE);
                String temp  = url.toString();
                Log.d("TAG", temp);
              if (url.startsWith(WebView.SCHEME_TEL) ||
                      url.startsWith("sms:") ||
                      url.startsWith(WebView.SCHEME_MAILTO) ||
                      url.startsWith(WebView.SCHEME_GEO) ||
                      url.startsWith("maps:"))
                {
                    try {
                        Log.d("TAG", "loading in dialer");
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    } catch (android.content.ActivityNotFoundException e) {
//                        LOG.e(LOG_TAG, "Error dialing " + url + ": " + e.toString());
                    }
                }
                else if(url.contains("skype")){
                  if(isSkypeClientInstalled(mActivity)) {
                      Log.d("TAG12", "loading in hai");
                      Intent intent = new Intent(Intent.ACTION_VIEW);
                      intent.setData(Uri.parse(url));
                      startActivity(intent);
                  }else {
                      AlertDialogManager.showAlertDialog(mActivity, "Error", "Please Install Skype First");

                      Log.d("TAG12", "loading in  nai Hai");
                  }
//                  Log.d("TAG12", "loading in dialer");

              }
                else {
                  view.loadUrl(url);
              }

                return true;
            }
            @Override
            public void onPageFinished(WebView view, final String url) {
                progressBarPB.setVisibility(View.GONE);
            }
        });
        webURLWV.loadUrl(GoogleDocs);
    }

    @Override
    protected void setUpEvents() {
        logoutRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(mActivity,view);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.logout_btn:
                                Intent intent = new Intent(mActivity, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                SharedPreferences preferences = RemoteSharedPreference.getPreferences(mActivity);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.commit();

//                        Toast.makeText(mActivity,"item1",Toast.LENGTH_SHORT).show();
                                // EX : call intent if you want to swich to other activity
                                return true;



                        }
                        return false;
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.option_logout, popup.getMenu());
                popup.show();


            }
        });


    }

    @Override
    public void onBackPressed() {
        if(webURLWV.canGoBack()) {
            webURLWV.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
            finish();
        }
    }

    public boolean isSkypeClientInstalled(Context myContext) {
        PackageManager myPackageMgr = myContext.getPackageManager();
        try {
            myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES);
        }
        catch (PackageManager.NameNotFoundException e) {
            return (false);
        }
        return (true);
    }
}


