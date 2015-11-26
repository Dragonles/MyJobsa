package com.job.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.job.bean.CompanyProve;
import com.job.bean.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

/**
 * UpdateInformationActivity  修改资料页
 *
 * from ZengQing
 * */

public class UpdateInformationActivity extends AppCompatActivity {

    CircleImageView img_update_userheader;// 用户头像
<<<<<<< HEAD
    TextView tv_update_username,tv_update_email,tv_update_tel; // 用户名 邮箱 电话
    Button btn_update_information_username,btn_update_information_password,btn_update_information_email,btn_update_information_tel;// 按钮
=======
    TextView tv_update_username,tv_update_sex,tv_update_email,tv_update_tel; // 用户名 性别 邮箱 电话
    Button btn_update_information_username,btn_update_information_usersex,btn_update_information_password,btn_update_information_email,btn_update_information_tel;// 按钮
>>>>>>> d25eeba0516b750c0700752d203edecf295bc3d3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information);
        img_update_userheader=(CircleImageView) findViewById(R.id.img_update_userheader);
<<<<<<< HEAD
        tv_update_username = (TextView) findViewById(R.id.tv_update_username);
        tv_update_email = (TextView) findViewById(R.id.tv_update_email);
        tv_update_tel = (TextView) findViewById(R.id.tv_update_tel);
=======
>>>>>>> d25eeba0516b750c0700752d203edecf295bc3d3

        Bmob.initialize(UpdateInformationActivity.this, "e98c629c488e891e6d090798dd2ced7f");
        final BmobUser bmobUser = BmobUser.getCurrentUser(UpdateInformationActivity.this);
        if(bmobUser != null) {
<<<<<<< HEAD
            tv_update_username.setText(bmobUser.getUsername());
            tv_update_email.setText(bmobUser.getEmail());
            tv_update_tel.setText(bmobUser.getMobilePhoneNumber());
=======
        //    tv_update_username.setText(bmobUser.getUsername());
>>>>>>> d25eeba0516b750c0700752d203edecf295bc3d3
            BmobQuery<User> query = new BmobQuery<User>();
            query.getObject(UpdateInformationActivity.this, bmobUser.getObjectId(), new GetListener<User>() {
                @Override
                public void onSuccess(User object) {
                    Picasso.with(UpdateInformationActivity.this).load(object.getUser_icon()).into(img_update_userheader);
                    Log.i("coe", object.getUser_icon().toString());
                }

                @Override
                public void onFailure(int code, String arg0) {
                    // TODO Auto-generated method stub
                    Log.i("ss", code + arg0);
                }

            });
        }

    }
    // 返回键点击事件
    public void up_back(View v){
        this.finish();
    }

}
