package com.job.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.job.adapter.AllclassAdpter;
import com.job.bean.Classify;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * 所有类的Activity
 *
 */

public class AllClassActivity extends AppCompatActivity {

    List<Classify> mall_class_list =new ArrayList<>();
    ListView mlistview;
    LinearLayout ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_class);
        mlistview=(ListView)findViewById(R.id.all_class_listview);
        ls=(LinearLayout)findViewById(R.id.backs);
        ls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllClassActivity.this.finish();
            }
        });
        Bmob.initialize(this, "e98c629c488e891e6d090798dd2ced7f");
        BmobQuery<Classify> ci = new BmobQuery<>();
        ci.findObjects(this, new FindListener<Classify>() {
            @Override
            public void onSuccess(List<Classify> list) {
                mall_class_list = list;
                mlistview.setAdapter(new AllclassAdpter(AllClassActivity.this, mall_class_list));

            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(getApplicationContext(), "读取列表失败", Toast.LENGTH_LONG).show();
            }
        });
        //mlistview 点击事件
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AllClassActivity.this, LocationActivity.class);
                intent.putExtra("poskey", position);
                startActivity(intent);
            }
        });



//        mall_class_list.add(new All_class_item("家政"));
//        mall_class_list.add(new All_class_item("钟点工"));
//        mall_class_list.add(new All_class_item("销售"));
//        mall_class_list.add(new All_class_item("游戏代练"));
//        mall_class_list.add(new All_class_item("促销"));
//        mall_class_list.add(new All_class_item("派单"));

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
