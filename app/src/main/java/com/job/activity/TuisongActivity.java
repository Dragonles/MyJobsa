package com.job.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.job.bean.CompanyRelease;
import com.job.bean.UserRelease;
import com.job.utils.PushTestReceiver;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.UpdateListener;

public class TuisongActivity extends AppCompatActivity {

    TextView user_name,user_xueli,user_sex,user_birth,user_yaoqiu,user_jingyan,usr_email,user_address,user_tel;
    Button give_btn;
    public static String baseurl = "https://api.tuisong.baidu.com/rest/3.0/";
    CircleImageView img;
    String yinpinid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        img=(CircleImageView)findViewById(R.id.choseimg);
        user_name=(TextView)findViewById(R.id.setname);
        user_xueli=(TextView)findViewById(R.id.setxueli);
        user_tel=(TextView)findViewById(R.id.settel);
        user_sex=(TextView)findViewById(R.id.sex);
        user_yaoqiu=(TextView)findViewById(R.id.setsalary);
        user_birth=(TextView)findViewById(R.id.birth);
        user_jingyan=(TextView)findViewById(R.id.setjingyan);
        usr_email=(TextView)findViewById(R.id.setemail);
        user_address=(TextView)findViewById(R.id.setaddress);
        give_btn=(Button)findViewById(R.id.jianli_submit);

        Intent intent = getIntent();
        String qid = intent.getStringExtra("qid");

        Log.i("qid",qid);

        BmobQuery<UserRelease> cpbq = new BmobQuery<UserRelease>();
        cpbq.addWhereEqualTo("user_id",qid);
        cpbq.findObjects(TuisongActivity.this, new FindListener<UserRelease>() {
            @Override
            public void onSuccess(List<UserRelease> list) {
                if (list.size() != 0) {
                    user_name.setText(list.get(0).getUr_name());
                    Picasso.with(TuisongActivity.this).load(list.get(0).getUr_icon()).into(img);
                    usr_email.setText(list.get(0).getUr_email());
                    user_address.setText(list.get(0).getUr_address());
                    user_jingyan.setText(list.get(0).getUr_work_time());
                    user_birth.setText(list.get(0).getBirthday());
                    user_sex.setText(list.get(0).getUr_sex());
                    user_xueli.setText(list.get(0).getUr_study());
                    user_yaoqiu.setText(list.get(0).getUr_money());
                    user_tel.setText(list.get(0).getUr_tel());
                    yinpinid = list.get(0).getUser_id();
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.i("tuisong", s + i);
            }
        });
        final BmobUser bmobUser = BmobUser.getCurrentUser(TuisongActivity.this);
        give_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BmobQuery<CompanyRelease> bqcp = new BmobQuery<CompanyRelease>();
                bqcp.addWhereEqualTo("user_id", bmobUser.getObjectId());
                bqcp.findObjects(TuisongActivity.this, new FindListener<CompanyRelease>() {
                    @Override
                    public void onSuccess(List<CompanyRelease> list) {
                        CompanyRelease cq = new CompanyRelease();
                        cq.setCr_apply(yinpinid);
                        cq.update(TuisongActivity.this, list.get(0).getObjectId(), new UpdateListener() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onFailure(int i, String s) {

                            }
                        });
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });

            }
        });
    }
}
