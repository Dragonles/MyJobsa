package com.job.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2015/11/23.
 */
public class User extends BmobUser {
    private String user_icon;

    public String getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }

    public User(String user_icon) {
        this.user_icon = user_icon;
    }


    public User() {
    }
}
