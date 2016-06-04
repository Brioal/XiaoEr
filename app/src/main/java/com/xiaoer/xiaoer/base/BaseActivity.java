package com.xiaoer.xiaoer.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xiaoer.xiaoer.interfaces.ActivityFormat;

/**
 * Created by mm on 2016/6/4.
 */

public class BaseActivity extends AppCompatActivity implements ActivityFormat {
    protected String TAG = "BaseActivity";
    protected Activity mContext;
    protected Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            initData();
            loadData();
        }
    };
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setView();
            Log.i(TAG, "handleMessage: ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
        mContext = this;
        initView(savedInstanceState);
        initBar();
        new Thread(mRunnable).start();
        Log.i(TAG, "onCreate:. ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override

    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void initBar() {

    }

    @Override
    public void initTheme() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void setView() {

    }


}
