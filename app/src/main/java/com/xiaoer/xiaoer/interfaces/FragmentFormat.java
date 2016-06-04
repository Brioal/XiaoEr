package com.xiaoer.xiaoer.interfaces;

/**格式化Fragment的接口
 * Created by Brioal on 2016/6/4.
 */

public interface  FragmentFormat {
    void initData(); //初始化数据

    void initView(); //初始化 布局

    void loadData(); // 加载数据

    void setView(); // 数据显示到布局

}
