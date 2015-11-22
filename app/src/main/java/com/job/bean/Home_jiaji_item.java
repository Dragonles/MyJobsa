package com.job.bean;

/**
 * Created by Administrator on 2015/11/20.
 */
public class Home_jiaji_item {
    private String hot_title;
    private String hot_moneny;
    private String hot_address;

    public String getHot_title() {
        return hot_title;
    }

    public void setHot_title(String hot_title) {
        this.hot_title = hot_title;
    }

    public String getHot_address() {
        return hot_address;
    }

    public void setHot_address(String hot_address) {
        this.hot_address = hot_address;
    }

    public String getHot_moneny() {
        return hot_moneny;
    }

    public void setHot_moneny(String hot_moneny) {
        this.hot_moneny = hot_moneny;
    }

    public Home_jiaji_item(String hot_title, String hot_address, String hot_moneny) {
        this.hot_title = hot_title;
        this.hot_address = hot_address;
        this.hot_moneny = hot_moneny;
    }
}
