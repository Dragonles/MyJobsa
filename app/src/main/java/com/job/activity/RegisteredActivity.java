package com.job.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.job.bean.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.bean.BmobSmsState;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.QuerySMSStateListener;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class RegisteredActivity extends Activity {


    Button mregists_button,mrequset,back;
    EditText mregist_phone,mcode;
    String number;
    public  static boolean intents=false;
    int sid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        mregists_button=(Button)findViewById(R.id.btn_regist_submit);
        mregist_phone=(EditText)findViewById(R.id.edit_regist_phone);
        mcode=(EditText)findViewById(R.id.edit_regist_code);
        mrequset=(Button)findViewById(R.id.requestcode);
        number=mregist_phone.getText()+"";
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisteredActivity.this.finish();
               // if (intents==false)
            }
        });
        Bmob.initialize(this, "e98c629c488e891e6d090798dd2ced7f");
        mrequset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<User> query = new BmobQuery<User>();
                final User user = new User();
                query.findObjects(RegisteredActivity.this, new FindListener<User>() {
                    @Override
                    public void onSuccess(List<User> object) {
                        int i = 0;
                        for (User gameScore : object) {
                            String phone = mregist_phone.getText().toString();
                            if (phone.equals(gameScore.getMobilePhoneNumber())) {
                                Log.i("get", gameScore.getMobilePhoneNumber() + "aaaaaaa" + mregist_phone.getText().toString());
                                Toast.makeText(RegisteredActivity.this, "您的手机号已注册，请重新注册", Toast.LENGTH_LONG).show();
                                i = 1;
                            }
                        }
                        if (i == 0) {
                            mrequset.setEnabled(false);
                            mrequset.setBackgroundColor(Color.WHITE);
                            final ProgressDialog pd = ProgressDialog.show(RegisteredActivity.this, "正在获取验证码", "");
                            Log.i("sget", "000");
                                    BmobSMS.requestSMSCode(RegisteredActivity.this, mregist_phone.getText() + "", "兼职吧", new RequestSMSCodeListener() {
                                    @Override
                                    public void done(Integer integer, BmobException e) {
                                        if (e == null) {//验证码发送成功
                                            sid = integer;
                                            mrequset.setClickable(false);
                                            mrequset.setEnabled(false);
                                            pd.dismiss();
                                            Toast.makeText(RegisteredActivity.this, "验证码正在来的路上", Toast.LENGTH_SHORT).show();
                                            Log.i("smile", "短信id：" + integer);//用于查询本次短信发送详情
                                        } else {
                                            Log.i("smile", "loser" + integer + e);
                                            pd.dismiss();
                                            Toast.makeText(RegisteredActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                                         //   mrequset.setClickable(false);
                                            mrequset.setEnabled(true);
                                            mrequset.setBackgroundResource(R.color.code_color);
                                        }
                                    }
                             });
                        }

                    }

                    @Override
                    public void onError(int code, String msg) {
                    }
                });
            }
        });
        mregists_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("log",mregist_phone.getText()+"");
                if (mregist_phone.length()==0||mcode.length()==0)
                {
                    Toast.makeText(RegisteredActivity.this,"请输入手机号和验证码",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(isMobileNO(mregist_phone.getText().toString()))
                    {
                        Toast.makeText(RegisteredActivity.this, "手机格式正确", Toast.LENGTH_SHORT).show();
                        final ProgressDialog pd = ProgressDialog.show(RegisteredActivity.this, "等待验证", "");
                        Log.i("smile",number);
                        BmobSMS.verifySmsCode(RegisteredActivity.this, mregist_phone.getText() + "", mcode.getText() + "", new VerifySMSCodeListener() {
                            @Override
                            public void done(BmobException ex) {
                                // TODO Auto-generated method stub
                                if (ex == null) {//短信验证码已验证成功
                                    Log.i("smiles", mregist_phone.getText() + "");
                                    mrequset.setClickable(true);
                                    pd.dismiss();
                                    Intent intent = new Intent(RegisteredActivity.this, SetingsUserActivity.class);
                                    intent.putExtra("phone", mregist_phone.getText() + "");
                                    intent.putExtra("intens", intents);
                                    startActivity(intent);
                                    intents = true;
                                    RegisteredActivity.this.finish();
                                } else {
                                    Toast.makeText(RegisteredActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                    Log.i("smile", "验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                                }
                            }
                        });
                    }
                    else {
                        Toast.makeText(RegisteredActivity.this, "手机号不正确", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });


    }

    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }
}
