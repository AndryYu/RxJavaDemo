package com.andryyu.rxjavademo.rxjava2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andryyu.rxjavademo.base.BaseCategoryActivity;
import com.andryyu.rxjavademo.modal.OperatorModel;
import com.andryyu.rxjavademo.rxjava2.error.RxCatchActivity;
import com.andryyu.rxjavademo.rxjava2.error.RxRetryActivity;

import java.util.ArrayList;

public class ErrorActivity extends BaseCategoryActivity {


    @Override
    protected void fillData() {
        data = new ArrayList<>();
        data.add(new OperatorModel("Catch","捕获，继续序列操作，将错误替换为正常的数据，从onError通知中恢复"));
        data.add(new OperatorModel("Retry","重试，如果Observable发射了一个错误通知，重新订阅它，期待它正常终止"));
    }

    @Override
    protected void itemClick(int position) {
        switch (position){
            case 0:
                startActivity(new Intent(ErrorActivity.this, RxCatchActivity.class));
                break;
            case 1:
                startActivity(new Intent(ErrorActivity.this, RxRetryActivity.class));
                break;
        }
    }
}
