package com.job.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.bmob.BTPFileResponse;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;
import com.job.bean.User;
import com.job.utils.PushTestReceiver;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import fragment.PerFragment;
import me.nereo.imagechoose.MultiImageSelectorActivity;
/**
 * 2015-11-22
 * 设置用户信息页面
 */

public class SetingsUserActivity extends AppCompatActivity {

    //用户名 密码 邮箱
    String phonenumber;
    TextInputLayout usernameWrapper,passwordWrapper,password2Wrapper,emailWrapper;
    CircleImageView mheadlog;//头像显示按钮
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;
    Button back;
    private String mFilePath;//图片路径

    private  static  final int REQUEST_IMAGE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings_user);
        //获取用户名 密码等edittext
         usernameWrapper = (TextInputLayout)findViewById(R.id.usernameWrapper);
        passwordWrapper=(TextInputLayout)findViewById(R.id.passwordWrapper);
        password2Wrapper=(TextInputLayout)findViewById(R.id.pass2Wrapper);
       // emailWrapper=(TextInputLayout)findViewById(R.id.emailWrapper);
        mheadlog=(CircleImageView)findViewById(R.id.touxiang);
        //带有滚动效果的 hint
        usernameWrapper.setHint("请输入昵称");
        passwordWrapper.setHint("请设置密码");
        back=(Button)findViewById(R.id.back);
        if (RegisteredActivity.intents==true)
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(SetingsUserActivity.this);
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            builder.setTitle("请设置账户信息");
                            builder.setCancelable(true);
                            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    });
                }
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SetingsUserActivity.this.finish();
//                if (RegisteredActivity.intents==true)
//                {
//                    final AlertDialog.Builder builder = new AlertDialog.Builder(CreatJianliActivity.this);
//                    yaoqiu.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            builder.setTitle("类型选择");
//                            builder.setIcon(android.R.drawable.button_onoff_indicator_off);
//                            builder.setCancelable(true);
//                            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                }
//                            });
//                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            });
//                            AlertDialog alert = builder.create();
//                            alert.show();
//                        }
//                    });
//                }
//            }
//        });
        password2Wrapper.setHint("请重新输入密码");
        Bmob.initialize(this, "e98c629c488e891e6d090798dd2ced7f");
        mheadlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetingsUserActivity.this,MultiImageSelectorActivity.class);
// whether show camera
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
// max select image amount
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
// select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
                startActivityForResult(intent,REQUEST_IMAGE);
            }
        });
    }
    //验证并注册 按钮
    public void yanzheng(View v) {
        final String username = usernameWrapper.getEditText().getText().toString();
        final String password = passwordWrapper.getEditText().getText().toString();
        final String password2=password2Wrapper.getEditText().getText().toString();
      //  final String email=emailWrapper.getEditText().getText().toString();
        Log.i("code", "ha");
        Intent intent = getIntent();
        phonenumber = intent.getStringExtra("phone");

        if (usernameWrapper.getEditText()!=null)
        {
            if(username.length()<2)
            {
                usernameWrapper.setError("用户名在2个字符以上");
            }
            else {
                usernameWrapper.setErrorEnabled(false);
                if (password.length() < 6) {
                    passwordWrapper.setError("请输入6位以上的密码");
                } else {
                    passwordWrapper.setErrorEnabled(false);
                    if (password2.equals(password)) {
                        password2Wrapper.setErrorEnabled(false);
//                            emailWrapper.setErrorEnabled(false);
                            final ProgressDialog pd = ProgressDialog.show(SetingsUserActivity.this, "等待上传", "");
                            Bmob.initialize(this, "e98c629c488e891e6d090798dd2ced7f");
                            BmobProFile.getInstance(getApplication()).upload(mFilePath, new UploadListener() {
                                @Override
                                public void onSuccess(String s, String s1, BmobFile bmobFile) {
                                    Log.i("id", phonenumber);
                                    BmobUser bmobUser = BmobUser.getCurrentUser(SetingsUserActivity.this);
                                    User user = new User();
                                    user.setUser_icon(bmobFile.getUrl());
                                    user.setUsername(username);
                                    user.setMobilePhoneNumber(phonenumber);
                                    user.setPassword(password2);
                                    //            user.setEmail(email);
                                    user.setChannel_id(PushTestReceiver.channe_id);
                                    Log.i("cid",PushTestReceiver.channe_id);
                                   // user.setMobilePhoneNumber(phonenumber);
                                    BmobQuery<User> query = new BmobQuery<User>();
                                    user.signUp(SetingsUserActivity.this, new SaveListener() {
                                        @Override
                                        public void onSuccess() {
                                            Log.i("cha", "onSuccess 发布成功");
                                            Toast.makeText(SetingsUserActivity.this, "资料完善成功", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(SetingsUserActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            //                                pd.dismiss();
                                        }

                                        @Override
                                        public void onFailure(int i, String s) {
                                            Log.i("cha", "onSuccess 失败" + s);
                                            pd.dismiss();
                                            //  Toast.makeText(SetingsUserActivity.this, "您的手机号已注册，请重新输入", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }

                                @Override
                                public void onProgress(int i) {

                                }

                                @Override
                                public void onError(int i, String s) {
                                    pd.dismiss();
                                    Log.i("cha", "onError是:" + s);
                                }
                            });
                        }

                }
            }
    }
    }
    //邮箱验证方法
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    //返回头像路径方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("ha", "ewrw");
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                // Get the result list of select image paths
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                for (String s:path)
                {
                    Log.i("ha",s);
                }
                mFilePath = path.get(0).toString();
                mheadlog.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
            }
        }
    }



}
