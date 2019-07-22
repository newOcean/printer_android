package com.example.doulai.demo;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.doulai.printersdk.R;


/**
 * Created by
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    protected View actionBarView;

    protected ActionBar bar;

    protected TextView tvTitle;

    protected TextView tvActBarLeft;

    protected TextView tvActBarRight;

    protected Context mContext;

    protected TextView tvActBarThree;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //自定义actionBar
        mContext = this;
        initData();
        bar = getSupportActionBar();
        bar.setDisplayShowCustomEnabled(true);
        actionBarView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_header, null);
        bar.setCustomView(actionBarView, new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
        //自定义actionBar
        tvTitle = (TextView) actionBarView.findViewById(R.id.tv_title);
        tvActBarLeft = (TextView) actionBarView.findViewById(R.id.tv_actbar_left);
        tvActBarRight = (TextView) actionBarView.findViewById(R.id.tv_actbar_right);
        tvActBarThree = (TextView) actionBarView.findViewById(R.id.tv_acbar_three);

        tvTitle.setText(getPageTite());
        tvActBarLeft.setText(getPageLeft());
        tvActBarRight.setText(getPageRight());


    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    protected abstract String getPageTite();

    protected abstract String getPageLeft();

    protected abstract String getPageRight();

    protected void initData(){

    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics());
        return res;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            afterRequestPermisson(requestCode);
        }else {
            afterDeniedPermisson(requestCode);
        }
    }

    public boolean isPermmisonAllowed(String permmison){
        return ContextCompat.checkSelfPermission(getApplicationContext(),permmison) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermmison(String permmsion ,int requestCode){
        ActivityCompat.requestPermissions(this,new String[]{permmsion,},requestCode);
    }


    public abstract void afterRequestPermisson(int requestCode);

    public abstract void afterDeniedPermisson(int requestCode);
}
