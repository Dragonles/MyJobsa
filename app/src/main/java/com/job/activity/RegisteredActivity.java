package com.job.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisteredActivity extends Activity {


    Button mjump,mback_login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        mjump=(Button)findViewById(R.id.regis_jump_button);
        mback_login_button=(Button)findViewById(R.id.back_login_button);
        mjump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RegisteredActivity.this,MainActivity.class);
                startActivity(intent);
                RegisteredActivity.this.finish();
            }
        });
        mback_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisteredActivity.this.finish();
            }
        });
    }

}
