package com.andryyu.rxjavademo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.andryyu.rxjavademo.R;
import com.andryyu.rxjavademo.rxjava2.GenerateActivity;
import com.andryyu.rxjavademo.ui.adapter.MyAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_Rxjava2)
    RecyclerView rvRxjava2;
    private Button btnBasic, btnThread, btnMap, btnZip, btnFlowable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

     /*   btnBasic = findViewById(R.id.btn_basic);
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
        });*/

        rvRxjava2.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(this) {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, GenerateActivity.class));
                        break;
                    case 1:
                        //startActivity(new Intent(MainActivity.this, TransformActivity.class));
                        break;
                    case 2:
                       // startActivity(new Intent(MainActivity.this, FilterActivity.class));
                        break;
                    case 3:
                       // startActivity(new Intent(MainActivity.this, CombineActivity.class));
                        break;
                    case 4:
                        //startActivity(new Intent(MainActivity.this, ErrorActivity.class));
                        break;
                    case 5:
                        //startActivity(new Intent(MainActivity.this, AssistActivity.class));
                        break;
                    case 6:
                        //startActivity(new Intent(MainActivity.this, ConvertActivity.class));
                        break;
                    case 7:
                        //startActivity(new Intent(MainActivity.this, ConditionActivity.class));
                        break;
                    case 8:
                        //startActivity(new Intent(MainActivity.this, ConnectActivity.class));
                        break;
                    case 9:
                        //startActivity(new Intent(MainActivity.this, BlockActivity.class));
                        break;
                }
            }
        };
        adapter.setData(new String[]{"创建操作", "变换操作", "过滤操作", "结合操作", "错误处理", "辅助操作", "转换操作", "条件和布尔操作", "连接操作(ConnectableObservable)", "阻塞操作", "字符串操作(StringObservable)", "算数和聚合操作(rxjava-math)", "异步操作(rxjava-async)"});
        rvRxjava2.setAdapter(adapter);
    }
}
