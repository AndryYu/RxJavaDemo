package com.andryyu.rxjavademo.rxjava2.generate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.andryyu.rxjavademo.base.BaseOperationActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RxIntervalActivity extends BaseOperationActivity {

    private Observable<Long> observable;

    @Override
    protected void initView() {
        tvTop.setText("Interval\n\n" +
                "创建一个按固定时间间隔发射整数序列的Observable，默认在computation调度器上执行（要注意观察者的调度器，observeOn），也可以指定调度器。基本上就是加强版的TimerTask\n\n" +
                "observable = Observable.interval(1, 2, TimeUnit.SECONDS);// 延迟1s后开始，每隔2s发射一次");
        observable = Observable.interval(1, 2, TimeUnit.SECONDS);
    }

    @Override
    protected void initData() {
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                observable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
                    @Override
                    public void onComplete() {
                        sb.append("onComplete()");
                        sb.append("\n");
                        tvBottom.setText(sb);
                    }

                    @Override
                    public void onError(Throwable e) {
                        sb.append("onError() ");
                        sb.append(e.getMessage());
                        sb.append("\n");
                        tvBottom.setText(sb);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {


                    }

                    @Override
                    public void onNext(Long o) {
                        sb.append("onNext() ");
                        sb.append(o);
                        sb.append("\n");
                        tvBottom.setText(sb);
                    }
                });
            }
        });
    }
}
