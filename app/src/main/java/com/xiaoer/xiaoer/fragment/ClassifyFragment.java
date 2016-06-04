package com.xiaoer.xiaoer.fragment;

import com.xiaoer.xiaoer.R;
import com.xiaoer.xiaoer.base.BaseFragment;

/**
 * Created by Brioal on 2016/6/4.
 */
public class ClassifyFragment extends BaseFragment {
    public static ClassifyFragment mFragment ;

    public static synchronized ClassifyFragment newInstance() {
        if (mFragment == null) {
            mFragment = new ClassifyFragment();
        }

        return mFragment;
    }
    @Override
    public void initView() {
        super.initView();
        mRootView=inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void loadData() {
        super.loadData();
    }

    @Override
    public void setView() {
        super.setView();
    }
}
