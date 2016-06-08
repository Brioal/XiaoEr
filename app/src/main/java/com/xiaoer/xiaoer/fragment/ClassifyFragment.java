package com.xiaoer.xiaoer.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.xiaoer.lib.util.NetWorkUtil;
import com.xiaoer.lib.util.ToastUtils;
import com.xiaoer.xiaoer.R;
import com.xiaoer.xiaoer.adapter.ClassifyAdapter;
import com.xiaoer.xiaoer.base.BaseFragment;
import com.xiaoer.xiaoer.entity.ClassifyEntity;
import com.xiaoer.xiaoer.util.Constants;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Brioal on 2016/6/4.
 */
public class ClassifyFragment extends BaseFragment {
    public static ClassifyFragment mFragment;
    @Bind(R.id.item_toolBar)
    Toolbar mToolBar;
    @Bind(R.id.classify_recyclerView)
    RecyclerView mRecyclerView;
    private List<ClassifyEntity> mList;
    private ClassifyAdapter mAdapter;
    protected String TAG = "ClassifyInfo";

    public static synchronized ClassifyFragment newInstance() {
        if (mFragment == null) {
            mFragment = new ClassifyFragment();
        }
        return mFragment;
    }

    @Override
    public void initView() {
        super.initView();
        mRootView = inflater.inflate(R.layout.fragment_classify, container, false);
        ButterKnife.bind(this, mRootView);
        mToolBar.setTitle("分类选择");
        mToolBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void loadData() {
        mList = Constants.getmDataLoader(mContext).getClassifies();
        if (mList.size() > 0) {
            mHandler.sendEmptyMessage(0);
        }
        if (NetWorkUtil.isNetworkConnected(mContext)) {
            Constants.getmDataLoader(mContext).getNetClassifues(new FindListener<ClassifyEntity>() {
                @Override
                public void onSuccess(List<ClassifyEntity> list) {
                    Log.i(TAG, "onSuccess: 加载分类成功");
                    mList = list;
                    if (mList.size() > 0) {
                        mHandler.sendEmptyMessage(0);
                    }
                   new Thread(new Runnable() {
                       @Override
                       public void run() {
                           Constants.getmDataLoader(mContext).saveClassifies(mList);
                       }
                   }).start();
                }

                @Override
                public void onError(int i, String s) {
                    Log.i(TAG, "onError: 加载分类失败" + s);
                    ToastUtils.showToast(mContext, s);
                }
            });
        }

    }

    @Override
    public void setView() {
//        if (mAdapter == null) {
            mAdapter = new ClassifyAdapter(mContext, mList);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mRecyclerView.setAdapter(mAdapter);
//        } else {
//            mAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
