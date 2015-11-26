package com.job.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.bean.BmobSmsState;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.QuerySMSStateListener;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.Bmob;

public class RegisteredActivity extends Activity {


    Button mregists_button,mrequset;
    EditText mregist_phone,mcode;
    int sid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        mregists_button=(Button)findViewById(R.id.btn_regist_submit);
        mregist_phone=(EditText)findViewById(R.id.edit_regist_phone);
        mcode=(EditText)findViewById(R.id.edit_regist_code);
        mrequset=(Button)findViewById(R.id.requestcode);
        Bmob.initialize(this, "e98c629c488e891e6d090798dd2ced7f");
        mrequset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobSMS.requestSMSCode(RegisteredActivity.this, mregist_phone.getText() + "", "兼职吧", new RequestSMSCodeListener() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if (e == null) {//验证码发送成功
                            sid=integer;
                            mrequset.setClickable(false);
                            Toast.makeText(RegisteredActivity.this,"验证码正在来的路上",Toast.LENGTH_SHORT).show();
                            Log.i("smile", "短信id：" + integer);//用于查询本次短信发送详情
                        } else {
                            Log.i("smile", "loser" + integer + e);
                        }
                    }
                });
                BmobSMS.querySmsState(RegisteredActivity.this, sid, new QuerySMSStateListener() {
                    @Override
                    public void done(BmobSmsState state, BmobException ex) {
                        Log.i("s",sid+"");
                        // TODO Auto-generated method stub
                        if (ex == null) {
                            Log.i("smile", "短信状态：" + state.getSmsState() + ",验证状态：" + state.getVerifyState());
                        }
                    }
                });
            }
        });
        mregists_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mregist_phone.getText()==null)
                {
                    Toast.makeText(RegisteredActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                }
                else {
                    BmobSMS.verifySmsCode(RegisteredActivity.this, mregist_phone.getText() + "", mcode.getText() + "", new VerifySMSCodeListener() {
                        @Override
                        public void done(BmobException ex) {
                            // TODO Auto-generated method stub
                            if (ex == null) {//短信验证码已验证成功
                                Log.i("smile", "验证通过");
                                mrequset.setClickable(true);
                                Intent intent =new Intent(RegisteredActivity.this,SetingsUserActivity.class);
                                intent.putExtra("phone",mregist_phone.getText());
                                startActivity(intent);
                                RegisteredActivity.this.finish();
                            } else {
                                Log.i("smile", "验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                            }
                        }
                    });
                }

            }
        });


    }

}
