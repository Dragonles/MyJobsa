package com.job.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.job.adapter.JobShouruAdapter;

import java.util.ArrayList;
import java.util.List;

public class JobShouruActivity extends AppCompatActivity {

    ListView listView;
    JobShouruAdapter adapter;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_shouru);

        listView = (ListView) findViewById(R.id.lv_shouru);
        adapter = new JobShouruAdapter(JobShouruActivity.this,list);
        listView.setAdapter(adapter);

    }

}
