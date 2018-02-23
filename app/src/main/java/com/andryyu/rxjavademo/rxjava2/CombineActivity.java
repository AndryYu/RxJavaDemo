package com.andryyu.rxjavademo.rxjava2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andryyu.rxjavademo.base.BaseCategoryActivity;
import com.andryyu.rxjavademo.modal.OperatorModel;
import com.andryyu.rxjavademo.rxjava2.combine.RxAndActivity;
import com.andryyu.rxjavademo.rxjava2.combine.RxCombineLatestActivity;
import com.andryyu.rxjavademo.rxjava2.combine.RxJoinActivity;
import com.andryyu.rxjavademo.rxjava2.combine.RxMergeActivity;
import com.andryyu.rxjavademo.rxjava2.combine.RxStartWithActivity;
import com.andryyu.rxjavademo.rxjava2.combine.RxSwitchActivity;
import com.andryyu.rxjavademo.rxjava2.combine.RxZipActivity;

import java.util.ArrayList;

public class CombineActivity extends BaseCategoryActivity {


    @Override
    protected void fillData() {
        data = new ArrayList<>();
        data.add(new OperatorModel("And/Then/When","通过模式(And条件)和计划(Then次序)组合两个或多个Observable发射的数据集"));
        data.add(new OperatorModel("CombineLatest","当两个Observables中的任何一个发射了一个数据时，通过一个指定的函数组合每个Observable发射的最新数据(一共两个数据)，然后发射这个函数的结果"));
        data.add(new OperatorModel("Join","无论何时，如果一个Observable发射了一个数据项，只要在另一个Observable发射的数据项定义的时间窗口内，就将两个Observable发射的数据合并发射"));
        data.add(new OperatorModel("Merge","将两个Observable发射的数据组合并成一个"));
        data.add(new OperatorModel("StartWith","在发射原来的Observable的数据序列之前，先发射一个指定的数据序列或数据项"));
        data.add(new OperatorModel("Switch","将一个发射Observable序列的Observable转换为这样一个Observable：它逐个发射那些Observable最近发射的数据"));
        data.add(new OperatorModel("Zip","打包，使用一个指定的函数将多个Observable发射的数据组合在一起，然后将这个函数的结果作为单项数据发射"));
    }

    @Override
    protected void itemClick(int position) {
        switch (position){
            case 0:
                startActivity(new Intent(CombineActivity.this, RxAndActivity.class));
                break;
            case 1:
                startActivity(new Intent(CombineActivity.this, RxCombineLatestActivity.class));
                break;
            case 2:
                startActivity(new Intent(CombineActivity.this, RxJoinActivity.class));
                break;
            case 3:
                startActivity(new Intent(CombineActivity.this, RxMergeActivity.class));
                break;
            case 4:
                startActivity(new Intent(CombineActivity.this, RxStartWithActivity.class));
                break;
            case 5:
                startActivity(new Intent(CombineActivity.this, RxSwitchActivity.class));
                break;
            case 6:
                startActivity(new Intent(CombineActivity.this, RxZipActivity.class));
                break;
        }
    }
}
