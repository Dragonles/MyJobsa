package com.job.activity;

import android.app.Activity;
import android.app.ProgressDialog;
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

import com.job.bean.User;
import com.job.utils.PushTestReceiver;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

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
        BmobUser bmobUser = BmobUser.getCurrentUser(this);
        if(bmobUser != null){

        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
        }
        //  登录按钮点击事件
        btn_login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pd = ProgressDialog.show(LoginActivity.this, "", "正在登录.....");
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setCancelable(false);
                Bmob.initialize(LoginActivity.this, "e98c629c488e891e6d090798dd2ced7f");
                BmobUser.loginByAccount(LoginActivity.this, edit_login_name.getText()+"", edit_login_pwd.getText().toString(), new LogInListener<User>() {

                    @Override
                    public void done(User user, BmobException e) {
                        // TODO Auto-generated method stub
                        if (user != null) {
                            Log.i("smile", "用户登陆成功");
                            Toast.makeText(LoginActivity.this, "用户登陆成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            pd.dismiss();
                            LoginActivity.this.finish();
                            User gameScore = new User();
                            gameScore.setChannel_id(PushTestReceiver.channe_id);
                            gameScore.update(LoginActivity.this,user.getObjectId(), new UpdateListener() {
                                @Override
                                public void onSuccess() {
                                    // TODO Auto-generated method stub
                                    Log.i("bmob", "chid更新成功：");
                                }

                                @Override
                                public void onFailure(int code, String msg) {
                                    // TODO Auto-generated method stub
                                    Log.i("bmob", "chid更新失败：" + msg);
                                }
                            });
                        }
                        else {
                            Log.i("code2",e.toString());
                            pd.dismiss();
                            Toast.makeText(LoginActivity.this, "用户名或密码输入错.误！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

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
            }
        });

    }

}
