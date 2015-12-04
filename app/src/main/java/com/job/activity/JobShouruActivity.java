package com.job.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.job.adapter.JobShouruAdapter;
import com.job.adapter.LocartionAdapter;
import com.job.bean.CompanyRelease;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.FindStatisticsListener;

public class JobShouruActivity extends AppCompatActivity {

    ListView listView;
    JobShouruAdapter adapter;
    CheckBox ck_check;
    TextView tv_num;
    List<CompanyRelease> companyReleaseList;
    ImageButton ib_back;
    int sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_shouru);
        ck_check = (CheckBox) findViewById(R.id.ck_ckeck);
        tv_num = (TextView) findViewById(R.id.tv_num);
        listView = (ListView) findViewById(R.id.lv_shouru);
//        ib_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                JobShouruActivity.this.finish();
//            }
//        });

        Log.i("crbqss", "ss");
        Bmob.initialize(this, "e98c629c488e891e6d090798dd2ced7f");

        BmobQuery<CompanyRelease> query = new BmobQuery<CompanyRelease>();
        query.sum(new String[]{"cr_salary"});
        query.findStatistics(this, CompanyRelease.class, new FindStatisticsListener() {

            @Override
            public void onSuccess(Object object) {
                JSONArray ary = (JSONArray) object;
                if (ary != null) {//
                    try {
                        JSONObject obj = ary.getJSONObject(0);
                        sum = obj.getInt("_sumCr_salary");//_(关键字)+首字母大写的列名
                        Log.i("sss", sum + "");
                        ck_check.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (ck_check.isChecked()) {
                                    tv_num.setText("***");
                                } else {
                                    tv_num.setText(sum + "");
                                }
                            }
                        });

                    } catch (JSONException e) {

                    }

                } else {

                }
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub

            }
        });


        BmobQuery<CompanyRelease> crbq = new BmobQuery<CompanyRelease>();

        crbq.findObjects(JobShouruActivity.this, new FindListener<CompanyRelease>() {
            @Override
            public void onSuccess(List<CompanyRelease> list) {
                Log.i("crbqss", "成功");
                companyReleaseList = list;

                Log.i("crbqss", companyReleaseList.size() + "");
                adapter = new JobShouruAdapter(JobShouruActivity.this,companyReleaseList);
                listView.setAdapter(adapter);

                tv_num.setText(sum + "");
            }

            @Override
            public void onError(int i, String s) {
                Log.i("crbqss", "失败" + i + " " + s);

            }
        });

//        listView = (ListView) findViewById(R.id.lv_shouru);
//        adapter = new JobShouruAdapter(JobShouruActivity.this,list);
//        listView.setAdapter(adapter);

    }

}
