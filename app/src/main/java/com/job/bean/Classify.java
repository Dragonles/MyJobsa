package com.job.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2015/11/24.
 */
public class Classify extends BmobObject {

    private String classify_name;

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    public Classify(String classify_name) {
        this.classify_name = classify_name;
    }
}
