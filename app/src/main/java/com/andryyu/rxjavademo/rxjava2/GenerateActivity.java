package com.andryyu.rxjavademo.rxjava2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.andryyu.rxjavademo.R;
import com.andryyu.rxjavademo.base.BaseActivity;
import com.andryyu.rxjavademo.base.BaseCategoryActivity;
import com.andryyu.rxjavademo.modal.OperatorModel;
import com.andryyu.rxjavademo.rxjava2.generate.RxCreateActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenerateActivity extends BaseCategoryActivity {


    @Override
    protected void fillData() {
        data = new ArrayList<>();
        data.add(new OperatorModel("Create","通过调用观察者的方法从头创建一个Observable"));
        data.add(new OperatorModel("Defer","在观察者订阅之前不创建这个Observable，为每一个观察者创建一个新的Observable"));
        data.add(new OperatorModel("Empty/Never/Throw","创建行为受限的特殊Observable"));
        data.add(new OperatorModel("From","将其它的对象或数据结构转换为Observable"));
        data.add(new OperatorModel("Interval","创建一个定时发射整数序列的Observable"));
        data.add(new OperatorModel("Just","将对象或者对象集合转换为一个会发射这些对象的Observable"));
        data.add(new OperatorModel("Range","创建发射指定范围的整数序列的Observable"));
        data.add(new OperatorModel("Repeat","创建重复发射特定的数据或数据序列的Observable"));
        data.add(new OperatorModel("Start","创建发射一个函数的返回值的Observable"));
        data.add(new OperatorModel("Timer","创建在一个指定的延迟之后发射单个数据的Observable"));
    }

    @Override
    protected void itemClick(int position) {
        switch (position){
            case 0:
                startActivity(new Intent(GenerateActivity.this, RxCreateActivity.class));
                break;
        }
    }
}
