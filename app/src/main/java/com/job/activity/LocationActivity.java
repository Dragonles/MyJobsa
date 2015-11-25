package com.job.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.job.adapter.LocartionAdapter;
import com.job.bean.Classify;
import com.job.bean.CompanyRelease;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class LocationActivity extends AppCompatActivity {

    ListView listview;
    EditText et_sousuo;
    List<CompanyRelease> companyReleaseList;
    int classifyid,sp;
    LocartionAdapter adapter;
    ImageButton ib_sousuo,ib_back;
    Button btn_gongzi,btn_diqu,btn_more;
    String[] diquming = {"罗庄", "兰山", "河东"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        listview = (ListView) findViewById(R.id.lv_fenlei);
        et_sousuo = (EditText) findViewById(R.id.editText);
        ib_sousuo = (ImageButton) findViewById(R.id.ib_sousuo);
        ib_back = (ImageButton) findViewById(R.id.ib_back);

        btn_gongzi = (Button) findViewById(R.id.btn_gongzi);
        btn_diqu = (Button) findViewById(R.id.btn_diqu);
        btn_more = (Button) findViewById(R.id.btn_more);

        final Editable s = et_sousuo.getText();
        final Classify classify = new Classify();

        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationActivity.this.finish();
            }
        });

        Bmob.initialize(this, "e98c629c488e891e6d090798dd2ced7f");
        ib_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("crbqss", "s:" + String.valueOf(s));


                BmobQuery<CompanyRelease> crbq = new BmobQuery<CompanyRelease>();
                crbq.addWhereEqualTo("cr_position", s);
                crbq.findObjects(LocationActivity.this, new FindListener<CompanyRelease>() {
                    @Override
                    public void onSuccess(List<CompanyRelease> list) {
                        Log.i("crbqss", "成功");
                        companyReleaseList = list;
                        Log.i("crbqss", classifyid + "    " + list.get(0).getCr_position());

                        adapter = new LocartionAdapter(getApplicationContext(), companyReleaseList);
                        listview.setAdapter(adapter);

                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.i("crbqss", "失败" + i + " " + s);
                        if (i == 9016) {
                            Toast.makeText(LocationActivity.this, "请检查网络~", Toast.LENGTH_SHORT).show();
                        }
                        if (i == 9015) {
                            Toast.makeText(LocationActivity.this, "查询无果~", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


        //按工资排序 点击事件
        btn_gongzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BmobQuery<CompanyRelease> money = new BmobQuery<CompanyRelease>();
                money.order("cr_salary");
                money.addWhereEqualTo("cr_position", s);
                money.findObjects(LocationActivity.this, new FindListener<CompanyRelease>() {
                    @Override
                    public void onSuccess(List<CompanyRelease> list) {
                        companyReleaseList = list;
                        adapter = new LocartionAdapter(getApplicationContext(), companyReleaseList);
                        listview.setAdapter(adapter);
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
            }
        });

        //按地区排序 点击事件
        btn_diqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                new AlertDialog.Builder(LocationActivity.this)
//                        .setTitle("请选择")
//                        .setIcon(android.R.drawable.ic_dialog_info)
//                        .setSingleChoiceItems(new String[]{"罗庄", "兰山", "河东"}, 0,
//                                new DialogInterface.OnClickListener() {
//
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
//                                    }
//                                }
//                        )
//                        .setNegativeButton("取消", null)
//                        .show();


                final AlertDialog.Builder builder = new AlertDialog.Builder(LocationActivity.this);

                builder.setTitle("请选择");
                builder.setIcon(android.R.drawable.button_onoff_indicator_off);
                builder.setCancelable(true);

                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Log.i("aaa", "sp = " + String.valueOf(sp));

                        String ss = null;
                        if (sp == 0) {
                            ss = "罗庄";
                        } else if (sp == 1) {
                            ss = "兰山";
                        } else if (sp == 2) {
                            ss = "河东";
                        }

                        BmobQuery<CompanyRelease> b_diqu = new BmobQuery<CompanyRelease>();
                        b_diqu.addWhereEqualTo("cr_address", ss);
                        b_diqu.findObjects(LocationActivity.this, new FindListener<CompanyRelease>() {
                            @Override
                            public void onSuccess(List<CompanyRelease> list) {

                                companyReleaseList = list;
                                Log.i("crbqss", classifyid + "    " + list.get(0).getCr_position());

                                adapter = new LocartionAdapter(getApplicationContext(), companyReleaseList);
                                listview.setAdapter(adapter);

                            }

                            @Override
                            public void onError(int i, String s) {
                                Log.i("crbqss", "失败" + i + " " + s);

                            }
                        });


                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setSingleChoiceItems(diquming, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btn_diqu.setText(diquming[which]);
                        sp = which;

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        //更多 点击事件
        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


//        et_sousuo.setOnEditorActionListener(new TextView.OnEditorActionListener {
//
////            @Override
////            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
////                if (actionId == EditorInfo.IME_ACTION_SEARCH){
////                    Toast.makeText(getApplicationContext(),"点击了搜索",Toast.LENGTH_LONG).show();
////                    return true;
////                }
////                return false;
////            }
//        });

        et_sousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(LocationActivity.this, "点击了搜索", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });


    }
}
