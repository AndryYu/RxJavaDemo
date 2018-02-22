package com.andryyu.rxjavademo.rxjava2.transform;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andryyu.rxjavademo.base.BaseOperationActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxWindowActivity extends BaseOperationActivity {

    private Observable<Observable<Integer>> observable;

    @Override
    protected void initView() {
        tvTop.setText("window非常类似buffer操作符，区别在于buffer操作符产生的结果是一个List，而window操作符产生的结果是一个Observable，订阅者可以对这个结果Observable重新进行订阅处理。window有多个重载方法，比如window(count, skip)、window(long,TimeUnit)，例子不一一列举。\n" +
                "\n\n" +
                "Observable<Observable<Integer>> observable = Observable.just(0, 1, 2).buffer(2); //订阅者onNext()收到的是一个Observable<Integer>，再订阅");

        observable = Observable.just(0, 1, 2).window(2);
    }

    @Override
    protected void initData() {
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                observable.subscribe(new Observer<Observable<Integer>>() {
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
                    public void onNext(Observable<Integer> o) {
                        sb.append("onNext() ");
                        sb.append(o.toString());
                        sb.append(" ");
                        sb.append("\n");
                        tvBottom.setText(sb);
                        o.subscribe(new Observer<Integer>() {
                            @Override
                            public void onComplete() {
                                sb.append("---sub onComplete()");
                                sb.append("\n");
                                tvBottom.setText(sb);
                            }

                            @Override
                            public void onError(Throwable e) {
                                sb.append("---sub onError() ");
                                sb.append(e.getMessage());
                                sb.append("\n");
                                tvBottom.setText(sb);
                            }

                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer integer) {
                                sb.append("---sub onNext() ");
                                sb.append(integer);
                                sb.append(" ");
                                sb.append("\n");
                                tvBottom.setText(sb);
                            }
                        });
                    }
                });
            }
        });
    }
}
