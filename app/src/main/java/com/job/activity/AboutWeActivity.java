package com.job.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import fragment.PerFragment;

public class AboutWeActivity extends AppCompatActivity {

    public Button btn_gengxin,btn_gongneng,btn_help,btn_tuichu;
    public ImageButton ib_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_we);

        btn_gengxin = (Button) findViewById(R.id.btn_gengxin);
        btn_gongneng = (Button) findViewById(R.id.btn_gongneng);
        btn_help = (Button) findViewById(R.id.btn_help);
        btn_tuichu = (Button) findViewById(R.id.btn_tuichu);
        ib_back = (ImageButton) findViewById(R.id.ib_back);

        btn_gengxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AboutWeActivity.this, "已是最新版本", Toast.LENGTH_SHORT).show();
            }
        });

        btn_gongneng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AboutWeActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
            }
        });

        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AboutWeActivity.this, "请在使用中自行摸索", Toast.LENGTH_SHORT).show();
            }
        });

        btn_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AboutWeActivity.this.finish();
            }
        });

        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AboutWeActivity.this.finish();
            }
        });


    }

}
