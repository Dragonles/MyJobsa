package com.job.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.job.adapter.RecruitHistoryAdapter;
import com.job.bean.CompanyRelease;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 *   个人中心  求职者  兼职历史
 * */
public class RecruitHistoryActivity extends AppCompatActivity {

    ListView lv_rh;
    RecruitHistoryAdapter recruitHistoryAdapter;
    List<CompanyRelease> list_rh;
    ImageButton txt_back;      //返回键
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit_history);
        lv_rh = (ListView) findViewById(R.id.lv_my);
        txt_back = (ImageButton) findViewById(R.id.fabuactivity_back);

        //获得当前用户
        BmobUser bmobUser = BmobUser.getCurrentUser(this);
        BmobQuery<CompanyRelease> crbq = new BmobQuery<>();
        crbq.addWhereEqualTo("cr_apply", bmobUser.getObjectId());
        crbq.findObjects(this, new FindListener<CompanyRelease>() {
            @Override
            public void onSuccess(List<CompanyRelease> list) {
                list_rh = list;
                recruitHistoryAdapter = new RecruitHistoryAdapter(RecruitHistoryActivity.this, list_rh);
                lv_rh.setAdapter(recruitHistoryAdapter);
            }

            @Override
            public void onError(int i, String s) {
                Log.i("myrecruitss", "失败" + i + s);
            }
        });

        //点击返回上一级
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecruitHistoryActivity.this.finish();
            }
        });


    }

}
