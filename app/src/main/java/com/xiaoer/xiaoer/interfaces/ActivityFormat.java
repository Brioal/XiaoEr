package com.xiaoer.xiaoer.interfaces;

import android.os.Bundle;

/**
 * Activity的格式化接口
 * Created by Brioal on 2016/6/4.
 */

public interface ActivityFormat {
    void initBar();//初始化ToolBar

    void initTheme(); //初始化主题设置

    void initData(); //初始化数据

    void loadData(); //加载数据

    void initView(Bundle savedInstanceState); //初始化View

    void setView(); //设置View

}
