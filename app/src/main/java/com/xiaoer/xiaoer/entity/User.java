package com.xiaoer.xiaoer.entity;

import android.content.Context;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Brioal on 2016/6/4.
 */

public class User extends BmobUser {
    private BmobFile mHead ; //头像


    private String mHeadUrl ; //头像的连接


    public BmobFile getmHead() {
        return mHead;
    }

    public void setmHead(BmobFile mHead) {
        this.mHead = mHead;
    }

    public String getmHeadUrl(Context context) {
        return mHeadUrl==null?mHead.getFileUrl(context):mHeadUrl;
    }

    public void setmHeadUrl(String mHeadUrl) {
        this.mHeadUrl = mHeadUrl;
    }
}
