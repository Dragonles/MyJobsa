package com.job.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.job.bean.Classify;
import com.job.bean.CompanyRelease;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class LocationActivity extends AppCompatActivity {

    ListView listview;
    EditText et_sousuo;
    List<CompanyRelease> companyReleaseList;
    int classifyid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        et_sousuo = (EditText) findViewById(R.id.editText);
        Editable s = et_sousuo.getText();

        final Classify classify = new Classify();

        BmobQuery<Classify> cla = new BmobQuery<Classify>();
        cla.addWhereEqualTo("classify_name", s);
        cla.findObjects(this, new FindListener<Classify>() {
            @Override
            public void onSuccess(final List<Classify> list) {
                classifyid = classify.getClassify_id();

                BmobQuery<CompanyRelease> query = new BmobQuery<>();
                query.addWhereEqualTo("cr_position", classifyid);

                query.findObjects(LocationActivity.this, new FindListener<CompanyRelease>() {
                    @Override
                    public void onSuccess(List<CompanyRelease> object) {

//                        companyReleaseList = object;

                    }

                    @Override
                    public void onError(int code, String msg) {
                        Log.i("cl","失败");
                    }
                });

            }

            @Override
            public void onError(int i, String s) {
                Log.i("cl","失败");
            }
        });





    }
}
