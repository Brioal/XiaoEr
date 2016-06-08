package com.xiaoer.xiaoer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.xiaoer.xiaoer.entity.HomeTip;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Brioal on 2016/5/28.
 */

public class TextViewAd extends TextView {
    private int mDuration; //文字从出现到显示消失的时间
    private int mInterval; //文字停留在中间的时长切换的间隔
    private List<HomeTip> mTexts; //显示文字的数据源
    private int mY = 0; //文字的Y坐标
    private int mIndex = 0; //当前的数据下标
    private Paint mPaintBack; //绘制内容的画笔
    private boolean isMove = true; //文字是否移动
    private String TAG = "ADTextView";
    private boolean hasInit = false;

    public TextViewAd(Context context) {
        this(context, null);
    }

    public TextViewAd(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //设置数据源
    public void setTexts(List<HomeTip> mTexts) {
        this.mTexts = mTexts;
    }

    //初始化默认值
    private void init() {
        mDuration = 70;
        mInterval = 3000;
        mIndex = 0;
        mPaintBack = new Paint();
        mPaintBack.setAntiAlias(true);
        mPaintBack.setDither(true);
        mPaintBack.setTextSize(30);
        mPaintBack.setColor(Color.WHITE);
        mTexts = new ArrayList<>();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged: " + h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mTexts != null&&mTexts.size()>0) {
            Log.i(TAG, "onDraw: " + mY);
            String back = mTexts.get(mIndex).getmTip();
            //绘制前缀
            //绘制内容文字
            Rect contentBound = new Rect();
            mPaintBack.getTextBounds(back, 0, back.length(), contentBound);
            if (mY == 0 && hasInit == false) {
                mY = getMeasuredHeight() - contentBound.top;
                hasInit = true;
            }
            //移动到最上面
            if (mY == 0 - contentBound.bottom) {
                Log.i(TAG, "onDraw: " + getMeasuredHeight());
                mY = getMeasuredHeight() - contentBound.top;
                mIndex++;
            }
            canvas.drawText(back, 0, back.length(), 100, mY, mPaintBack);
            //移动到中间
            if (mY == getMeasuredHeight() / 2 - (contentBound.top + contentBound.bottom) / 2) {
                isMove = false;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        postInvalidate();
                        isMove = true;
                    }
                }, mInterval);
            }
            mY -= 1;
            //循环使用数据
            if (mIndex == mTexts.size()) {
                mIndex = 0;
            }
            //如果是处于移动状态时的,则延迟绘制
            //计算公式为一个比例,一个时间间隔移动组件高度,则多少毫秒来移动1像素
            if (isMove) {
                postInvalidateDelayed(mDuration / getMeasuredHeight());
            }
        }

    }
}
