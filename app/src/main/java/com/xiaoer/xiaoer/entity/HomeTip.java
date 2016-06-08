package com.xiaoer.xiaoer.entity;

import cn.bmob.v3.BmobObject;

/**首页搜索框内的文字
 * Created by Brioal on 2016/6/5.
 */
public class HomeTip extends BmobObject {
    private String mTip ;

    public HomeTip(String mTip) {
        this.mTip = mTip;
    }

    public String getmTip() {
        return mTip;
    }

    public void setmTip(String mTip) {
        this.mTip = mTip;
    }
}
