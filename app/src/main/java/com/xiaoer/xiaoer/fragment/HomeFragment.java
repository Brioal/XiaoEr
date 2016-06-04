package com.xiaoer.xiaoer.fragment;

import com.xiaoer.xiaoer.R;
import com.xiaoer.xiaoer.base.BaseFragment;

/**
 * Created by Brioal on 2016/6/4.
 */
public class HomeFragment extends BaseFragment {
    public static HomeFragment mFragment;

    public static synchronized HomeFragment newInstance() {
        if (mFragment == null) {
            mFragment = new HomeFragment();
        }

        return mFragment;
    }

    @Override
    public void initView() {
        super.initView();
        mRootView=inflater.inflate(R.layout.fragment_home, container, false);
    }
}
