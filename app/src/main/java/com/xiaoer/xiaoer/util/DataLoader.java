package com.xiaoer.xiaoer.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xiaoer.xiaoer.database.DBHelper;
import com.xiaoer.xiaoer.entity.BannerEntity;
import com.xiaoer.xiaoer.entity.ClassifyEntity;
import com.xiaoer.xiaoer.entity.ContentEntity;
import com.xiaoer.xiaoer.entity.HomeTip;
import com.xiaoer.xiaoer.entity.ImageEntity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.listener.FindListener;

/**
 * 数据加载类
 * Created by Brioal on 2016/6/4.
 */

public class DataLoader {
    private Context mContext;
    private DBHelper mHelper;

    public DataLoader(Context mContext) {
        this.mContext = mContext;
        mHelper = new DBHelper(mContext, "XiaoEr.db3", null, 1);
    }

    //读取本地的分类信息
    public List<ClassifyEntity> getClassifies() {
        List<ClassifyEntity> entities = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Classify", null);
        while (cursor.moveToNext()) {
            ClassifyEntity entity = new ClassifyEntity(cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            entity.setObjectId(cursor.getString(5));
            entities.add(entity);
        }
        cursor.close();
        return entities;
    }

    //读取网络的分类数据
    public void getNetClassifues(FindListener<ClassifyEntity> listener) {
        DataQuery<ClassifyEntity> query = new DataQuery<>();
        query.getDatas(mContext, 100, 0, "mId", -1, null, null, listener);
    }

    //保存分类数据到本地
    public void saveClassifies(List<ClassifyEntity> list) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        db.execSQL("delete from Classify where _id >= 0");
        for (int i = 0; i < list.size(); i++) {
            ClassifyEntity entity = list.get(i);
            db.execSQL("insert into Classify values (null , ? , ? , ? , ? , ? ) ", new Object[]{
                    entity.getmId(), entity.getmParent() == null ? "" : entity.getmParent(), entity.getmContent(), entity.getmPic() == null ? "" : entity.getmPicUrl(mContext), entity.getObjectId()});
        }
    }

    //获取本地保存的Banner数据

    public List<BannerEntity> getBannersLocal() {
        List<BannerEntity> list = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Banner", null);
        while (cursor.moveToNext()) {
            BannerEntity entity = new BannerEntity(cursor.getString(1), cursor.getString(2), cursor.getString(3));
            list.add(entity);
        }
        cursor.close();
        return list;
    }

    //从网络获取Banner数据
    public void getBannerNet(FindListener<BannerEntity> listener) {
        DataQuery<BannerEntity> query = new DataQuery<>();
        query.getDatas(mContext, 100, 0, "", -1, null, null, listener);
    }

    //保存Banner数据到本地
    public void saveBanner(List<BannerEntity> list) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        db.execSQL("delete from Banner where _id >=0");
        for (int i = 0; i < list.size(); i++) {
            BannerEntity entity = list.get(i);
            db.execSQL("insert into Banner values ( null , ? , ? , ? )", new Object[]{
                    entity.getmTip(),
                    entity.getmContentId(),
                    entity.getmPicUrl(mContext)
            });
        }
    }

    //从本地获取Content数据
    public List<ContentEntity> getContentLocal() {
        List<ContentEntity> list = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Content", null);
        while (cursor.moveToNext()) {
            ContentEntity entity = new ContentEntity(cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(7), cursor.getInt(8), cursor.getInt(9), cursor.getLong(10), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14));
            list.add(entity);
        }
        return list;
    }

    //从网络获取内容数据
    public void getContentNet(FindListener<ContentEntity> listener) {
        DataQuery<ContentEntity> query = new DataQuery<>();
        query.getDatas(mContext, 15, 0, "", -1, null, null, listener);
    }

    //保存数据到本地
    public void saveContentLocal(List<ContentEntity> list) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        db.execSQL("delete from Content where _id >= 0");
        for (int i = 0; i < list.size(); i++) {
            ContentEntity entity = list.get(i);
            db.execSQL("insert into Content values ( null ,?,?,?,?, ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )", new Object[]{
                    entity.getmUserId(),
                    entity.getmDesc(),
                    entity.getmNowPrice(),
                    entity.getmOldPrice(),
                    entity.getmClassifyId(),
                    entity.getmComment(),
                    entity.getmPraise(),
                    entity.getmCollect(),
                    entity.getmRead(),
                    entity.getmTime(),
                    entity.getmLocation(),
                    entity.getmUserHeadUrl(),
                    entity.getmUserName(),
                    entity.getmClassifyTitle()
            });

        }
    }

    //获取本地保存的首页Tip
    public List<HomeTip> getHomeTipLocal() {
        List<HomeTip> list = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from HomeTip", null);
        while (cursor.moveToNext()) {
            HomeTip tip = new HomeTip(cursor.getString(1));
            list.add(tip);
        }
        cursor.close();
        return list;
    }

    //获取网络保存的HomeTip数据
    public void getHomeTipNet(FindListener<HomeTip> listener) {
        DataQuery<HomeTip> query = new DataQuery<>();
        query.getDatas(mContext, 10, 0, "", -1, null, null, listener);
    }

    //保存HomeTip到本地
    public void saveHomeTipLocal(List<HomeTip> list) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        db.execSQL("delete from HomeTip where _id >=0");
        for (int i = 0; i < list.size(); i++) {
            db.execSQL("insert into HomeTip values ( null , ? ) ", new Object[]{list.get(i).getmTip()});
        }
    }

    //读取本地的图片资源
    public List<ImageEntity> getImageLocal(String contentId) {
        List<ImageEntity> list = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Image where mContentId = '" + contentId + "'", null);
        while (cursor.moveToNext()) {
            ImageEntity entity = new ImageEntity(cursor.getString(1), cursor.getInt(2), cursor.getString(3));
            list.add(entity);
        }
        cursor.close();
        return list;
    }

    //加载网络的图片
    public void getImageNet(String contentId, FindListener<ImageEntity> listener) {
        DataQuery<ImageEntity> query = new DataQuery<>();
        query.getDatas(mContext, 10, 0, "mId", 0, "mContentId", contentId, listener);
    }

    //保存图片到本地
    public void saveImageNetLocal(String contentId, List<ImageEntity> list) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        db.execSQL("delete from Image where mContentId = '" + contentId + "'");
        for (int i = 0; i < list.size(); i++) {
            ImageEntity entity = list.get(i);
            db.execSQL("insert into Image values ( null , ? , ? , ? )", new Object[]{
                    entity.getmContentId(),
                    entity.getmId(),
                    entity.getmPicUrl(mContext)
            });
        }
    }

}
