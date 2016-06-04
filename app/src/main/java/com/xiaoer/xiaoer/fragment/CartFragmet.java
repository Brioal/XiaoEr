package com.xiaoer.xiaoer.fragment;

import com.xiaoer.xiaoer.R;
import com.xiaoer.xiaoer.base.BaseFragment;

/**
 * Created by Brioal on 2016/6/4.
 */
public class CartFragmet extends BaseFragment {
    public static CartFragmet mFragment ;

    public static synchronized CartFragmet newInstance() {
        if (mFragment == null) {
            mFragment = new CartFragmet();
        }
        return mFragment;
    }
    @Override
    public void initView() {
        super.initView();
        mRootView=inflater.inflate(R.layout.fragment_home, container, false);
    }
}
