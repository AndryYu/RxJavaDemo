package com.andryyu.rxjavademo.generate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andryyu.rxjavademo.BaseActivity;
import com.andryyu.rxjavademo.R;

public class GenerateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
