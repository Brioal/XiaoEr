package com.xiaoer.xiaoer.util;

import android.content.Context;

/**封装用户信息,数据获取类
 * Created by Brioal on 2016/6/4.
 */

public class Constants {
    public static final String APPID = "ee477131f62f87bc6fb3c52b15b82a8d";
    public static DataLoader mDataLoader ;

    public static DataLoader getmDataLoader(Context context) {
        if (mDataLoader == null) {
            mDataLoader = new DataLoader(context);
        }

        return mDataLoader;
    }
}
