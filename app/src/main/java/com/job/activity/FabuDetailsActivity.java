package com.job.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.job.bean.Classify;
import com.job.bean.CompanyRelease;
import com.job.utils.Text;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.SaveListener;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.picker.WheelPicker;

/**
 * 招聘者 发布详情页
 * 2015-11-19
 * yanhao
 * */

public class FabuDetailsActivity extends AppCompatActivity {

    /**
     * onCreate方法
     * */
    /**
     * 修改工作时间
     * */
    private Calendar c = null;
    private Calendar c1 = null;
    private final static int DATE_START = 0;
    private final static int DATE_END = 1;
    /**
     * 修改工作时间
     * */

    ImageButton back;      //返回按钮
    //工作地点  工作时间    工作薪资  要求学历  工作人数  工作要求  联系人  联系电话
    EditText edit_fabu_job_xinzi,
            edit_fabu_job_renshu,edit_fabu_job_yaoqiu,
            edit_fabu_job_lianxiren,edit_fabu_job_phone;
    Button btn_zhaopin,btn_fabu_job_jiesuan, btn_fabu_job_zhiwei,
            btn_fabu_job_xueli, btn_fabu_job_date_start,
            btn_fabu_job_date_end,btn_fabu_job_didian;     //点击发布招聘    选择结算类型按工作职位  学历  选择时间
    CheckBox checkbox_fabu_job_jiaji;       //选择是否加急
    String[] classifys = {"元/天", "元/周", "元/月", "月结", "周结", "日结", "面议"};  //结算方式
    String[] classifyz;
    //修改学历  将EditText  改为Button
    String[] education = {"不限", "高中以下", "高中", "中专/技校", "大专", "本科",
            "硕士" ,"博士", "MBA/EMBA"};  //学历

