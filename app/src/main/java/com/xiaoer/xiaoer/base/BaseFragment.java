package com.xiaoer.xiaoer.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoer.xiaoer.interfaces.FragmentFormat;

/**Fragment基类
 * Created by mm on 2016/6/4.
 */

public class BaseFragment extends Fragment implements FragmentFormat {
    protected Activity mContext;
    protected View mRootView;

    protected Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            loadData();      //加载数据
        }
    };

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setView(); //数据显示到布局
        }
    };

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initFragmentView(inflater, container, savedInstanceState);
        return mRootView;
    }

    //实例化总布局
    protected void initFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void setView() {

    }
}
