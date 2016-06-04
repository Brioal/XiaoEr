package com.xiaoer.xiaoer.fragment;

import com.xiaoer.xiaoer.R;
import com.xiaoer.xiaoer.base.BaseFragment;

/**
 * Created by Brioal on 2016/6/4.
 */
public class UserFragment extends BaseFragment {
    public static UserFragment mFragment ;

    public static synchronized UserFragment newInstance() {
        if (mFragment == null) {
            mFragment = new UserFragment();
        }
        return mFragment;
    }



    @Override
    public void initView() {
        mRootView=inflater.inflate(R.layout.fragment_home, container, false);
    }

}
