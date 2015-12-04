package com.job.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.job.bean.User;
import com.job.bean.UserRelease;

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
import me.nereo.imagechoose.bean.Image;

/**
 * 用户简历详情页
 * 首页点击listview 跳转到详情页
 * */
public class UserJianliDetailsActivity extends AppCompatActivity {

    int jl;
    ImageView choseimg;  //头像
    TextView setname, setsex, setbirth, setxueli, setyaoqiu, setjingyan, setsalary, setaddress;
    Button jianli_submit, updatejianli, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_jianli_details);
        //接收 HOmeFragement传递来的数据
        Intent intent = getIntent();
        jl = intent.getIntExtra("keypos", -1);
        choseimg = (CircleImageView) findViewById(R.id.choseimg);       //头像
        setname = (TextView) findViewById(R.id.setname);        //姓名
        setsex = (TextView) findViewById(R.id.setsex);      //性别
        setbirth = (TextView) findViewById(R.id.setbirth);      //出生日期
        setxueli = (TextView) findViewById(R.id.setxueli);      //学历
        setyaoqiu = (TextView) findViewById(R.id.setyaoqiu);        //要求
        setjingyan = (TextView) findViewById(R.id.setjingyan);      //经验
        setsalary = (TextView) findViewById(R.id.setsalary);        //薪资
        setaddress = (TextView) findViewById(R.id.setaddress);      //工作地址
        jianli_submit = (Button) findViewById(R.id.jianli_submit);  //点击发送消息按钮
        updatejianli = (Button) findViewById(R.id.updatejianli);    //已发送消息按钮
        back = (Button) findViewById(R.id.back);

        //返回按钮点击事件
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserJianliDetailsActivity.this.finish();
            }
        });
        Log.i("ujianli", "/*/*简历");

        Bmob.initialize(getApplicationContext(), "e98c629c488e891e6d090798dd2ced7f");
        if (jl >= 0){
            BmobQuery<UserRelease> bmobQuery = new BmobQuery<>();
            bmobQuery.findObjects(UserJianliDetailsActivity.this, new FindListener<UserRelease>() {
                @Override
                public void onSuccess(List<UserRelease> list) {
                    setname.setText(list.get(jl).getUr_name().toString());
                    setsex.setText(list.get(jl).getUr_sex().toString());
                    setbirth.setText(list.get(jl).getBirthday().toString());
                    setxueli.setText(list.get(jl).getUr_study().toString());
                    setyaoqiu.setText(list.get(jl).getUr_requirement().toString());
                    setjingyan.setText(list.get(jl).getUr_work_time().toString());
                    setsalary.setText(list.get(jl).getUr_money().toString());
                    setaddress.setText(list.get(jl).getUr_address().toString());
                    Log.i("ujianli","成功查看简历");

                }
                @Override
                public void onError(int i, String s) {

                }
            });
        }

        jianli_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser bmobUser = BmobUser.getCurrentUser(UserJianliDetailsActivity.this);
