package com.xiaoer.xiaoer.entity;

import android.content.Context;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 首页轮播图的实体类
 * Created by Brioal on 2016/6/5.
 */
public class BannerEntity extends BmobObject {
    private String mTip; //提示
    private BmobFile mPic; //图片
    private String mContentId; //内容的iD

    private String mPicUrl; //图片的连接

    public BannerEntity(String mTip, String mContentId, String mPicUrl) {
        this.mTip = mTip;
        this.mContentId = mContentId;
        this.mPicUrl = mPicUrl;
    }

    public String getmTip() {
        return mTip;
    }

    public void setmTip(String mTip) {
        this.mTip = mTip;
    }

    public BmobFile getmPic() {
        return mPic;
    }

    public void setmPic(BmobFile mPic) {
        this.mPic = mPic;
    }

    public String getmContentId() {
        return mContentId;
    }

    public void setmContentId(String mContentId) {
        this.mContentId = mContentId;
    }

    public String getmPicUrl(Context context) {
        return mPicUrl == null ? mPic.getFileUrl(context) : mPicUrl;
    }

    public void setmPicUrl(String mPicUrl) {
        this.mPicUrl = mPicUrl;
    }
}
