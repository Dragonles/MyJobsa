package com.job.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.job.activity.LeadActivity;
import com.job.activity.MainActivity;
import com.job.activity.R;

import cn.bmob.v3.BmobUser;

/**
 * 闪屏页
 * 2015-11-18
 * yanhao
 * */
public class  ShanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shan);
         /*使用线程延迟三秒，跳转到主页面*/
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "YYwje7mhVDNwj1q3TZK0SgyC");
        BmobUser bmobUser = BmobUser.getCurrentUser(ShanActivity.this);
        if(bmobUser != null){
            Intent intent = new Intent(ShanActivity.this, MainActivity.class);
            startActivity(intent);
            ShanActivity.this.finish();

        }else{
            new Thread (new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    /*
                    * 延迟3秒后跳转引导页（LeadActivity）
                    * */
                        Intent intent =new Intent(ShanActivity.this,LeadActivity.class);
                        ShanActivity.this.startActivity(intent);
                        ShanActivity.this.finish();
                        break;
                    }
                }
            }).start();
        }

    }

}
