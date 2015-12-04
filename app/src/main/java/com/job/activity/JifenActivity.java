package com.job.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.job.bean.Qiandao;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import fragment.HomeFragment;

public class JifenActivity extends AppCompatActivity {

    LinearLayout back;
    TextView numbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifen);
        back=(LinearLayout)findViewById(R.id.back);
        Intent intent = getIntent();
        String qid = intent.getStringExtra("qid");
        TextView tv = (TextView) findViewById(R.id.showqid);
        tv.setText(qid);
        numbers=(TextView)findViewById(R.id.numbers);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JifenActivity.this.finish();
            }
        });
        //numbers.setText();
        final BmobUser bmobUser = BmobUser.getCurrentUser(JifenActivity.this);
        if(bmobUser != null){
            BmobQuery<Qiandao> cpbq = new BmobQuery<Qiandao>();
            cpbq.addWhereEqualTo("user_id",bmobUser.getObjectId());
            cpbq.findObjects(JifenActivity.this, new FindListener<Qiandao>() {
                @Override
                public void onSuccess(List<Qiandao> list) {
                    if (list.size() != 0) {
                        BmobQuery<Qiandao> query = new BmobQuery<Qiandao>();
                        query.getObject(JifenActivity.this,list.get(0).getObjectId(), new GetListener<Qiandao>() {
                            @Override
                            public void onSuccess(Qiandao object) {
                                numbers.setText(object.getJifen());
                            }

                            @Override
                            public void onFailure(int code, String arg0) {
                            }

                        });
                    } else {

                    }
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jifen, menu);
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
