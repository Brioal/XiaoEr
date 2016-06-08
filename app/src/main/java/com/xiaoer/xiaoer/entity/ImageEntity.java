package com.xiaoer.xiaoer.entity;

import android.content.Context;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**图片的实体类
 * Created by Brioal on 2016/6/5.
 */
public class ImageEntity extends BmobObject {
    private String mContentId; //所属内容的id
    private int mId; //图片的顺序
    private BmobFile mPic; //图片

    private String mPicUrl; //图片的url

    public ImageEntity(String mContentId, int mId, String mPicUrl) {
        this.mId = mId;
        this.mContentId = mContentId;
        this.mPicUrl = mPicUrl;
    }

    public String getmContentId() {
        return mContentId;
    }

    public void setmContentId(String mContentId) {
        this.mContentId = mContentId;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmPicUrl(Context context) {
        return mPicUrl==null?mPic.getFileUrl(context):mPicUrl;
    }

    public void setmPicUrl(String mPicUrl) {
        this.mPicUrl = mPicUrl;
    }
}
