package com.andryyu.rxjavademo.rxjava2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andryyu.rxjavademo.base.BaseCategoryActivity;
import com.andryyu.rxjavademo.modal.OperatorModel;
import com.andryyu.rxjavademo.rxjava2.generate.RxCreateActivity;
import com.andryyu.rxjavademo.rxjava2.transform.RxBufferActivity;
import com.andryyu.rxjavademo.rxjava2.transform.RxFlatMapActivity;
import com.andryyu.rxjavademo.rxjava2.transform.RxGroupByActivity;
import com.andryyu.rxjavademo.rxjava2.transform.RxMapActivity;
import com.andryyu.rxjavademo.rxjava2.transform.RxScanActivity;
import com.andryyu.rxjavademo.rxjava2.transform.RxWindowActivity;

import java.util.ArrayList;

public class TransformActivity extends BaseCategoryActivity {


    @Override
    protected void fillData() {
        data = new ArrayList<>();
        data.add(new OperatorModel("Buffer","定期从Observable收集数据到一个集合，然后把这些数据集合打包发射，而不是一次发射一个"));
        data.add(new OperatorModel("FlatMap","将Observable发射的数据变换为Observables集合，然后将这些Observable发射的数据平坦化的放进一个单独的Observable,可以认为是一个嵌套的数据结构展开的过程"));
        data.add(new OperatorModel("GroupBy","将原来的Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，每一个Observable发射一组不同的数据"));
        data.add(new OperatorModel("Map","通过对序列的每一项都应用一个函数变换Observable发射的数据，实质是对序列中的每一项执行一个函数，函数的参数就是这个数据项"));
        data.add(new OperatorModel("Scan","对Observable发射的每一项数据应用一个函数，然后按顺序依次发射这些值"));
        data.add(new OperatorModel("Window","定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项。类似于Buffer，但Buffer发射的是数据，Window发射的是Observable，每一个Observable发射原始Observable的数据的一个子集"));
    }

    @Override
    protected void itemClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(TransformActivity.this, RxBufferActivity.class));
                break;
            case 1:
                startActivity(new Intent(TransformActivity.this, RxFlatMapActivity.class));
                break;
            case 2:
                startActivity(new Intent(TransformActivity.this, RxGroupByActivity.class));
                break;
            case 3:
                startActivity(new Intent(TransformActivity.this, RxMapActivity.class));
                break;
            case 4:
                startActivity(new Intent(TransformActivity.this, RxScanActivity.class));
                break;
            case 5:
                startActivity(new Intent(TransformActivity.this, RxWindowActivity.class));
                break;
        }
    }
}
