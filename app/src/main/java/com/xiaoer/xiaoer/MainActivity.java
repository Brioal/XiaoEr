package com.xiaoer.xiaoer;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.RelativeLayout;

import com.xiaoer.lib.bottomtablayout.adapter.BottomBaseAdapter;
import com.xiaoer.lib.bottomtablayout.view.BottomTabLayout;
import com.xiaoer.lib.util.StatusBarUtils;
import com.xiaoer.xiaoer.base.BaseActivity;
import com.xiaoer.xiaoer.fragment.CartFragmet;
import com.xiaoer.xiaoer.fragment.ClassifyFragment;
import com.xiaoer.xiaoer.fragment.HomeFragment;
import com.xiaoer.xiaoer.fragment.UserFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_container)
    RelativeLayout mContainer;
    @Bind(R.id.main_bottomTabLayout)
    BottomTabLayout mTabLayout;


    private FragmentManager manager;
    private String[] texts = new String[]{
            "首页",
            "分类",
            "购物车",
            "个人中心"
    };

    @Override
    public void initData() {
        super.initData();
        TAG = "MainInfo";
    }

    @Override
    public void loadData() {
        super.loadData();
        mHandler.sendEmptyMessage(0);
    }

    @Override
    public void initTheme() {
        super.initTheme();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        manager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTab();
    }

    @Override
    public void setView() {
        manager.beginTransaction().add(R.id.main_container, HomeFragment.newInstance()).commit();
        StatusBarUtils.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    private void initTab() {
        final Drawable[] images = new Drawable[]{
                getResources().getDrawable(R.mipmap.ic_home),
                getResources().getDrawable(R.mipmap.ic_classify),
                getResources().getDrawable(R.mipmap.ic_cart),
                getResources().getDrawable(R.mipmap.ic_user)
        };
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setmCurrentCheck(0);
        mTabLayout.setSelectItem(new BottomTabLayout.onSelectItem() {
            @Override
            public void onSelectedItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = HomeFragment.newInstance();
                        break;
                    case 1:
                        fragment = ClassifyFragment.newInstance();
                        break;
                    case 2:
                        fragment = CartFragmet.newInstance();
                        break;
                    case 3:
                        fragment = UserFragment.newInstance();
                        break;
                }
                changeFragment(fragment);
            }
        });
        mTabLayout.setmCheckedPerscent(1.4f);
        mTabLayout.setmCheckedColor(getResources().getColor(R.color.colorPrimary));
        mTabLayout.setmNormalColor(Color.GRAY);
        mTabLayout.setmAdapter(new BottomBaseAdapter() {
            @Override
            public int getItemCount() {
                return texts.length;
            }

            @Override
            public Drawable getDrawable(int position) {
                return images[position];
            }

            @Override
            public CharSequence getText(int position) {
                return texts[position];
            }
        });
    }

    public void changeFragment(Fragment fragment) {
        if (fragment.isAdded()) {
            manager.beginTransaction().replace(mContainer.getId(), fragment).commit();
        } else {
            manager.beginTransaction().add(mContainer.getId(), fragment).commit();
        }
    }
}
