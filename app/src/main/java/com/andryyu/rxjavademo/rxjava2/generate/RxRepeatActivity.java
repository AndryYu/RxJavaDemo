package com.andryyu.rxjavademo.rxjava2.generate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.andryyu.rxjavademo.base.BaseOperationActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class RxRepeatActivity extends BaseOperationActivity {

    private Observable<Integer> observable;
    private Observable<Integer> whenObservable;

    @Override
    protected void initView() {
        tvTop.setText("Repeat\n\n" +
                "创建一个发射特定数据重复多次的Observable，默认在trampoline调度器上执行。repeatWhen，完成的时候触发是否重试\n\n" +
                "observable = Observable.just(0, 1, 2).repeat(3);\n" +
                "\n" +
                "whenObservable = Observable.just(0, 1, 2).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {\n" +
                "    @Override\n" +
                "    public Observable<?> call(Observable<? extends Void> observable) {\n" +
                "        observable = observable.delay(2, TimeUnit.SECONDS); //2s后发射\n" +
                "        return observable;\n" +
                "    }\n" +
                "});");

        btnSubscribe.setText("repeat");
        btnSubscribe2.setText("repeatWhen");
        btnSubscribe2.setVisibility(View.VISIBLE);

        observable = Observable.just(0, 1, 2).repeat(3);

        whenObservable = Observable.just(0, 1, 2).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                observable = observable.delay(2, TimeUnit.SECONDS); //2s后发射
                return observable;
            }
        });
    }

    @Override
    protected void initData() {
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                observable.subscribe(new Observer<Integer>() {
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

        btnSubscribe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                whenObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
                    @Override
                    public void onComplete() {
                        sb.append("onComplete()");
                        sb.append("\n");
                        tvBottom.setText(sb);
                    }

                    @Override
                    public void onError(Throwable e) {
                        sb.append("onError()");
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
