package com.job.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.job.adapter.MyRecruitAdapter;
import com.job.bean.CompanyRelease;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/***
 *   个人中心  招聘者   招聘历史
 */

public class MyRecruitActivity extends AppCompatActivity {

    ListView lv_myRecruit;
    MyRecruitAdapter myRecruitAdapter;
    List<CompanyRelease> list_cr;
    ImageButton txt_back;      //返回键
    String objectid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recruit);
        lv_myRecruit = (ListView) findViewById(R.id.lv_my);
        txt_back = (ImageButton) findViewById(R.id.fabuactivity_back);

        BmobUser bmobUser = BmobUser.getCurrentUser(this);
        final BmobQuery<CompanyRelease> crbq = new BmobQuery<>();
        crbq.addWhereEqualTo("user_id", bmobUser.getObjectId());
        crbq.findObjects(this, new FindListener<CompanyRelease>() {
            @Override
            public void onSuccess(final List<CompanyRelease> list) {
                list_cr = list;
                Log.i("myrecruitss", "dianji " + list.get(0).getObjectId());
                objectid = list.get(0).getObjectId();
                //listview 点击事件
                lv_myRecruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.i("myrecruitss", "dianji ");

                        CompanyRelease cr = new CompanyRelease();
                        cr.setCr_state("已完成");
                        cr.update(MyRecruitActivity.this, objectid, new UpdateListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(getApplicationContext(), "更新完成", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Toast.makeText(getApplicationContext(), "更新失败", Toast.LENGTH_LONG).show();
                            }
                        });
//                cr.update(MyRecruitActivity.this, );
                    }
                });

                myRecruitAdapter = new MyRecruitAdapter(MyRecruitActivity.this, list_cr);
                lv_myRecruit.setAdapter(myRecruitAdapter);
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
                MyRecruitActivity.this.finish();
            }
        });

    }

}
