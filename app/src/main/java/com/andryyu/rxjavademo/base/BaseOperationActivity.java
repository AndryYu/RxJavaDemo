package com.andryyu.rxjavademo.base;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.andryyu.rxjavademo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public abstract class BaseOperationActivity extends BaseActivity {

    @BindView(R.id.tv_top)
    protected TextView tvTop;
    @BindView(R.id.tv_bottom)
    protected TextView tvBottom;
    @BindView(R.id.btn_subscribe)
    protected Button btnSubscribe;
    @BindView(R.id.btn_subscribe2)
    protected Button btnSubscribe2;
    @BindView(R.id.btn_subscribe3)
    protected Button btnSubscribe3;

    protected StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        initData();
    }

    /**
     * <p>initView</p>
     *
     * @description 初始化界面控件
     */
    protected abstract void initView();

    /**
     * <p>initData</p>
     *
     * @description 初始化数据
     */
    protected abstract void initData();


    protected void clickSubscribe(){

    }
}
