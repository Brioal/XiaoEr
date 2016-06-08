package com.xiaoer.xiaoer.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageButton;

import com.xiaoer.lib.util.NetWorkUtil;
import com.xiaoer.xiaoer.adapter.HomeContentAdapter;
import com.xiaoer.xiaoer.view.TextViewAd;
import com.xiaoer.xiaoer.R;
import com.xiaoer.xiaoer.base.BaseFragment;
import com.xiaoer.xiaoer.entity.BannerEntity;
import com.xiaoer.xiaoer.entity.ContentEntity;
import com.xiaoer.xiaoer.entity.HomeTip;
import com.xiaoer.xiaoer.util.Constants;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Brioal on 2016/6/4.
 */
public class HomeFragment extends BaseFragment {
    public static HomeFragment mFragment;
    @Bind(R.id.fragment_home_queryTv)
    TextViewAd mQueryTv;
    @Bind(R.id.fragment_home_msg)
    ImageButton mBtnMsg;
    @Bind(R.id.fragment_home_recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.fragment_home_refreshLayout)
    SwipeRefreshLayout mRefreshLayout;

    private List<HomeTip> mTips;
    private List<BannerEntity> mBanners;
    private List<ContentEntity> mContents;
    private HomeContentAdapter mAdapter;

    public static synchronized HomeFragment newInstance() {
        if (mFragment == null) {
            mFragment = new HomeFragment();
        }

        return mFragment;
    }

    @Override
    public void setView() {
        super.setView();
        if (mTips.size() > 0) {
            mQueryTv.setTexts(mTips);
            mQueryTv.invalidate();
        }
        mAdapter = new HomeContentAdapter(mContext, mBanners, mContents);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void initView() {
        super.initView();
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, mRootView);
        mRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(mRunnable).start();
            }
        });
    }

    @Override
    public void loadData() {
        super.loadData();
        mTips = Constants.getmDataLoader(mContext).getHomeTipLocal();
        mBanners = Constants.getmDataLoader(mContext).getBannersLocal();
        mContents = Constants.getmDataLoader(mContext).getContentLocal();
        mHandler.sendEmptyMessage(0);
        if (NetWorkUtil.isNetworkConnected(mContext)) {
            Constants.getmDataLoader(mContext).getHomeTipNet(new FindListener<HomeTip>() {
                @Override
                public void onSuccess(final List<HomeTip> list) {
                    Log.i(TAG, "onSuccess: 首页Tip加载成功" + list.size());
                    mTips = list;
                    mHandler.sendEmptyMessage(0);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Constants.getmDataLoader(mContext).saveHomeTipLocal(mTips);
                        }
                    }).start();
                }

                @Override
                public void onError(int i, String s) {
                    Log.i(TAG, "onError: 加载HomeTip失败" + s);
                }
            });

            Constants.getmDataLoader(mContext).getBannerNet(new FindListener<BannerEntity>() {
                @Override
                public void onSuccess(List<BannerEntity> list) {
                    Log.i(TAG, "onSuccess: 加载Banner成功" + list.size());
                    mBanners = list;
                    mHandler.sendEmptyMessage(0);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Constants.getmDataLoader(mContext).saveBanner(mBanners);
                        }
                    }).start();
                }

                @Override
                public void onError(int i, String s) {
                    Log.i(TAG, "onError: Banner加载失败" + s);
                }
            });

            Constants.getmDataLoader(mContext).getContentNet(new FindListener<ContentEntity>() {
                @Override
                public void onSuccess(List<ContentEntity> list) {
                    Log.i(TAG, "onSuccess: 加载首页数据成功" + list.size());
                    mContents = list;
                    mHandler.sendEmptyMessage(0);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Constants.getmDataLoader(mContext).saveContentLocal(mContents);
                        }
                    }).start();
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