//                if(bmobUser != null){
//                    BmobUser bu = BmobUser.getCurrentUser(UserJianliDetailsActivity.this);
//                    BmobQuery<UserRelease> cpbq = new BmobQuery<UserRelease>();
//                    cpbq.addWhereEqualTo("user_id",uid);
//                    cpbq.findObjects(UserJianliDetailsActivity.this, new FindListener<UserRelease>() {
//                        @Override
//                        public void onSuccess(final List<UserRelease> lists) {
//                            if (lists.size() != 0) {
//                                if (lists.get(0).getIs_jianli().equals("1")) {
//                                    BmobQuery<User> query = new BmobQuery<User>();
//                                    query.getObject(UserJianliDetailsActivity.this, lists.get(0).getUser_id(), new GetListener<User>() {
//                                        @Override
//                                        public void onSuccess(User object) {
//                                            Log.i("ssssssssss",object.getChannel_id());
//                                            cid=object.getChannel_id();
//                                        }
//                                        @Override
//                                        public void onFailure(int code, String arg0) {
//                                        }
//                                    });
//                                    String name =lists.get(0).getUr_name();
//                                    String user_id=lists.get(0).getUser_id();
//                                    Log.i("fengfeng", "sendMessage");
//                                    //3935128846849032787   4066917556904834292  4511091832997455114
//                                    String msg = "{\"title\":\"求职信息\",\"description\":\""+name+"\",\"custom_content\":{\"qid\":\""+user_id+"\"}}";
//                                    final String url = baseurl + "push/single_device";
//                                    final HashMap<String, String> map = new HashMap<>();
//                                    map.put("apikey", "YYwje7mhVDNwj1q3TZK0SgyC");
//                                    final long time = System.currentTimeMillis() / 1000L;
//                                    map.put("timestamp", time + "");
//                                    map.put("channel_id",cid);
//                                    map.put("msg", msg);
//                                    map.put("msg_type", "1");
//                                    String kv = "";
//                                    Collection<String> keyset = map.keySet();
//                                    List<String> list = new ArrayList<String>(keyset);
//                                    //对key键值按字典升序排序
//                                    Collections.sort(list);
//                                    for (int i = 0; i < list.size(); i++) {
//                                        kv = kv + list.get(i) + "=" + map.get(list.get(i));
//                                    }
//                                    String res = "POST" + url + kv + "Ru88AHlCDwdxhfsIbblQVq2cZF3S6zC3";
//                                    Log.i("fengfeng", res);
//                                    String nomd5 = URLEncoder.encode(res);
//                                    //urlencode();
//                                    final String sign = CreateMD.getMd5(nomd5);
//                                    map.put("sign", sign);
//                                    new Thread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            try {
//                                                String burl = url;
//                                                URL httpurl = new URL(burl);
//                                                HttpURLConnection httpURLConnection = (HttpURLConnection) httpurl.openConnection();
//                                                httpURLConnection.setRequestMethod("POST");
//                                                httpURLConnection.setConnectTimeout(5000);
//                                                httpURLConnection.setDoOutput(true);
//                                                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//                                                httpURLConnection.setRequestProperty("User-Agent", "BCCS_SDK/3.0 (Linux; #1 SMP Fri Nov 22 03:15:09 UTC 2013; x86_64) PHP/5.3.29 (Baidu Push SDK for PHP v3.0.0) apache2handler/Unknown(Apache) ZEND/2.3.0");
//                                                OutputStream outputStream = httpURLConnection.getOutputStream();
//                                                Iterator<HashMap.Entry<String, String>> iterator2 = map.entrySet().iterator();
//                                                String query = "";
//                                                while (iterator2.hasNext()) {
//                                                    HashMap.Entry<String, String> entry = iterator2.next();
//                                                    query = query + entry.getKey() + "=" + entry.getValue() + "&";
//                                                }
//                                                String endquery = query.substring(0, query.length() - 1);
//                                                Log.i("fengfeng", endquery);
//
//                                                outputStream.write(endquery.getBytes());
//                                                httpURLConnection.connect();
//                                                Log.i("fengfeng", httpURLConnection.getResponseCode() + "请求返回码");
//                                                if (httpURLConnection.getResponseCode() == 200) {
//                                                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
//                                                    //char[] cbuf = new char[1024];
//                                                    String line = "";
//                                                    StringBuffer responseResult = new StringBuffer();
//                                                    while ((line = bufferedReader.readLine()) != null) {
//                                                        responseResult.append(line);
//                                                    }
//                                                    //String resdata = cbuf.toString();
//                                                    Log.i("fengfeng", responseResult.toString());
//                                                } else {
//                                                    Log.i("fengfeng", burl);
//                                                    Log.i("fengfeng", httpURLConnection.getResponseCode() + "请求返回码");
//
//                                                }
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                                Log.i("fengfeng", "网络异常");
//                                            }
//                                        }
//                                    }).start();
//                                } else {
////                                    btn_yingpin.setEnabled(false);
////                                    Toast.makeText(UserJianliDetailsActivity.this, "请先创建简历", Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        }
//                        @Override
//                        public void onError(int i, String s) {
//                            Log.i("ss", i + s);
//                        }
//                    });
//                }
            }
        });
    }

}