    int pk;
    int sp,zy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabu_details);
        btn_zhaopin = (Button) findViewById(R.id.btn_zhaopin);      //点击发布按钮
        btn_fabu_job_jiesuan = (Button) findViewById(R.id.btn_fabu_job_jiesuan);//点击选择结算
        checkbox_fabu_job_jiaji = (CheckBox) findViewById(R.id.checkbox_fabu_job_jiaji);    //选择是否加急
        btn_fabu_job_didian = (Button) findViewById(R.id.btn_fabu_job_didian);  //工作地点
        /*
        * 修改工作时间
        * */
        btn_fabu_job_date_start = (Button) findViewById(R.id.btn_fabu_job_date_start);      //工作开始时间
        btn_fabu_job_date_end = (Button) findViewById(R.id.btn_fabu_job_date_end);//工作停止时间
        /***/

        btn_fabu_job_zhiwei = (Button) findViewById(R.id.edit_fabu_job_zhiwei);  //工作职位
        edit_fabu_job_xinzi = (EditText) findViewById(R.id.edit_fabu_job_xinzi);//工作薪资

        /*修改学历*/
        btn_fabu_job_xueli = (Button) findViewById(R.id.edit_fabu_job_xueli);    //要求学历
        /**/

        edit_fabu_job_renshu = (EditText) findViewById(R.id.edit_fabu_job_renshu);//工作人数
        edit_fabu_job_yaoqiu = (EditText) findViewById(R.id.edit_fabu_job_yaoqiu);//工作要求
        edit_fabu_job_lianxiren = (EditText) findViewById(R.id.edit_fabu_job_lianxiren);  //联系人
        edit_fabu_job_phone = (EditText) findViewById(R.id.edit_fabu_job_phone);//联系电话
        back = (ImageButton) findViewById(R.id.fabuactivity_back);

        //接收数据
        Intent intent = getIntent();
        pk = intent.getIntExtra("poskey", -1);


        /**
         * 选择省 城  区（县）
         * */
        btn_fabu_job_didian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texts = Text.getText();
                final ArrayList<String> option1 = new ArrayList<String>();
                final ArrayList<ArrayList<String>> option2 = new
                        ArrayList<ArrayList<String>>();
                final ArrayList<ArrayList<ArrayList<String>>> option3 = new
                        ArrayList<ArrayList<ArrayList<String>>>();
                try {
                    JSONObject jsonObject = new JSONObject(texts);
                    JSONArray sheng = jsonObject.getJSONArray("p");
                    JSONObject shi = jsonObject.getJSONObject("c");
                    JSONObject qu = jsonObject.getJSONObject("a");
                    for (int i = 0; i < sheng.length(); i++) {
                        JSONArray shi01 = shi.getJSONArray(sheng.getString(i));
                        ArrayList<String> option2_1 = new ArrayList<String>();
                        ArrayList<ArrayList<String>> option3_1 = new
                                ArrayList<ArrayList<String>>();
                        for (int j = 0; j < shi01.length(); j++) {
                            option2_1.add(shi01.getString(j));
                            JSONArray qu01 = qu.getJSONArray(sheng.getString(i) +
                                    "-" + shi01.getString(j));
                            ArrayList<String> option3_1_1 = new ArrayList<String>
                                    ();
                            for (int k = 0; k < qu01.length(); k++) {
                                option3_1_1.add(qu01.getString(k));
                            }
                            option3_1.add(option3_1_1);
                        }
                        option1.add(sheng.getString(i));
                        option2.add(option2_1);
                        option3.add(option3_1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                OptionPicker picker = new OptionPicker(FabuDetailsActivity.this);
                picker.setOptions(option1, option2, option3);
                picker.setOnWheelListener(new WheelPicker.OnWheelListener<int[]>()
                {
                    @Override
                    public void onSubmit(int[] result) {
                        String province = option1.get(result[0]);
                        String city = option2.get(result[0]).get(result[1]);
                        String district = option3.get(result[0]).get(result
                                [1]).get(result[2]);
                        String results = province + "-" + city + "-" + district;
                        btn_fabu_job_didian.setText(results);
                    }
                });
                picker.showAtBottom();
            }
        });




        //返回箭头  返回上一级
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FabuDetailsActivity.this.finish();
            }
        });

        //获得类型
        BmobQuery<Classify> cl = new BmobQuery<>();
        cl.findObjects(FabuDetailsActivity.this, new FindListener<Classify>() {
            @Override
            public void onSuccess(List<Classify> list) {
                if (pk >= 0){
                    Log.i("fabussssss",pk+list.get(pk).getClassify_name());
                    btn_fabu_job_zhiwei.setText(list.get(pk).getClassify_name());
                }
                classifyz = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    classifyz[i] = list.get(i).getClassify_name();
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });

        /**
         * 修改工作时间
         * */
        //开始工作时间点击事件
        btn_fabu_job_date_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_START);
            }
        });
        //结束工作时间点击事件
        btn_fabu_job_date_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_END);
            }
        });

        //职业点击事件
        btn_fabu_job_zhiwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder btnbuilder = new AlertDialog.Builder
                        (FabuDetailsActivity.this);
                btnbuilder.setTitle("类型选择");
                btnbuilder.setIcon(android.R.drawable.button_onoff_indicator_off);
                btnbuilder.setCancelable(true);

                btnbuilder.setPositiveButton("确认", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                btnbuilder.setNegativeButton("取消", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                btnbuilder.setSingleChoiceItems(classifyz, 0, new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btn_fabu_job_zhiwei.setText(classifyz[which]);
//                        zy = which;
                            }
                        });
                AlertDialog alert = btnbuilder.create();
                alert.show();
//                btn_fabu_job_zhiwei.setText(classifyz[zy]);
            }
        });

        /**
         *修改
         * 学历
         * */
        //学历点击事件
        btn_fabu_job_xueli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder btnbuilder = new AlertDialog.Builder
                        (FabuDetailsActivity.this);
                btnbuilder.setTitle("学历选择");
                btnbuilder.setIcon(android.R.drawable.button_onoff_indicator_off);
                btnbuilder.setCancelable(true);

                btnbuilder.setPositiveButton("确认", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                btnbuilder.setNegativeButton("取消", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                btnbuilder.setSingleChoiceItems(education, 0, new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btn_fabu_job_xueli.setText(education[which]);
//                        zy = which;
                            }
                        });
                AlertDialog alert = btnbuilder.create();
                alert.show();
            }
        });

        //结算点击事件
        btn_fabu_job_jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder btnbuilder = new AlertDialog.Builder
                        (FabuDetailsActivity.this);
                btnbuilder.setTitle("类型选择");
                btnbuilder.setIcon(android.R.drawable.button_onoff_indicator_off);
                btnbuilder.setCancelable(true);

                btnbuilder.setPositiveButton("确认", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                btnbuilder.setNegativeButton("取消", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                btnbuilder.setSingleChoiceItems(classifys, 0, new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btn_fabu_job_jiesuan.setText(classifys[which]);
//                        sp = which;
                            }
                        });
                AlertDialog alert = btnbuilder.create();
                alert.show();
