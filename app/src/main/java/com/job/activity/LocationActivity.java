package com.job.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.job.adapter.JobShouruAdapter;
import com.job.adapter.LocartionAdapter;
import com.job.bean.Classify;
import com.job.bean.CompanyProve;
import com.job.bean.CompanyRelease;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class LocationActivity extends Activity {

    ListView listview;
    List<CompanyRelease> companyReleaseList;
    EditText editText;
    Button ib_sousuo;
    LocartionAdapter adapter;
    RelativeLayout tv_back;
    Button btn_gongzi,btn_diqu,btn_more;
    int sp;
    String ss;
    String classify;
    int pk;
    String[] diquming = {"罗庄","兰山","河东"};
    boolean js = false;
    Editable s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        editText = (EditText) findViewById(R.id.editText);
        tv_back = (RelativeLayout) findViewById(R.id.tv_back);
        ib_sousuo = (Button) findViewById(R.id.ib_sousuo);
        listview = (ListView) findViewById(R.id.lv_fenlei);
        btn_gongzi = (Button) findViewById(R.id.btn_gongzi);
        btn_diqu = (Button) findViewById(R.id.btn_diqu);
        btn_more = (Button) findViewById(R.id.btn_more);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationActivity.this.finish();
            }
        });

        //接收数据
        Intent intent = getIntent();
        pk = intent.getIntExtra("poskey", -1);

        s = editText.getText();
        //输入框
        //更多分类按钮， 点击跳转到 所有分类页面
        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LocationActivity.this, AllClassActivity.class);
                startActivity(intent1);
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(getApplicationContext(), "点击了搜索", Toast.LENGTH_LONG).show();
                    js = true;
                    return true;
                }
                return false;
            }
        });


        if (pk != -1) {
            BmobQuery<Classify> cibq = new BmobQuery<>();
            cibq.findObjects(this, new FindListener<Classify>() {
                @Override
                public void onSuccess(List<Classify> list) {
                    classify = list.get(pk).getClassify_name();
                    editText.setText(list.get(pk).getClassify_name());
//                    bq(pk);
                    BmobQuery<CompanyRelease> query = new BmobQuery<CompanyRelease>();
                    query.addWhereEqualTo("cr_position", list.get(pk).getClassify_name());
                    query.findObjects(LocationActivity.this, new FindListener<CompanyRelease>() {
                        @Override
                        public void onSuccess(List<CompanyRelease> list) {
                            // TODO Auto-generated method stub
                            companyReleaseList = list;

                            adapter = new LocartionAdapter(getApplicationContext(), companyReleaseList);
                            listview.setAdapter(adapter);
                        }

                        @Override
                        public void onError(int code, String msg) {
                            // TODO Auto-generated method stub

                        }
                    });
                    Log.i("locationActivityss", "" + pk);
                    js = true;

                }

                @Override
                public void onError(int i, String s) {

                }
            });
        }


        Bmob.initialize(this,"e98c629c488e891e6d090798dd2ced7f");

        //搜索
        ib_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("sssaa", "555555");
                BmobQuery<CompanyRelease> query = new BmobQuery<CompanyRelease>();
                query.addWhereEqualTo("cr_position", s);
                query.findObjects(LocationActivity.this, new FindListener<CompanyRelease>() {
                    @Override
                    public void onSuccess(List<CompanyRelease> list) {
                        // TODO Auto-generated method stub
                        companyReleaseList = list;

                        adapter = new LocartionAdapter(getApplicationContext(), companyReleaseList);
                        listview.setAdapter(adapter);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        // TODO Auto-generated method stub

                    }
                });
            }
        });

        //选择工资排序
        btn_gongzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("sssaa", "44444444");
                BmobQuery<CompanyRelease> query = new BmobQuery<CompanyRelease>();
                query.addWhereEqualTo("cr_position", s);
                query.order("cr_salary");
                query.findObjects(LocationActivity.this, new FindListener<CompanyRelease>() {
                    @Override
                    public void onSuccess(List<CompanyRelease> list) {
                        // TODO Auto-generated method stub
                        companyReleaseList = list;

                        adapter = new LocartionAdapter(getApplicationContext(), companyReleaseList);
                        listview.setAdapter(adapter);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        // TODO Auto-generated method stub

                    }
                });
            }
        });

        //选择地区按钮
        btn_diqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(LocationActivity.this);

                builder.setTitle("请选择");
                builder.setIcon(android.R.drawable.button_onoff_indicator_off);
                builder.setCancelable(true);

                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Log.i("sssaa", sp + "");
//
                        if (sp == 0) {
                            ss = "罗庄";
                        } else if (sp == 1) {
                            ss = "兰山";
                        } else if (sp == 1) {
                            ss = "河东";
                        }

                        Log.i("sssaa", "ss = " + ss);

                        BmobQuery<CompanyRelease> b1 = new BmobQuery<CompanyRelease>();
                        b1.addWhereEqualTo("cr_address", ss);

                        Log.i("sssaa", "s = " + s);

                        BmobQuery<CompanyRelease> b2 = new BmobQuery<CompanyRelease>();
                        b2.addWhereEqualTo("cr_position", s);

                        List<BmobQuery<CompanyRelease>> andQuerys = new ArrayList<BmobQuery<CompanyRelease>>();
                        andQuerys.add(b1);
                        andQuerys.add(b2);

                        BmobQuery<CompanyRelease> query = new BmobQuery<CompanyRelease>();
                        query.and(andQuerys);
                        query.findObjects(LocationActivity.this, new FindListener<CompanyRelease>() {
                            @Override
                            public void onSuccess(List<CompanyRelease> list) {
                                // TODO Auto-generated method stub
                                companyReleaseList = list;

                                Log.i("sssaa", "list : " + list.size() + "");
                                adapter = new LocartionAdapter(getApplicationContext(), companyReleaseList);
                                listview.setAdapter(adapter);
                            }

                            @Override
                            public void onError(int code, String msg) {
                                // TODO Auto-generated method stub

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
        //listView 点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(LocationActivity.this, FabuJobDetailsActivity.class);
                intent1.putExtra("keypos", position);
                intent1.putExtra("keyClassify",classify);
                startActivity(intent1);
            }
        });

    }

    //homefragment 点击GridView
    public void bq(int p) {
        Log.i("locationActivityss", "** "+s);
        BmobQuery<CompanyRelease> query = new BmobQuery<CompanyRelease>();
        query.addWhereEqualTo("cr_position", s);
        query.findObjects(LocationActivity.this, new FindListener<CompanyRelease>() {
            @Override
            public void onSuccess(List<CompanyRelease> list) {
                // TODO Auto-generated method stub
                companyReleaseList = list;

                adapter = new LocartionAdapter(getApplicationContext(), companyReleaseList);
                listview.setAdapter(adapter);
            }

            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub

            }
        });
    }

}

