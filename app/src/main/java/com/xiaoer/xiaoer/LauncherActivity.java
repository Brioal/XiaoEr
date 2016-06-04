package com.xiaoer.xiaoer;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaoer.xiaoer.base.BaseActivity;

public class LauncherActivity extends BaseActivity {
    private SimpleDraweeView gifView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_launcher);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initBar() {
        super.initBar();
    }

    @Override
    public void initTheme() {
        super.initTheme();
        setTheme(R.style.AppTheme_NoActionBar);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void initView() {
        super.initView();
        gifView= (SimpleDraweeView) findViewById(R.id.launcher_draweeView);
    }


    @Override
    public void setView() {
        super.setView();



    }

    @Override
    public void loadData() {
        super.loadData();
        mHandler.sendEmptyMessage(0);

    }

}