//                btn_fabu_job_jiesuan.setText(classifys[sp]);
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder
                (FabuDetailsActivity.this);

        Bmob.initialize(this, "e98c629c488e891e6d090798dd2ced7f");
        final BmobUser bmobUser = BmobUser.getCurrentUser(this);
        if (bmobUser != null){
            Log.i("fabudetailsss", "登陆了");
            btn_zhaopin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String didian = btn_fabu_job_didian.getText().toString();    //工作地点
                    String date = btn_fabu_job_date_start.getText().toString()+"至"+btn_fabu_job_date_end.getText().toString();  //工作时间//工作时间
                    String zhiwei = btn_fabu_job_zhiwei.getText().toString();   //工作职位
                    Integer xinzi = Integer.valueOf(edit_fabu_job_xinzi.getText
                            ().toString());      //工作薪资
                    String xueli = btn_fabu_job_xueli.getText().toString();   //要求学历
                    Integer integer = Integer.valueOf(edit_fabu_job_renshu.getText
                            ().toString());//获得工作人数                                        //工作人数
                    String yaoqiu = edit_fabu_job_yaoqiu.getText().toString(); //工作要求
                    String jiaji = checkbox_fabu_job_jiaji.isChecked()+""; //加急
                    String lianxiren = edit_fabu_job_lianxiren.getText().toString
                            ();  //联系人
                    String phone = edit_fabu_job_phone.getText().toString();  //联系方式

                    if (didian == "" || date == "" || zhiwei == "" || xinzi == null
                            || xueli == "" || integer == null || yaoqiu == ""){
                        Toast.makeText(FabuDetailsActivity.this, "请将信息填写完整", Toast.LENGTH_SHORT).show();
                    }else {
                        CompanyRelease companyRelease = new CompanyRelease();
                        companyRelease.setUser_id(bmobUser.getObjectId());      //用户ID
                        companyRelease.setCr_address(didian);    //工作地点
                        companyRelease.setCr_companydate(date);  //工作时间
                        companyRelease.setCr_position(zhiwei);   //工作职位
                        companyRelease.setCr_salary(xinzi);      //工作薪资
                        companyRelease.setCr_education(xueli);   //要求学历
                        companyRelease.setCr_count(integer);     //工作人数
                        companyRelease.setCr_require(yaoqiu);    //工作要求
                        companyRelease.setCr_payments(btn_fabu_job_jiesuan.getText
                                ().toString());   //结算方式
                        companyRelease.setCr_urgency(jiaji);  //加急
                        companyRelease.setCr_person(lianxiren);  //联系人
                        companyRelease.setCr_infor(phone);      //联系方式
                        companyRelease.save(FabuDetailsActivity.this, new
                                SaveListener() {
                                    @Override
                                    public void onSuccess() {
                                        Toast.makeText(getApplicationContext(), "提交成功",
                                                Toast.LENGTH_LONG).show();
                                        FabuDetailsActivity.this.finish();
                                    }

                                    @Override
                                    public void onFailure(int i, String s) {
                                        Log.i("onfailure",i+s);
                                        Toast.makeText(getApplicationContext(), "提交失败",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                    }

                }
            });

        }else {
            Log.i("fabudetailsss", "没有登陆");

            builder.setTitle("类型选择");
            builder.setIcon(android.R.drawable.ic_dialog_info);
            builder.setCancelable(true);
            builder.setMessage("请先登录");
            builder.setPositiveButton("点击登录", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //跳转到登录页面
                            Intent intent = new Intent(FabuDetailsActivity.this,
                                    LoginActivity.class);
                            startActivity(intent);
                        }
                    });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //取消
                    FabuDetailsActivity.this.finish();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }

    //选择日期
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case DATE_START:
                c = Calendar.getInstance();
                dialog = new DatePickerDialog(
                        this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker dp, int year,int
                                    month, int dayOfMonth) {
                                btn_fabu_job_date_start.setText(year + "-" + (month
                                        + 1) + "-" + dayOfMonth);
                            }
                        },
                        c.get(Calendar.YEAR), // 传入年份
                        c.get(Calendar.MONTH), // 传入月份
                        c.get(Calendar.DAY_OF_MONTH) // 传入天数
                );
                break;
            case DATE_END:
                c1 = Calendar.getInstance();
                dialog = new DatePickerDialog(
                        this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker dp, int year,int
                                    month, int dayOfMonth) {
                                btn_fabu_job_date_end.setText(year + "-" + (month +
                                        1) + "-" + dayOfMonth);
                            }
                        },
                        c1.get(Calendar.YEAR), // 传入年份
                        c1.get(Calendar.MONTH), // 传入月份
                        c1.get(Calendar.DAY_OF_MONTH) // 传入天数
                );
                break;

        }
        return dialog;
    }

}
