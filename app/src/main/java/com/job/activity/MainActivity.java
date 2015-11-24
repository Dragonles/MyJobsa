package com.job.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import fragment.FabuFragment;
import fragment.HomeFragment;
import fragment.NearFragment;
import fragment.PerFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    //设置是否返回地址信息（默认返回地址信息）
    public String city;

    FragmentManager mfm;
    FragmentTransaction ftt;
    RadioButton mhome,mfabu,mnearll,mper;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mhome = (RadioButton)findViewById(R.id.home);//主页的单选按钮
        mfabu = (RadioButton) findViewById(R.id.fabu);//发布的单选按钮
        mnearll = (RadioButton) findViewById(R.id.nearll);//附近的单选按钮
        mper=(RadioButton)findViewById(R.id.per);//个人中心的单选按钮


        //home第一个默认
        mhome.setChecked(true);

        //为每个按钮添加点击事件
        mfabu.setOnClickListener(this);
        mnearll.setOnClickListener(this);
        mhome.setOnClickListener(this);
        mper.setOnClickListener(this);

        mfm = getSupportFragmentManager();
        if (savedInstanceState == null){
            FragmentTransaction ftt = mfm.beginTransaction();
            HomeFragment pf = new HomeFragment();
            ftt.add(R.id.fragment_parent, pf, "home");
            ftt.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ftt = mfm.beginTransaction();
        if (mfm.findFragmentByTag("home")!= null){
            ftt.hide(mfm.findFragmentByTag("home"));
        }
        if (mfm.findFragmentByTag("fabu")!= null){
            ftt.hide(mfm.findFragmentByTag("fabu"));
        }
        if (mfm.findFragmentByTag("nearll")!= null){
            ftt.hide(mfm.findFragmentByTag("nearll"));
        }
        if (mfm.findFragmentByTag("per")!= null){
            ftt.hide(mfm.findFragmentByTag("per"));
        }
        int id = v.getId();
        if (id == R.id.home){
            if (mfm.findFragmentByTag("home")!=null){
                ftt.show(mfm.findFragmentByTag("home"));
            }else{
                HomeFragment hf = new HomeFragment();
                //add(父布局ID，Fragment，Tag);
                ftt.add(R.id.fragment_parent, hf, "home");
            }

        }else if (id == R.id.fabu){
            if (mfm.findFragmentByTag("fabu")!=null){
                ftt.show(mfm.findFragmentByTag("fabu"));
            }else{
                FabuFragment ff = new FabuFragment();
                //add(父布局ID，Fragment，Tag);
                ftt.add(R.id.fragment_parent,ff,"fabu");
            }
        }else if (id == R.id.nearll){   //我的
            if (mfm.findFragmentByTag("nearll")!=null){
                ftt.show(mfm.findFragmentByTag("nearll"));
            }else{
                NearFragment nf = new NearFragment();
                //add(父布局ID，Fragment，Tag);
                ftt.add(R.id.fragment_parent,nf,"nearll");
            }
        }else if (id == R.id.per){   //我的
            if (mfm.findFragmentByTag("per")!=null){
                ftt.show(mfm.findFragmentByTag("per"));
            }else{
                PerFragment pf = new PerFragment();
                //add(父布局ID，Fragment，Tag);
                ftt.add(R.id.fragment_parent,pf,"per");
            }
        }
        ftt.commit();
    }

}
