package com.job.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * LeadActivity  引导页
 * 2015-11-18
 * Qing
 *
 * */

public class LeadActivity extends Activity {

    RadioButton radiobtn_lead_pager1, radiobtn_lead_pager2, radiobtn_lead_pager3;
    ViewPager view_lead_viewpager;
    List<View> list_lead_view = new ArrayList<>();
    Button mstart;
    TextView tt;
    List<RadioButton> list_lead_radiobtn = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        view_lead_viewpager = (ViewPager) findViewById(R.id.view);
        View v1 = LayoutInflater.from(this).inflate(R.layout.viewpager1_layout,null);
        View v2 = LayoutInflater.from(this).inflate(R.layout.viewpager2_layout,null);
        View v3 = LayoutInflater.from(this).inflate(R.layout.viewpager3_layout,null);
        mstart=(Button)findViewById(R.id.btn_lead_viewpager_start);
        list_lead_view.add(v1);
        list_lead_view.add(v2);
        list_lead_view.add(v3);
        radiobtn_lead_pager1 = (RadioButton) findViewById(R.id.pager1);
        radiobtn_lead_pager2 = (RadioButton) findViewById(R.id.pager2);
        radiobtn_lead_pager3 = (RadioButton) findViewById(R.id.pager3);
        list_lead_radiobtn.add(radiobtn_lead_pager1);
        list_lead_radiobtn.add(radiobtn_lead_pager2);
        list_lead_radiobtn.add(radiobtn_lead_pager3);
        tt=(TextView)findViewById(R.id.tt1);
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(tt, "connection error", Snackbar.LENGTH_LONG).setAction("retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tt.setText("aleady click snackbar");
                    }
                }).show();
            }
        });
        BmobUser bmobUser = BmobUser.getCurrentUser(LeadActivity.this);
        if(bmobUser != null){
            Intent intent = new Intent(LeadActivity.this, MainActivity.class);
            startActivity(intent);
            LeadActivity.this.finish();
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
        }
        view_lead_viewpager.setAdapter(new MyViewPagerAdapter());
        view_lead_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                list_lead_radiobtn.get(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        ((Button)v3.findViewById(R.id.btn_lead_viewpager_start)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                Intent intent = new Intent(LeadActivity.this, LoginActivity.class);
                startActivity(intent);
                LeadActivity.this.finish();
            }
        });
        findViewById(R.id.btn_lead_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeadActivity.this, LoginActivity.class);
                startActivity(intent);
                LeadActivity.this.finish();
            }
        });
    }
    class MyViewPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return list_lead_view.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // super.destroyItem(container, position, object);
            container.removeView(list_lead_view.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = list_lead_view.get(position);
            container.addView(v,0);
            return v;
        }
    }
}

