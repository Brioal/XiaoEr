package com.xiaoer.xiaoer.actiity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaoer.xiaoer.MainActivity;
import com.xiaoer.xiaoer.R;
import com.xiaoer.xiaoer.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LauncherActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.launcher_draweeView)
    SimpleDraweeView mGifView;
    @Bind(R.id.launcher_btnIn)
    Button mBtnIn;
    private DraweeController draweeController;


    @Override
    public void initBar() {
        super.initBar();
    }

    @Override
    public void initTheme() {
        setTheme(R.style.AppTheme_NoActionBar);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Fresco.initialize(mContext);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    @Override
    public void setView() {
        mGifView.post(new Runnable() {
            @Override
            public void run() {
                Uri path = (new Uri.Builder()).scheme("res").path(String.valueOf(R.mipmap.ic_welcome)).build();
                draweeController = Fresco.newDraweeControllerBuilder()
                        .setAutoPlayAnimations(true)
                        .setUri(path)
                        .build();
                mGifView.setController(draweeController);
            }
        });
        mBtnIn.setOnClickListener(this);
    }

    @Override
    public void loadData() {
        mHandler.sendEmptyMessage(0);
    }


    @Override
    public void onClick(View v) {
        Animatable animatable=draweeController.getAnimatable();
        //判断是否正在运行
        if (animatable.isRunning()){
            //运行中，停止
            animatable.stop();
        }
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
