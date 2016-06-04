package com.xiaoer.xiaoer;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaoer.xiaoer.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LauncherActivity extends BaseActivity {

    @Bind(R.id.launcher_draweeView)
    SimpleDraweeView mGifView;
    @Bind(R.id.launcher_tv)
    TextView launcherTv;


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
                Uri path = (new Uri.Builder()).scheme("res").path(String.valueOf(R.drawable.water)).build();
                DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setAutoPlayAnimations(true)
                        .setUri(path)
                        .build();
                mGifView.setController(draweeController);
            }
        }) ;

        launcherTv.setText("123");
    }

    @Override
    public void loadData() {
        mHandler.sendEmptyMessage(0);
    }


}
