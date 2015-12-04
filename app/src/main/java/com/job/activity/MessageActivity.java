package com.job.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.job.adapter.YitoujianliAdpter;
import com.job.bean.CompanyRelease;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

public class MessageActivity extends AppCompatActivity {

    ListView listView;
    YitoujianliAdpter yitoujianliAdpter;
    TextView title,adress,names;
    List<CompanyRelease> cr_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yitou_item);
        title=(TextView)findViewById(R.id.hot_item_title);
        adress=(TextView)findViewById(R.id.hot_item_address);
        names=(TextView)findViewById(R.id.hot_item_money);
        title.setText(FabuJobDetailsActivity.leixing);
        adress.setText(FabuJobDetailsActivity.adress);
        names.setText(FabuJobDetailsActivity.names);
//        listView=(ListView)findViewById(R.id.listView);
//
//        final BmobUser bu = BmobUser.getCurrentUser(this);
//        BmobQuery<CompanyRelease> bqcr1 = new BmobQuery<>();
//        bqcr1.addWhereEqualTo("cr_urgency", "true");
//        BmobQuery<CompanyRelease> bqcr2 = new BmobQuery<>();
//        bqcr2.addWhereEqualTo("cr_apply", bu.getObjectId());
//        List<BmobQuery<CompanyRelease>> list_bqcr = new ArrayList<>();
//        list_bqcr.add(bqcr1);
//        list_bqcr.add(bqcr2);
//        BmobQuery<CompanyRelease> bqcr = new BmobQuery<>();
//        bqcr.and(list_bqcr);
//        bqcr.findObjects(getApplicationContext(), new FindListener<CompanyRelease>() {
//            @Override
//            public void onSuccess(List<CompanyRelease> list) {
//                cr_list = list;
//                yitoujianliAdpter = new YitoujianliAdpter(getApplicationContext(), cr_list);
//                listView.setAdapter(yitoujianliAdpter);
//            }
//
//            @Override
//            public void onError(int i, String s) {
//
//            }
//        });
    }

}
