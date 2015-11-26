package com.job.bean;

import cn.bmob.v3.BmobObject;

/**
 * 主页热门兼职构造器
 * Created by Administrator on 2015/11/20.
 */
public class CompanyRelease extends BmobObject {
    private String title;
    private String cr_address;
    private String cr_salary;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCr_address() {
        return cr_address;
    }

    public void setCr_address(String cr_address) {
        this.cr_address = cr_address;
    }

    public String getCr_salary() {
        return cr_salary;
    }

    public void setCr_salary(String cr_salary) {
        this.cr_salary = cr_salary;
    }

    public CompanyRelease(String title, String cr_salary, String cr_address) {
        this.title = title;
        this.cr_salary = cr_salary;
        this.cr_address = cr_address;
    }
}
