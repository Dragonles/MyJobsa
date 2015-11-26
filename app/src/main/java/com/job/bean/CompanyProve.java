package com.job.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2015/11/24.
 */
public class CompanyProve extends BmobObject{
    private String cp_logo;
    private String cp_companyperson;
    private String cp_type;
    private String cp_product;
    private String cp_date;
    private String cp_name;
    private String cp_address;
    private String cp_number;
    private String user_id;
    private String prove_flag;


    public CompanyProve(){

    }

    public CompanyProve(String cp_logo, String cp_companyperson, String cp_type, String cp_product, String cp_date, String cp_name, String cp_address, String cp_number, String user_id, String prove_flag) {
        this.cp_logo = cp_logo;
        this.cp_companyperson = cp_companyperson;
        this.cp_type = cp_type;
        this.cp_product = cp_product;
        this.cp_date = cp_date;
        this.cp_name = cp_name;
        this.cp_address = cp_address;
        this.cp_number = cp_number;
        this.user_id = user_id;
        this.prove_flag = prove_flag;
    }

    public String getCp_logo() {
        return cp_logo;
    }

    public void setCp_logo(String cp_logo) {
        this.cp_logo = cp_logo;
    }

    public String getCp_companyperson() {
        return cp_companyperson;
    }

    public void setCp_companyperson(String cp_companyperson) {
        this.cp_companyperson = cp_companyperson;
    }

    public String getCp_type() {
        return cp_type;
    }

    public void setCp_type(String cp_type) {
        this.cp_type = cp_type;
    }

    public String getCp_product() {
        return cp_product;
    }

    public void setCp_product(String cp_product) {
        this.cp_product = cp_product;
    }

    public String getCp_date() {
        return cp_date;
    }

    public void setCp_date(String cp_date) {
        this.cp_date = cp_date;
    }

    public String getCp_name() {
        return cp_name;
    }

    public void setCp_name(String cp_name) {
        this.cp_name = cp_name;
    }

    public String getCp_address() {
        return cp_address;
    }

    public void setCp_address(String cp_address) {
        this.cp_address = cp_address;
    }

    public String getCp_number() {
        return cp_number;
    }

    public void setCp_number(String cp_number) {
        this.cp_number = cp_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProve_flag() {
        return prove_flag;
    }

    public void setProve_flag(String prove_flag) {
        this.prove_flag = prove_flag;
    }
}
