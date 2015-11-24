package com.job.bean;

/**
 * Created by Alter on 2015/11/24.
 */
public class CompanyRelease {

    private Integer cr_position;
    private String cr_address;
    private Integer cr_salary;

    public CompanyRelease(Integer cr_position, String cr_address, Integer cr_salary) {
        this.cr_position = cr_position;
        this.cr_address = cr_address;
        this.cr_salary = cr_salary;
    }

    public Integer getCr_position() {
        return cr_position;
    }

    public void setCr_position(Integer cr_position) {
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
}
