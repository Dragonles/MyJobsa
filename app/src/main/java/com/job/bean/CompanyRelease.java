package com.job.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Alter on 2015/11/24.
 */
public class CompanyRelease extends BmobObject {

    private String cr_position;    //工作职业
    private String cr_address;      //工作地址
    private Integer cr_salary;      //薪资
    private String cr_require;      //工作要求
    private String cr_education;    //工作学历
    private String cr_payments;     //结算方式
    private Integer cr_count;       //人数
    private String cr_person;       //联系人
    private Integer cr_infor;       //联系方式

    public CompanyRelease(String cr_position, String cr_address, Integer cr_salary, String cr_require, String cr_education, String cr_payments, Integer cr_count, String cr_person, Integer cr_infor) {
        this.cr_position = cr_position;
        this.cr_address = cr_address;
        this.cr_salary = cr_salary;
        this.cr_require = cr_require;
        this.cr_education = cr_education;
        this.cr_payments = cr_payments;
        this.cr_count = cr_count;
        this.cr_person = cr_person;
        this.cr_infor = cr_infor;
    }

    public String getCr_position() {
        return cr_position;
    }

    public void setCr_position(String cr_position) {
        this.cr_position = cr_position;
    }

    public String getCr_address() {
        return cr_address;
    }

    public void setCr_address(String cr_address) {
        this.cr_address = cr_address;
    }

    public Integer getCr_salary() {
        return cr_salary;
    }

    public void setCr_salary(Integer cr_salary) {
        this.cr_salary = cr_salary;
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

    public Integer getCr_infor() {
        return cr_infor;
    }

    public void setCr_infor(Integer cr_infor) {
        this.cr_infor = cr_infor;
    }
}
