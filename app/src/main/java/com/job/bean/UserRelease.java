package com.job.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2015/11/25.
 */
public class UserRelease extends BmobObject {
    private String ur_name;
    private String ur_icon;
    private String ur_sex;
    private String birthday;
    private String ur_study;
    private String ur_address;
    private String ur_work_time;
    private String ur_money;
    private String user_id;
    private String ur_class;
    private String is_jianli;
    private String ur_requirement;
    /**
     * 增庆添加
     * */

    private String ur_email; // 邮箱
    private String ur_tel; // 手机号

    public String getUr_email() {
        return ur_email;
    }

    public String getUr_requirement() {
        return ur_requirement;
    }

    public void setUr_requirement(String ur_requirement) {
        this.ur_requirement = ur_requirement;
    }

    public void setUr_email(String ur_email) {
        this.ur_email = ur_email;
    }

    public String getUr_tel() {
        return ur_tel;
    }

    public void setUr_tel(String ur_tel) {
        this.ur_tel = ur_tel;
    }


    /**
     *
     * */
    public UserRelease() {

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUr_name() {
        return ur_name;
    }

    public void setUr_name(String ur_name) {
        this.ur_name = ur_name;
    }

    public String getUr_money() {
        return ur_money;
    }

    public void setUr_money(String ur_money) {
        this.ur_money = ur_money;
    }

    public String getUr_work_time() {
        return ur_work_time;
    }

    public void setUr_work_time(String ur_work_time) {
        this.ur_work_time = ur_work_time;
    }

    public String getUr_address() {
        return ur_address;
    }

    public void setUr_address(String ur_address) {
        this.ur_address = ur_address;
    }

    public String getUr_study() {
        return ur_study;
    }

    public void setUr_study(String ur_study) {
        this.ur_study = ur_study;
    }

    public String getUr_sex() {
        return ur_sex;
    }

    public void setUr_sex(String ur_sex) {
        this.ur_sex = ur_sex;
    }

    public String getUr_icon() {
        return ur_icon;
    }

    public void setUr_icon(String ur_icon) {
        this.ur_icon = ur_icon;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUr_class() {
        return ur_class;
    }

    public void setUr_class(String ur_class) {
        this.ur_class = ur_class;
    }

    public String getIs_jianli() {
        return is_jianli;
    }

    public void setIs_jianli(String is_jianli) {
        this.is_jianli = is_jianli;
    }

    public UserRelease(String ur_name, String ur_tel, String ur_email, String ur_class, String user_id, String ur_money, String ur_work_time, String ur_address, String ur_study, String birthday, String ur_sex, String ur_icon,String is_jianli
    ,String ur_requirement) {
        this.ur_name = ur_name;
        this.ur_tel = ur_tel;
        this.ur_email = ur_email;
        this.ur_class = ur_class;
        this.user_id = user_id;
        this.ur_money = ur_money;
        this.ur_work_time = ur_work_time;
        this.ur_address = ur_address;
        this.ur_study = ur_study;
        this.birthday = birthday;
        this.ur_sex = ur_sex;
        this.ur_icon = ur_icon;
        this.is_jianli=is_jianli;
        this.ur_requirement=ur_requirement;
    }
}
