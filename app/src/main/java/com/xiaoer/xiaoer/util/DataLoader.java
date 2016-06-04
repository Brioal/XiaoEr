package com.xiaoer.xiaoer.util;

import android.content.Context;

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
//        SQLi

        return entities;
    }

    //读取网络的数据
    public void getNetClassifues(FindListener<ClassifyEntity> listener) {
        DataQuery<ClassifyEntity> query = new DataQuery<>();
        query.getDatas(mContext, 100, 0, "mId", -1, null, null, listener);
    }
}
