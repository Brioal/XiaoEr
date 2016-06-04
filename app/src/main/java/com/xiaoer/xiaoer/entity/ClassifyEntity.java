package com.xiaoer.xiaoer.entity;

import android.content.Context;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**分类的实体类
 * Created by Brioal on 2016/6/4.
 */

public class ClassifyEntity extends BmobObject {
    private int  mId; //用于排序的分类
    private String mParent;// 所属的大分类
    private BmobFile mPic; //分类的图片
    private String mContent; //分类的名称

    private String mPicUrl; //本地存储的分类的图片链接

    public ClassifyEntity(int mId, String mParent, String mContent, String mPicUrl) {
        this.mId = mId;
        this.mParent = mParent;
        this.mContent = mContent;
        this.mPicUrl = mPicUrl;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmParent() {
        return mParent;
    }

    public void setmParent(String mParent) {
        this.mParent = mParent;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmPicUrl(Context context) {
        return mPicUrl == null ? mPic.getFileUrl(context) : mPicUrl;
    }

    public void setmPicUrl(String mPicUrl) {
        this.mPicUrl = mPicUrl;
    }
}
