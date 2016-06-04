package com.xiaoer.xiaoer.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by Brioal on 2016/6/4.
 */

public class User extends BmobUser {
    private String username;
    private String password;
    private String email;
    private Boolean emailVerified;
    private String sessionToken;
    private String mobilePhoneNumber;
    private Boolean mobilePhoneNumberVerified;
}
