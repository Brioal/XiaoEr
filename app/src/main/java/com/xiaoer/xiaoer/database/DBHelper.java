package com.xiaoer.xiaoer.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库操作类
 * Created by Brioal on 2016/6/4.
 */

public class DBHelper extends SQLiteOpenHelper {
    private final String CREATE_CLASSIFY_TABLE = "create table Classify ( _id integer primary key autoincrement , mId integer,mParent ,mContent , mPicUrl ,mObjectId ) ";
    private final String CREATE_BANNER_TABLE = "create table Banner ( _id integer primary key autoincrement , mTip , mContentId , mPicUrl )";
    private final String CREATE_CONTENt_TABLE = "create table Content ( _id integer primary key autoincrement  , mUserId , mDesc  , mNowPrice integer , mOldPrice integer ,mClassifyId , mComment integer , mPraise integer , mCollect integer , mRead integer , mTime long , mLocation ,   mUserHeadUrl ,  mUserName ,mClassifyTitle )";
    private final String CREATE_HOMETIP_TABLE = "create table HomeTip ( _id integer primary key autoincrement , mTip )";
    private final String CREATE_IMAGE_TABLE = "create table Image ( _id integer primary key autoincrement , mContentId , mId integer , mPicUrl )";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CLASSIFY_TABLE);
        db.execSQL(CREATE_BANNER_TABLE);
        db.execSQL(CREATE_CONTENt_TABLE);
        db.execSQL(CREATE_HOMETIP_TABLE);
        db.execSQL(CREATE_IMAGE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
