package com.job.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.job.bean.Feedback;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class FankuiActivity extends Activity {

    EditText et_fankui;
    Button btn_tijiao;
    ImageButton ib_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fankui);

        et_fankui = (EditText) findViewById(R.id.et_fankui);
        btn_tijiao = (Button) findViewById(R.id.btn_tijiao);
        ib_back = (ImageButton) findViewById(R.id.ib_back);



        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BmobUser bu = BmobUser.getCurrentUser(FankuiActivity.this);

                if(bu.getObjectId() == null){
                    Toast.makeText(FankuiActivity.this, "请登录", Toast.LENGTH_SHORT).show();
                }else {

                    Feedback fb = new Feedback();
                    //注意：不能调用gameScore.setObjectId("")方法8
                    fb.setUser_id(bu.getObjectId());
                    fb.setFb_neirong(et_fankui.getText().toString());
                    fb.save(FankuiActivity.this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(FankuiActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(FankuiActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();
                            Log.i("lll", i + " " + s);
                        }
                    });
                }
            }
        });

        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FankuiActivity.this.finish();
            }
        });
    }
}
