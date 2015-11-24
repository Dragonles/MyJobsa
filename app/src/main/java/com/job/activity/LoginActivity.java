package com.job.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * LoginActivity  登录页
 * 2015-11-18
 * Qing
 *
 * */

public class LoginActivity extends Activity {

    private EditText edit_login_name,edit_login_pwd; // 用户名  密码
    private Button btn_login_submit,btn_login_forget,btn_login_toregister; // 登录按钮 忘记密码  立即注册
    private ImageButton imbtn_login_qq,imbtn_login_sina,imbtn_login_weixin; // 第三方登录
    RelativeLayout mlogin_big_relative;
    LinearLayout mlogin_title_linear;
    TextView mjump;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //  获取各组件
        edit_login_name = (EditText) findViewById(R.id.edit_login_name);
        edit_login_pwd = (EditText) findViewById(R.id.edit_login_pwd);
        btn_login_submit = (Button) findViewById(R.id.btn_login_submit);
//        btn_login_forget = (Button) findViewById(R.id.btn_login_forget);
        btn_login_toregister = (Button) findViewById(R.id.btn_login_toregister);
        mlogin_big_relative=(RelativeLayout)findViewById(R.id.login_big_relative);
        mjump=(TextView)findViewById(R.id.login_jumbp);
        mjump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
        //  登录按钮点击事件
        btn_login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"点击了登录按钮！",Toast.LENGTH_SHORT).show();
            }
        });
        //  忘记密码点击事件
//        btn_login_forget.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(LoginActivity.this,"点击了忘记密码！",Toast.LENGTH_SHORT).show();
//            }
//        });
        //  立即注册点击事件
        btn_login_toregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisteredActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });

    }

}
