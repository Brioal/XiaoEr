package com.xiaoer.xiaoer;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
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
        Uri path = (new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.launcher_bg)).build();
        DraweeController draweeController= Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setUri(path)
                .build();
        gifView.setController(draweeController);


    }

    @Override
    public void loadData() {
        super.loadData();
        mHandler.sendEmptyMessage(0);

    }

}
