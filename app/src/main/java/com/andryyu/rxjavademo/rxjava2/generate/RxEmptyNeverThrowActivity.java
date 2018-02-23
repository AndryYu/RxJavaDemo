package com.andryyu.rxjavademo.rxjava2.generate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.andryyu.rxjavademo.base.BaseOperationActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RxEmptyNeverThrowActivity extends BaseOperationActivity {

    private Observable<Integer> emptyObservable;
    private Observable<Integer> neverObservable;
    private Observable<Integer> throwObservable;

    @Override
    protected void initView() {
        tvTop.setText("Empty\n" +
                "\n" +
                "创建一个不发射任何数据但是正常终止的Observable\n" +
                "\n" +
                "Never\n" +
                "\n" +
                "创建一个不发射数据也不终止的Observable\n" +
                "\n" +
                "Throw\n" +
                "\n" +
                "创建一个不发射数据以一个错误终止的Observable\n" +
                "\n" +
                "这三个操作符生成的Observable行为非常特殊和受限。测试的时候很有用，有时候也用于结合其它的Observables，或者作为其它需要Observable的操作符的参数。\n\n" +
                "emptyObservable = Observable.empty();\n" +
                "\n" +
                "neverObservable = Observable.never();\n" +
                "\n" +
                "throwObservable = Observable.error(new Exception(\"RxJavaAPI test error\"));");
        btnSubscribe.setText("never");
        btnSubscribe2.setText("empty");
        btnSubscribe2.setVisibility(View.VISIBLE);
        btnSubscribe3.setText("throw");
        btnSubscribe3.setVisibility(View.VISIBLE);

        emptyObservable = Observable.empty();
        neverObservable = Observable.never();
        throwObservable = Observable.error(new Exception("RxJavaAPI test error"));
    }

    @Override
    protected void initData() {
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                neverObservable.subscribe(new Observer<Integer>() {
                    @Override
                    public void onComplete() {
                        sb.append("onCompleted()");
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
                    public void onNext(Integer o) {
                        sb.append("onNext() ");
                        sb.append(o);
                        sb.append("\n");
                        tvBottom.setText(sb);
                    }
                });
            }
        });

        btnSubscribe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                emptyObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
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
                    public void onNext(Integer o) {
                        sb.append("onNext() ");
                        sb.append(o);
                        sb.append("\n");
                        tvBottom.setText(sb);
                    }
                });
            }
        });

        btnSubscribe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                throwObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
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
                    public void onNext(Integer o) {
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
