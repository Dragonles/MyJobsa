package com.job.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.job.adapter.AllclassAdpter;
import com.job.bean.All_class_item;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_class);
        mlistview = (ListView)findViewById(R.id.all_class_listview);
        Bmob.initialize(AllClassActivity.this, "e98c629c488e891e6d090798dd2ced7f");
        BmobQuery<Classify> query = new BmobQuery<Classify>();
        final ProgressDialog pd = ProgressDialog.show(AllClassActivity.this, "正在加载", "");
        query.findObjects(this, new FindListener<Classify>() {
            @Override
            public void onSuccess(List<Classify> object) {
                // TODO Auto-generated method stub
                pd.dismiss();
                // toast("查询成功：共" + object.size() + "条数据。");
                for (Classify gameScore : object) {
                    mall_class_list.add(new Classify(gameScore.getClassify_name()));
                }
                mlistview.setAdapter(new AllclassAdpter(AllClassActivity.this,mall_class_list));

            }

            @Override
            public void onError(int code, String msg) {
                // TODO Auto-generated method stub
                //toast("查询失败：" + msg);
            }
        });

        Log.i("im","allaclass");

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
