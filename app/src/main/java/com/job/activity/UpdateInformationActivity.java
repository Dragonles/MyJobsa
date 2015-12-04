package com.job.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;
import com.job.bean.CompanyProve;
import com.job.bean.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import me.nereo.imagechoose.MultiImageSelectorActivity;

/**
 * UpdateInformationActivity  修改资料页
 *
 * from ZengQing
 * */

public class UpdateInformationActivity extends AppCompatActivity {

    CircleImageView img_update_userheader;// 用户头像
    EditText et_search;
    String searchC;
    String icon_url;
    private String mFilePath;//图片路径
    private  static  final int REQUEST_IMAGE=2;
    TextView tv_update_username,tv_update_email,tv_update_tel,tv_update_password; // 用户名 性别 邮箱 电话
    Button btn_update_information_username,btn_update_information,btn_update_information_password,btn_update_information_email,btn_update_information_tel;// 按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information);
        img_update_userheader=(CircleImageView)findViewById(R.id.user_cion);
        tv_update_username=(TextView)findViewById(R.id.tv_update_username);
        tv_update_password=(TextView)findViewById(R.id.tv_update_password);
        tv_update_tel=(TextView)findViewById(R.id.tv_update_tel);
        btn_update_information=(Button)findViewById(R.id.update_message);


        tv_update_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(UpdateInformationActivity.this);
                LayoutInflater inflater = (LayoutInflater) UpdateInformationActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialogview, null);
                dialog.setView(layout);
                et_search = (EditText) layout.findViewById(R.id.searchC);
                dialog.setTitle("输入更改的用户名");
                dialog.setIcon(android.R.drawable.btn_radio);
                dialog.setCancelable(true);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        searchC = et_search.getText().toString();
                        tv_update_username.setText(searchC);
                    }
                });

                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });




        Bmob.initialize(UpdateInformationActivity.this, "e98c629c488e891e6d090798dd2ced7f");
        final BmobUser bmobUser = BmobUser.getCurrentUser(UpdateInformationActivity.this);
        if(bmobUser != null) {

            tv_update_username.setText(bmobUser.getUsername());
            //    tv_update_username.setText(bmobUser.getUsername());
            BmobQuery<User> query = new BmobQuery<User>();
            User user =new User();
            query.getObject(UpdateInformationActivity.this, bmobUser.getObjectId(), new GetListener<User>() {
                @Override
                public void onSuccess(User object) {
                    Picasso.with(UpdateInformationActivity.this).load(object.getUser_icon()).into(img_update_userheader);
                    // tv_update_password.setText(object.getPassword());
                    icon_url=object.getJianli_icon();
                    //       Log.i("coe", object.getPassword().toString());

                }
                @Override
                public void onFailure(int code, String arg0) {
                    // TODO Auto-generated method stub
                    Log.i("ss", code + arg0);
                }

            });
            img_update_userheader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UpdateInformationActivity.this, MultiImageSelectorActivity.class);
                    // whether show camera
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                    // max select image amount
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
                    // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
                    startActivityForResult(intent, REQUEST_IMAGE);
                }
            });
            btn_update_information.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("smile", "aa");
                    final ProgressDialog pd = ProgressDialog.show(UpdateInformationActivity.this, "正在修改", "");
                    Bmob.initialize(UpdateInformationActivity.this, "e98c629c488e891e6d090798dd2ced7f");
                    BmobProFile.getInstance(getApplication()).upload(mFilePath, new UploadListener() {
                        @Override
                        public void onSuccess(String s, String s1, final BmobFile bmobFile) {
                            //
                            BmobUser newUser = new BmobUser();
                            final User user1 = new User();
                            user1.setUser_icon(bmobFile.getUrl());

                            newUser.setUsername(searchC);
                            final BmobUser bmobUser = BmobUser.getCurrentUser(UpdateInformationActivity.this);
                            newUser.update(UpdateInformationActivity.this, bmobUser.getObjectId(), new UpdateListener() {
                                @Override
                                public void onSuccess() {
                                    // TODO Auto-generated method stub
                                    // toast("更新用户信息成功:");
                                    pd.dismiss();
                                }

                                @Override
                                public void onFailure(int code, String msg) {
                                    // TODO Auto-generated method stub
                                    //  toast("更新用户信息失败:" + msg);
                                }
                            });
                            BmobUser.updateCurrentUserPassword(UpdateInformationActivity.this, tv_update_password.getText().toString(), tv_update_tel.getText().toString(), new UpdateListener() {
                                @Override
                                public void onSuccess() {
                                    // TODO Auto-generated method stub
                                    Log.i("smile", "密码修改成功，可以用新密码进行登录啦" + bmobFile.getUrl());

                                    user1.update(UpdateInformationActivity.this, bmobUser.getObjectId(), new UpdateListener() {

                                        @Override
                                        public void onSuccess() {
                                            // TODO Auto-generated method stub
                                            Log.i("bmob", "更新成功：");
                                            Log.i("id", bmobFile.getUrl());
                                        }

                                        @Override
                                        public void onFailure(int code, String msg) {
                                            // TODO Auto-generated method stub
                                            Log.i("bmob", "更新失败：" + msg);
                                        }
                                    });
                                    Toast.makeText(UpdateInformationActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                                    UpdateInformationActivity.this.finish();


                                }

                                @Override
                                public void onFailure(int code, String msg) {
                                    // TODO Auto-generated method stub
                                    Toast.makeText(UpdateInformationActivity.this, "原密码输入错误", Toast.LENGTH_SHORT).show();
                                    Log.i("smile", "密码修改失败：" + msg + "(" + code + ")");
                                }
                            });

                        }

                        @Override
                        public void onProgress(int i) {
                            pd.dismiss();
                        }

                        @Override
                        public void onError(int i, String s) {
                            pd.dismiss();
                            Log.i("cha", "onError是:" + s);
                        }
                    });
                }
            });
        }

    }
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
                img_update_userheader.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
            }
        }
    }
    // 返回键点击事件
    public void up_back(View v){
        this.finish();
    }

}
