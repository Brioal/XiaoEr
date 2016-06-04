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
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);

    }

    @Override
    public void initBar() {
        super.initBar();
    }

    @Override
    public void initTheme() {
        super.initTheme();

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_launcher);
        gifView= (SimpleDraweeView) findViewById(R.id.launcher_draweeView);
    }


    @Override
    public void setView() {
        super.setView();
        playGif();


    }

    @Override
    public void loadData() {
        super.loadData();
        mHandler.sendEmptyMessage(0);

    }
    private void playGif(){
        Uri path = (new Uri.Builder()).scheme("res").path(String.valueOf(R.mipmap.water)).build();
        DraweeController draweeController= Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setUri(path)
                .build();
        gifView.setController(draweeController);
    }

}
