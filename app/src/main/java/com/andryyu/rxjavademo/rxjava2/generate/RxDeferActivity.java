package com.andryyu.rxjavademo.rxjava2.generate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.andryyu.rxjavademo.base.BaseOperationActivity;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxDeferActivity extends BaseOperationActivity {

    private Observable<String> justObservable;
    private Observable<String> deferObservable;
    private StringBuilder man;
    @Override
    protected void initView() {
         man = new StringBuilder("小明");
        justObservable = Observable.just(man.toString());

        deferObservable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just(man.toString());
            }
        });

        btnSubscribe.setText("Just");
        btnSubscribe2.setVisibility(View.VISIBLE);
        btnSubscribe2.setText("Defer");
        tvTop.setText("Defer\n\n" +
                "直到有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable。对比看下，使用just创建的并没有获取到最新的对象man\n" +
                "\n" +
                "final StringBuilder man = new StringBuilder(\"小明\");\n" +
                "justObservable = Observable.just(man.toString());\n" +
                "\n" +
                "deferObservable = Observable.defer(new Callable<ObservableSource<? extends String>>() {\n" +
                "   @Override\n" +
                "   public ObservableSource<? extends String> call() throws Exception {\n" +
                "       return Observable.just(man.toString());\n" +
                "   }\n" +
                "});\n" +
                "man.setLength(0);\n" +
                "man.append(\"小红 女 5岁\");");
    }

    @Override
    protected void initData() {
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                justObservable.subscribe(new Observer<String>() {

                    @Override
                    public void onError(Throwable e) {
                        sb.append("onError() ");
                        sb.append(e.getMessage());
                        sb.append("\n");
                        tvBottom.setText(sb);
                    }

                    @Override
                    public void onComplete() {
                        sb.append("onComplete()");
                        sb.append("\n");
                        tvBottom.setText(sb);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String o) {
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
                deferObservable.subscribe(new Observer<String>() {
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
                    public void onNext(String o) {
                        sb.append("onNext() ");
                        sb.append(o);
                        sb.append("\n");
                        tvBottom.setText(sb);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                });
            }
        });

        man.setLength(0);
        man.append("小红 女 5岁");
    }
}
