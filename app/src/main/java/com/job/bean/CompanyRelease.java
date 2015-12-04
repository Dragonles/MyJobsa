package com.job.bean;

import cn.bmob.v3.BmobObject;

/**
 * 主页热门兼职构造器
 * Created by Administrator on 2015/11/20.
 */
public class CompanyRelease extends BmobObject {
    private String title;
    private String user_id;         //用户ID
    private String cr_position;    //工作职业
    private String cr_address;      //工作地址
    private Integer cr_salary;      //薪资
    private String cr_require;      //工作要求
    private String cr_education;    //工作学历
    private String cr_payments;     //结算方式
    private Integer cr_count;       //人数
    private String cr_person;       //联系人
    private String cr_infor;       //联系方式
    private String cr_companydate; //工作时间
    private String cr_urgency;      //加急事件
    private String cr_state;       //完成状态
    private String cr_apply;        //应聘者

    public CompanyRelease() {

    }

    public CompanyRelease(String cr_address, String cr_require, Integer cr_count) {
        this.cr_address = cr_address;
        this.cr_require = cr_require;
        this.cr_count = cr_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCr_position() {
        return cr_position;
    }

    public void setCr_position(String cr_position) {
        this.cr_position = cr_position;
    }

    public Integer getCr_salary() {
        return cr_salary;
    }

    public void setCr_salary(Integer cr_salary) {
        this.cr_salary = cr_salary;
    }

    public String getCr_address() {
        return cr_address;
    }

    public void setCr_address(String cr_address) {
        this.cr_address = cr_address;
    }

    public String getCr_require() {
        return cr_require;
    }

    public void setCr_require(String cr_require) {
        this.cr_require = cr_require;
    }

    public String getCr_education() {
        return cr_education;
    }

    public void setCr_education(String cr_education) {
        this.cr_education = cr_education;
    }

    public String getCr_payments() {
        return cr_payments;
    }

    public void setCr_payments(String cr_payments) {
        this.cr_payments = cr_payments;
    }

    public Integer getCr_count() {
        return cr_count;
    }

    public void setCr_count(Integer cr_count) {
        this.cr_count = cr_count;
    }

    public String getCr_person() {
        return cr_person;
    }

    public void setCr_person(String cr_person) {
        this.cr_person = cr_person;
    }

    public String getCr_infor() {
        return cr_infor;
    }

    public void setCr_infor(String cr_infor) {
        this.cr_infor = cr_infor;
    }

    public String getCr_companydate() {
        return cr_companydate;
    }

    public void setCr_companydate(String cr_companydate) {
        this.cr_companydate = cr_companydate;
    }

    public String getCr_urgency() {
        return cr_urgency;
    }

    public void setCr_urgency(String cr_urgency) {
        this.cr_urgency = cr_urgency;
    }

    public String getCr_state() {
        return cr_state;
    }

    public void setCr_state(String cr_state) {
        this.cr_state = cr_state;
    }

    public String getCr_apply() {
        return cr_apply;
    }

    public void setCr_apply(String cr_apply) {
        this.cr_apply = cr_apply;
    }

    public CompanyRelease(String title, Integer cr_salary, String cr_address) {
        this.title = title;
        this.cr_salary = cr_salary;
        this.cr_address = cr_address;
    }

    public CompanyRelease(String user_id,String cr_apply, String cr_state, String cr_infor, String cr_companydate, String cr_urgency, String cr_person, Integer cr_count, String cr_payments, String cr_education, String cr_require, String cr_position, Integer cr_salary, String cr_address) {
        this.cr_apply = cr_apply;
        this.user_id=user_id;
        this.cr_state = cr_state;
        this.cr_infor = cr_infor;
        this.cr_companydate = cr_companydate;
        this.cr_urgency = cr_urgency;
        this.cr_person = cr_person;
        this.cr_count = cr_count;
        this.cr_payments = cr_payments;
        this.cr_education = cr_education;
        this.cr_require = cr_require;
        this.cr_position = cr_position;
        this.cr_salary = cr_salary;
        this.cr_address = cr_address;
    }
}
