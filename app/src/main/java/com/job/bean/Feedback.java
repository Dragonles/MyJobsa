package com.job.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Alter on 2015/12/1.
 */
public class Feedback extends BmobObject {

    private String user_id;
    private String fb_neirong;

    public Feedback(){

    }

    public Feedback(String user_id, String fb_neirong){
        this.user_id = user_id;
        this.fb_neirong = fb_neirong;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFb_neirong() {
        return fb_neirong;
    }

    public void setFb_neirong(String fb_neirong) {
        this.fb_neirong = fb_neirong;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "user_id=" + user_id +
                ", fb_neirong='" + fb_neirong + '\'' +
                '}';
    }
}
