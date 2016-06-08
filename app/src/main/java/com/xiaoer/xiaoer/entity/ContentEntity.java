package com.xiaoer.xiaoer.entity;

import cn.bmob.v3.BmobObject;

/**
 * 物品展示的实体类
 * Created by Brioal on 2016/6/5.
 */
public class ContentEntity extends BmobObject {
    private String mUserId; //用户的ID
    private String mDesc; //物品的描述
    private int mNowPrice; //现价
    private int mOldPrice; //原价
    private String mClassifyId; //所属分类的id
    private int mComment; //评论数量
    private int mPraise; //点赞数量
    private int mCollect; //收藏数量
    private int mRead; //浏览数量
    private long mTime; //发布时间
    private String mLocation; //定位名称

    private String mUserHeadUrl; //用户的头像
    private String mUserName; //用户的名称
    private String mClassifyTitle; //所属的分类


    public ContentEntity() {
    }

    public ContentEntity(String mUserId, String mDesc, int mNowPrice, int mOldPrice, String mClassifyId, int mComment, int mPraise, int mCollect, int mRead, long mTime, String mLocation, String mUserHeadUrl, String mUserName, String mClassifyTitle) {
        this.mUserId = mUserId;
        this.mClassifyTitle = mClassifyTitle;
        this.mDesc = mDesc;
        this.mNowPrice = mNowPrice;
        this.mClassifyId = mClassifyId;
        this.mOldPrice = mOldPrice;
        this.mComment = mComment;
        this.mPraise = mPraise;
        this.mCollect = mCollect;
        this.mRead = mRead;
        this.mTime = mTime;
        this.mLocation = mLocation;
        this.mUserHeadUrl = mUserHeadUrl;
        this.mUserName = mUserName;
    }

    public String getmClassifyId() {
        return mClassifyId;
    }

    public void setmClassifyId(String mClassifyId) {
        this.mClassifyId = mClassifyId;
    }

    public String getmClassifyTitle() {
        return mClassifyTitle;
    }

    public void setmClassifyTitle(String mClassifyTitle) {
        this.mClassifyTitle = mClassifyTitle;
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public int getmNowPrice() {
        return mNowPrice;
    }

    public void setmNowPrice(int mNowPrice) {
        this.mNowPrice = mNowPrice;
    }

    public int getmOldPrice() {
        return mOldPrice;
    }

    public void setmOldPrice(int mOldPrice) {
        this.mOldPrice = mOldPrice;
    }

    public int getmComment() {
        return mComment;
    }

    public void setmComment(int mComment) {
        this.mComment = mComment;
    }

    public int getmPraise() {
        return mPraise;
    }

    public void setmPraise(int mPraise) {
        this.mPraise = mPraise;
    }

    public int getmCollect() {
        return mCollect;
    }

    public void setmCollect(int mCollect) {
        this.mCollect = mCollect;
    }

    public int getmRead() {
        return mRead;
    }

    public void setmRead(int mRead) {
        this.mRead = mRead;
    }

    public long getmTime() {
        return mTime;
    }

    public void setmTime(long mTime) {
        this.mTime = mTime;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }


    public String getmUserHeadUrl() {
        return mUserHeadUrl;
    }

    public void setmUserHeadUrl(String mUserHeadUrl) {
        this.mUserHeadUrl = mUserHeadUrl;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }
}
