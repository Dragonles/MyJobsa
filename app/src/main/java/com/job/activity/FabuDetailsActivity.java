package com.job.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

/**
 * 发布详情页
 * 2015-11-19
 * yanhao
 * */

public class FabuDetailsActivity extends AppCompatActivity {

    /**
     * onCreate方法
     * */
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabu_details);
        back = (ImageView) findViewById(R.id.fabuactivity_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             FabuDetailsActivity.this.finish();
            }
        });
    }

}
