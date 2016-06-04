package com.xiaoer.xiaoer.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xiaoer.xiaoer.database.DBHelper;
import com.xiaoer.xiaoer.entity.ClassifyEntity;

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
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            ClassifyEntity entity = new ClassifyEntity(cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            entity.setObjectId(cursor.getString(5));
            entities.add(entity);
        }
        cursor.close();
        db.close();
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
        for (int i = 0; i < list.size(); i++) {
            ClassifyEntity entity = list.get(i);
            db.execSQL("insert into Classify values (null , ? , ? , ? , ? , ? ) ", new Object[]{
                    entity.getmId(), entity.getmParent(), entity.getmContent(), entity.getmPicUrl(mContext), entity.getObjectId()});
        }
        db.close();
    }
}
