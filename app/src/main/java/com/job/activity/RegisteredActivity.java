package com.job.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class RegisteredActivity extends Activity {

    EditText et_phonenumber,et_verification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        et_phonenumber = (EditText)findViewById(R.id.et_phonenumber);
        et_verification = (EditText)findViewById(R.id.et_phoneverification);

    }

}
