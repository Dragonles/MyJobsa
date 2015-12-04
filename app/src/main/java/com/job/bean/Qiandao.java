package com.job.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2015/11/30.
 */
public class Qiandao extends BmobObject {
    private String user_id;
    private String jifen;

    public Qiandao() {

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }

    public Qiandao(String user_id, String jifen) {
        this.user_id = user_id;
        this.jifen = jifen;
    }
}
