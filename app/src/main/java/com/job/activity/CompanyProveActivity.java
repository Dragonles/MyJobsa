package com.job.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;
import com.job.bean.Classify;
import com.job.bean.CompanyProve;
import com.job.bean.User;

import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import fragment.PerFragment;
import me.nereo.imagechoose.MultiImageSelectorActivity;

public class CompanyProveActivity extends AppCompatActivity {

    EditText cp_product,cp_name,cp_address,cp_number;
    Button cp_type,msubmit,cp_date;
    ImageView co_logo;
    public static EditText cp_person;
    private  static  final int REQUEST_IMAGE=2;
    private String mFilePath;
    String[] splist;
    DatePicker dp;
    Calendar ca;
    int sp=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_prove);
        co_logo=(ImageView)findViewById(R.id.cp_logo);
        cp_address=(EditText)findViewById(R.id.cp_address);
        cp_date=(Button)findViewById(R.id.cp_date);
        cp_person=(EditText)findViewById(R.id.cp_companyperson);
        cp_name=(EditText)findViewById(R.id.cp_name);
        ca = Calendar.getInstance();
        cp_product=(EditText)findViewById(R.id.cp_product);
        cp_number=(EditText)findViewById(R.id.cp_number);
        dp=(DatePicker)findViewById(R.id.datePicker1);
        cp_type=(Button)findViewById(R.id.cp_type);
        msubmit=(Button)findViewById(R.id.prove_submit);
        Bmob.initialize(CompanyProveActivity.this, "e98c629c488e891e6d090798dd2ced7f");
        final BmobUser bmobUser = BmobUser.getCurrentUser(CompanyProveActivity.this);
        final BmobQuery<Classify> cl = new BmobQuery<>();
        cl.findObjects(CompanyProveActivity.this, new FindListener<Classify>() {
            @Override
            public void onSuccess(List<Classify> list) {
                splist = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    splist[i] = list.get(i).getClassify_name();
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.i("classify", "fa");
            }
        });

        dp.init(ca.get(Calendar.YEAR),ca.get(Calendar.DAY_OF_MONTH),ca.get(Calendar.MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        // TODO Auto-generated method stub
                        ca.set(year, monthOfYear, dayOfMonth);
                        cp_date.setText(year + "年" + monthOfYear + "月" + dayOfMonth
                                + "日");// 文本框显示日期

                    }
                });
        cp_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                new DatePickerDialog(CompanyProveActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                cp_date.setText(year + "年" + monthOfYear + "月" + dayOfMonth
                                        + "日");// 文本框显示日期


                            }

                        }, ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca
                        .get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(CompanyProveActivity.this);
            cp_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.setTitle("类型选择");
                    builder.setIcon(android.R.drawable.button_onoff_indicator_off);
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
                    builder.setSingleChoiceItems(splist, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cp_type.setText(splist[which]);
                            sp = which;
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
        });
        co_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyProveActivity.this, MultiImageSelectorActivity.class);
// whether show camera
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
// max select image amount
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
// select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });
        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog pd = ProgressDialog.show(CompanyProveActivity.this, "正在提交", "");
                Bmob.initialize(CompanyProveActivity.this, "e98c629c488e891e6d090798dd2ced7f");
                BmobProFile.getInstance(getApplication()).upload(mFilePath, new UploadListener() {
                    @Override
                    public void onSuccess(String s, String s1, BmobFile bmobFile) {
                        Log.i("id", "qqqqqqqqqqqqqqqqq");
                        User user = new User();
                        BmobUser bmobUser = BmobUser.getCurrentUser(CompanyProveActivity.this);
                        CompanyProve com = new CompanyProve();
                        com.setCp_logo(bmobFile.getUrl());
                        com.setCp_name(cp_name.getText().toString());
                        com.setCp_date(cp_date.getText().toString());
                        com.setCp_companyperson(cp_person.getText().toString());
                        com.setCp_address(cp_address.getText().toString());
                        com.setUser_id(bmobUser.getObjectId());
                        com.setCp_type(sp+"");
                        com.setCp_product(cp_product.getText().toString());
                        com.setCp_number(cp_number.getText().toString());
                        BmobQuery<User> query = new BmobQuery<User>();
                        com.save(CompanyProveActivity.this, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Log.i("cha", "onSuccess 发布成功");
                                Toast.makeText(CompanyProveActivity.this, "认证已发送，请等待审核", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(CompanyProveActivity.this, MainActivity.class);
                                startActivity(intent);
                                //                                pd.dismiss();
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Log.i("cha", "onSuccess 失败" + s);
                                pd.dismiss();
                                Toast.makeText(CompanyProveActivity.this, "发送失败", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onProgress(int i) {

                    }

                    @Override
                    public void onError(int i, String s) {
                        pd.dismiss();
                        Toast.makeText(CompanyProveActivity.this, "失败", Toast.LENGTH_LONG).show();
                        Log.i("cha", "onError是:" + s);
                    }
                });
            }
        });
    }

    public void back(View v)
    {
        this.finish();
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
                co_logo.setImageBitmap(BitmapFactory.decodeFile(path.get(0)));
            }
        }
    }
}
