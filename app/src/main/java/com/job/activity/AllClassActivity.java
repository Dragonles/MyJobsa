package com.job.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.job.adapter.AllclassAdpter;
import com.job.bean.All_class_item;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有类的Activity
 *
 */

public class AllClassActivity extends AppCompatActivity {

    List<All_class_item> mall_class_list =new ArrayList<>();
    ListView mlistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_class);
        mlistview=(ListView)findViewById(R.id.all_class_listview);
        mall_class_list.add(new All_class_item("家政"));
        mall_class_list.add(new All_class_item("钟点工"));
        mall_class_list.add(new All_class_item("销售"));
        mall_class_list.add(new All_class_item("游戏代练"));
        mall_class_list.add(new All_class_item("促销"));
        mall_class_list.add(new All_class_item("派单"));
        mlistview.setAdapter(new AllclassAdpter(this,mall_class_list));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_all_class, menu);
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
}
