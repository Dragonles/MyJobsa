package com.job.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fragment.PerFragment;

public class AboutWeActivity extends AppCompatActivity {

    public Button btn_gengxin,btn_gongneng,btn_help,btn_tuichu,ib_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_we);

        btn_gengxin = (Button) findViewById(R.id.btn_gengxin);
        btn_gongneng = (Button) findViewById(R.id.btn_gongneng);
        btn_help = (Button) findViewById(R.id.btn_help);
        btn_tuichu = (Button) findViewById(R.id.btn_tuichu);
        ib_back = (Button) findViewById(R.id.ib_back);

        btn_gengxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AboutWeActivity.this, "已是最新版本", Toast.LENGTH_SHORT).show();
            }
        });

        btn_gongneng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AboutWeActivity.this, PerFragment.class);
                startActivity(intent);
            }
        });


    }

}
