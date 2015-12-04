package com.job.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.job.bean.CompanyRelease;
import com.job.bean.User;
import com.job.bean.UserRelease;
import com.job.utils.PushTestReceiver;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.UpdateListener;

public class FabuJobDetailsActivity extends AppCompatActivity {

    public static String baseurl = "https://api.tuisong.baidu.com/rest/3.0/";
    ImageButton txt_back;      //返回键
    Button btn_phone, btn_yingpin;
    TextView txt_job_zhiye,text_job_xinzi,txt_job_xueli,
            txt_job_renshu,txt_job_yaoqiu,txt_job_didian,
            txt_job_date,txtjob_lianxiren,txt_job_phone;
    int pos = 0;
    String name;
    String cid;
    public static String user_cid;
    int posj = -1;
    public  static  String leixing ,names,adress;
    ProgressDialog pd;
    String uid;
    int po = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabu_job_details);
        txt_back = (ImageButton) findViewById(R.id.fabuactivity_back);
        btn_phone = (Button) findViewById(R.id.fabu_btn_phone);
//        TextView tv = (TextView) findViewById(R.id.showchanelid);
//        tv.setText(getIntent().getStringExtra("chanelid"));
        btn_yingpin = (Button) findViewById(R.id.fabu_btn_yingpin);
        txt_job_zhiye = (TextView) findViewById(R.id.edit_fabu_job_zhiye);  //职业
        text_job_xinzi = (TextView) findViewById(R.id.text_fabu_job_xinzi);  //薪资
        txt_job_xueli = (TextView) findViewById(R.id.edit_fabu_job_xueli);  //学历
        txt_job_renshu = (TextView) findViewById(R.id.text_fabu_job_renshu);    //人数
        txt_job_yaoqiu = (TextView) findViewById(R.id.text_fabu_yaoqiu);    //职位要求
        txt_job_didian = (TextView) findViewById(R.id.text_fabu_job_didian);    //地点
        txt_job_date = (TextView) findViewById(R.id.text_fabu_job_date);    //工作时间
        txtjob_lianxiren = (TextView) findViewById(R.id.text_fabu_job_lianxiren);   //联系人
        txt_job_phone = (TextView) findViewById(R.id.text_fabu_job_phone);  ///联系电话
        pd =ProgressDialog.show(FabuJobDetailsActivity.this,"","正在加载数据......");
        btn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+txt_job_phone.getText()));
                startActivity(intent);
            }
        });
        //接收数据
        Intent intent = getIntent();
        pos = intent.getIntExtra("keypos", 0);  //主页热门兼职
        posj = intent.getIntExtra("keyposj",-1);  //主页加急兼职
        //接收数据
        Log.i("fabujobdeatiiiii", ""+posj+pos);
        Bmob.initialize(this, "e98c629c488e891e6d090798dd2ced7f");
        BmobQuery<CompanyRelease> bmobQuery = new BmobQuery<>();
        if (posj != -1) {
            Log.i("fabujobdeatiiiii", ""+posj+pos+"yyyyyyyy");
            bmobQuery.addWhereEqualTo("cr_urgency", "true");
            po = posj;
        }else {
            po = pos;
        }
        bmobQuery.findObjects(FabuJobDetailsActivity.this, new FindListener<CompanyRelease>() {
            @Override
            public void onSuccess(List<CompanyRelease> list) {
                pd.dismiss();
                uid = list.get(po).getUser_id();
                txt_job_zhiye.setText(list.get(po).getCr_position());  //职业
                text_job_xinzi.setText(list.get(po).getCr_salary() + list.get(pos).getCr_payments());   //薪资
//                txt_job_xueli.setText(list.get(po).getCr_education());   //学历
                txt_job_renshu.setText(list.get(po).getCr_count() + "人");     //人数
                txt_job_yaoqiu.setText(list.get(po).getCr_require());    //职位要求
                txt_job_didian.setText(list.get(po).getCr_address());    //地点
                txt_job_date.setText(list.get(po).getCr_companydate());      //工作时间
                txtjob_lianxiren.setText(list.get(po).getCr_person());     //联系人
                txt_job_phone.setText(list.get(po).getCr_infor());    ///联系电话
                leixing=list.get(po).getCr_position();
                names=list.get(po).getCr_position();
                adress=list.get(po).getCr_address();
            }
            @Override
            public void onError(int i, String s) {
                Log.i("s",i+s);
                Toast.makeText(getApplicationContext(), "打开失败", Toast.LENGTH_LONG).show();
            }
        });
        //点击返回上一级
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FabuJobDetailsActivity.this.finish();
            }
        });
    }
    public void sendMessage(View v)
    {

        BmobQuery<UserRelease> query = new BmobQuery<UserRelease>();
        final BmobUser bmobUser =BmobUser.getCurrentUser(FabuJobDetailsActivity.this);
        query.addWhereEqualTo("user_id",uid);
        query.findObjects(this, new FindListener<UserRelease>() {
            @Override
            public void onSuccess(List<UserRelease> object) {
                if (object.size()!=0)
                {
                    String userid=bmobUser.getObjectId();
                    String names = object.get(0).getUr_name();
                    String msg = "{\"title\":\"求职信息\",\"description\":\""+names+"\",\"custom_content\":{\"qid\":\""+userid+"\"}}";
                    final String url = baseurl + "push/single_device";
                    final HashMap<String, String> map = new HashMap<>();
                    map.put("apikey", "YYwje7mhVDNwj1q3TZK0SgyC");
                    final long time = System.currentTimeMillis() / 1000L;
                    map.put("timestamp", time + "");
                    map.put("channel_id","4511091832997455114");
                    //      Log.i("cid", cid);
                    map.put("msg", msg);
                    map.put("msg_type", "1");
                    String kv = "";
                    Collection<String> keyset = map.keySet();
                    List<String> list = new ArrayList<String>(keyset);
                    //对key键值按字典升序排序
                    Collections.sort(list);
                    for (int i = 0; i < list.size(); i++) {
                        kv = kv + list.get(i) + "=" + map.get(list.get(i));
                    }
                    String res = "POST" + url + kv + "Ru88AHlCDwdxhfsIbblQVq2cZF3S6zC3";
                    Log.i("fengfeng", res);
                    String nomd5 = URLEncoder.encode(res);
                    //urlencode();
                    final String sign = CreateMD.getMd5(nomd5);
                    map.put("sign", sign);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String burl = url;
                                URL httpurl = new URL(burl);
                                HttpURLConnection httpURLConnection = (HttpURLConnection) httpurl.openConnection();
                                httpURLConnection.setRequestMethod("POST");
                                httpURLConnection.setConnectTimeout(5000);
                                httpURLConnection.setDoOutput(true);
                                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                                httpURLConnection.setRequestProperty("User-Agent", "BCCS_SDK/3.0 (Linux; #1 SMP Fri Nov 22 03:15:09 UTC 2013; x86_64) PHP/5.3.29 (Baidu Push SDK for PHP v3.0.0) apache2handler/Unknown(Apache) ZEND/2.3.0");
                                OutputStream outputStream = httpURLConnection.getOutputStream();
                                Iterator<HashMap.Entry<String, String>> iterator2 = map.entrySet().iterator();
                                String query = "";
                                while (iterator2.hasNext()) {
                                    HashMap.Entry<String, String> entry = iterator2.next();
                                    query = query + entry.getKey() + "=" + entry.getValue() + "&";
                                }
                                String endquery = query.substring(0, query.length() - 1);
                                Log.i("fengfeng", endquery);

                                outputStream.write(endquery.getBytes());
                                httpURLConnection.connect();
                                Log.i("fengfeng", httpURLConnection.getResponseCode() + "请求返回码");
                                if (httpURLConnection.getResponseCode() == 200) {
                                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                                    //char[] cbuf = new char[1024];
                                    String line = "";
                                    StringBuffer responseResult = new StringBuffer();
                                    while ((line = bufferedReader.readLine()) != null) {
                                        responseResult.append(line);
                                    }
                                    //String resdata = cbuf.toString();
                                    Log.i("fengfeng", responseResult.toString());
                                } else {
                                    Log.i("fengfeng", burl);
                                    Log.i("fengfeng", httpURLConnection.getResponseCode() + "请求返回码");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.i("fengfeng", "网络异常");
                            }
                        }
                    }).start();
                }
            }
            @Override
            public void onError(int code, String msg) {
            }
        });


    }

}
