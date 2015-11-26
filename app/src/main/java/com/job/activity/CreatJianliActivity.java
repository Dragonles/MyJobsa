package com.job.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;
import com.job.bean.CompanyProve;
import com.job.bean.UserRelease;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import me.nereo.imagechoose.MultiImageSelectorActivity;

public class CreatJianliActivity extends AppCompatActivity {

    EditText name,xueli,jinyan,sarlary,address;
    RadioGroup radioGroup1;
    Button chosebirth,submit,update;
    RadioButton getboy,getgirl;
    String imgfile;
    CircleImageView user_img;
    TextView getdate;
    DatePicker dp;
    String sexs;
    private String mFilePath;//图片路径
    private  static  final int REQUEST_IMAGE=2;
    Calendar ca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_jianli);
        name=(EditText)findViewById(R.id.setname);
        xueli=(EditText)findViewById(R.id.setxueli);
        jinyan=(EditText)findViewById(R.id.setjingyan);
        ca = Calendar.getInstance();
        sarlary=(EditText)findViewById(R.id.setsalary);
        user_img=(CircleImageView)findViewById(R.id.choseimg);
        address=(EditText)findViewById(R.id.setaddress);
        radioGroup1=(RadioGroup)findViewById(R.id.setradiogroup);
        chosebirth=(Button)findViewById(R.id.setbirth);
        getboy=(RadioButton)findViewById(R.id.setman);
        getdate=(TextView)findViewById(R.id.getdate);
        dp=(DatePicker)findViewById(R.id.datePicker1);
        getgirl=(RadioButton)findViewById(R.id.setwuman);
        update=(Button)findViewById(R.id.updatejianli);
        submit=(Button)findViewById(R.id.jianli_submit);
        //name.setEnabled(false);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             *
             * 性别判断
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == getboy.getId()) {
                    Toast.makeText(CreatJianliActivity.this, getboy.getText(), Toast.LENGTH_LONG).show();
                    sexs = "男";

                } else {
                    Toast.makeText(CreatJianliActivity.this, getgirl.getText(), Toast.LENGTH_LONG).show();
                    sexs = "女";

                }

            }
        });
        dp.init(ca.get(Calendar.YEAR), ca.get(Calendar.DAY_OF_MONTH), ca.get(Calendar.MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        ca.set(year, monthOfYear, dayOfMonth);
                        getdate.setText(year + "年" + monthOfYear + "月" + dayOfMonth
                                + "日");// 文本框显示日期
                    }
                });
        chosebirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                new DatePickerDialog(CreatJianliActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                chosebirth.setText(year + "年" + monthOfYear + "月" + dayOfMonth
                                        + "日");// 文本框显示日期


                            }

                        }, ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca
                        .get(Calendar.DAY_OF_MONTH)).show();
            }

        });
        user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatJianliActivity.this, MultiImageSelectorActivity.class);
// whether show camera
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
// max select image amount
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
// select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BmobUser bu = BmobUser.getCurrentUser(CreatJianliActivity.this);
                final ProgressDialog pd = ProgressDialog.show(CreatJianliActivity.this, "正在提交简历,请等待", "");
                Bmob.initialize(CreatJianliActivity.this, "e98c629c488e891e6d090798dd2ced7f");
                BmobProFile.getInstance(getApplication()).upload(mFilePath, new UploadListener() {
                    @Override
                    public void onSuccess(String s, String s1, BmobFile bmobFile) {
                        UserRelease gameScore = new UserRelease();
                        gameScore.setUr_name(name.getText().toString());
                        imgfile=bmobFile.getUrl();
                        name.setTextColor(Color.CYAN);
                        gameScore.setBirthday(chosebirth.getText().toString());
                        gameScore.setUr_address(address.getText().toString());
                        gameScore.setUr_money(sarlary.getText().toString());
                        gameScore.setUr_sex(sexs);
                        gameScore.setUser_id(bu.getObjectId());
                        gameScore.setUr_icon(bmobFile.getUrl());
                        gameScore.setUr_study(xueli.getText().toString());
                        gameScore.setUr_work_time(jinyan.getText().toString());
                        gameScore.save(CreatJianliActivity.this, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(CreatJianliActivity.this, "简历提交成功", Toast.LENGTH_LONG).show();
                                pd.dismiss();
                            }

                            @Override
                            public void onFailure(int code, String arg0) {
                                Toast.makeText(CreatJianliActivity.this, "简历提交失败", Toast.LENGTH_LONG).show();
                                Log.i("jisli", code + arg0);
                                pd.dismiss();
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
        });
        Bmob.initialize(CreatJianliActivity.this, "e98c629c488e891e6d090798dd2ced7f");
        BmobUser bu = BmobUser.getCurrentUser(CreatJianliActivity.this);
        BmobQuery<UserRelease> cpbq = new BmobQuery<UserRelease>();
        Log.i("ddfdfdfdf", "" + bu.getObjectId());
        if (bu != null) {
            cpbq.addWhereEqualTo("user_id", bu.getObjectId());
            cpbq.findObjects(CreatJianliActivity.this, new FindListener<UserRelease>() {
                @Override
                public void onSuccess(final List<UserRelease> list) {
                    Picasso.with(CreatJianliActivity.this).load(list.get(0).getUr_icon()).into(user_img);
                    Log.i("img",list.get(0).getUr_icon());
                    name.setText(list.get(0).getUr_name().toString());
                    String s=list.get(0).getUr_sex().toString();
                    if (s.equals("男"))
                    {
                        getboy.setChecked(true);
                    }
                    else {
                        getgirl.setChecked(true);
                    }
                    String date=list.get(0).getBirthday().toString();
                    chosebirth.setText(date);
                    xueli.setText(list.get(0).getUr_study().toString());
                    jinyan.setText(list.get(0).getUr_work_time().toString());
                    sarlary.setText(list.get(0).getUr_money().toString());
                    address.setText(list.get(0).getUr_address().toString());
                    if (list.size()!=0)
                    {
                        update.setVisibility(View.VISIBLE);
                        submit.setVisibility(View.GONE);
                        final UserRelease userRelease =new UserRelease();
                        update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UserRelease gameScore = new UserRelease();
                                gameScore.setUr_name(name.getText().toString());
                                gameScore.setUr_icon(imgfile);
                                gameScore.setUr_sex(sexs);
                                gameScore.setBirthday(chosebirth.getText().toString());
                                gameScore.setUr_study(xueli.getText().toString());
                                gameScore.setUr_work_time(jinyan.getText().toString());
                                gameScore.setUr_money(sarlary.getText().toString());
                                gameScore.setUr_address(address.getText().toString());
                                gameScore.update(CreatJianliActivity.this,list.get(0).getObjectId(), new UpdateListener() {

                                    @Override
                                    public void onSuccess() {
                                        // TODO Auto-generated method stub
                                        Log.i("bmob","更新成功：");
                                    }

                                    @Override
                                    public void onFailure(int code, String msg) {
                                        // TODO Auto-generated method stub
                                        Log.i("bmob","更新失败："+msg);
                                    }
                                });
                            }
                        });
                    }
                }
                @Override
                public void onError(int i, String s) {
                    Log.i("ss", i + s);
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
                user_img.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
            }
        }
    }
}
