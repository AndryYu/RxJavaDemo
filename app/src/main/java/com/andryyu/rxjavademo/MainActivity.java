package com.andryyu.rxjavademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnBasic, btnThread, btnMap, btnZip, btnFlowable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBasic = findViewById(R.id.btn_basic);
        btnThread = findViewById(R.id.btn_thread);
        btnMap = findViewById(R.id.btn_map);
        btnZip = findViewById(R.id.btn_zip);
        btnFlowable = findViewById(R.id.btn_flowable);
        btnBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BasicActivity.class));
            }
        });
        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ThreadSwitchActivity.class));
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });
        btnZip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ZipActivity.class));
            }
        });
        btnFlowable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FlowableActivity.class));
            }
        });
    }
}
