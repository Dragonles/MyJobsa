package com.job.bean;

/**
 * Created by Alter on 2015/11/24.
 */
public class Classify {


    public int classify_id;
    public String classify_name;

    public Classify(){

    }
    public Classify(int classify_id, String classify_name){
        this.classify_id = classify_id;
        this.classify_name = classify_name;
    }

    public int getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(int classify_id) {
        this.classify_id = classify_id;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "classify_id=" + classify_id +
                ", classify_name='" + classify_name + '\'' +
                '}';
    }
}
