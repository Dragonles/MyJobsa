package com.job.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2015/11/23.
 */
public class User extends BmobUser {
    private String user_icon;
    private Integer user_id;
    private String jianli_icon;
    private String channel_id;

    public String getJianli_icon() {
        return jianli_icon;
    }

    public void setJianli_icon(String jianli_icon) {
        this.jianli_icon = jianli_icon;
    }

    public String getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }

    public User(String user_icon) {
        this.user_icon = user_icon;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public User() {
    }
}
