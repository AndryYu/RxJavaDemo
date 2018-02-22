package com.andryyu.rxjavademo.rxjava2.transform;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.andryyu.rxjavademo.base.BaseOperationActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class RxFlatMapActivity extends BaseOperationActivity {

    private Observable<String> observable;
    private Observable<String> concatMapObservable;
    private Observable<String> switchMapObservable;

    @Override
    protected void initView() {
        tvTop.setText("将一个发射数据的Observable变换为多个Observables，然后将它们发射的数据合并后放进一个单独的Observable。\n" +
                "这个方法是很有用的，例如，当你有一个这样的Observable：它发射一个数据序列，这些数据本身包含Observable成员或者可以变换为Observable，因此你可以创建一个新的Observable发射这些次级Observable发射的数据的完整集合。\n" +
                "注意：FlatMap对这些Observables发射的数据做的是merge操作，因此它们可能是交错的。为了防止交错的发生，可以使用与之类似的concatMap()操作符。\n" +
                "map和flatMapmap的区别：map transform one event to another, flatMap transform one event to zero or more event.\n\n" +
                "Observable<String> observable = Observable.just(0, 1, 2).flatMap(new Func1<Integer, Observable<String>>() {\n" +
                "   @Override\n" +
                "   public Observable<String> call(Integer integer) {\n" +
                "       int delay = 2 - integer;// 0延迟2s，1延迟1s，2无延迟\n" +
                "       return Observable.just(\"A\" + integer).delay(delay, TimeUnit.SECONDS);\n" +
                "   }\n" +
                "});\n" +
                "Observable<String> concatMapObservable = Observable.just(0, 1, 2).concatMap(new Func1<Integer, Observable<String>>() {\n" +
                "   @Override\n" +
                "   public Observable<String> call(Integer integer) {\n" +
                "       int delay = 2 - integer;// 0延迟2s，1延迟1s，2无延迟\n" +
                "       return Observable.just(\"A\" + integer).delay(delay, TimeUnit.SECONDS);\n" +
                "   }\n" +
                "});\n" +
                "Observable<String> switchMapObservable = Observable.just(0, 1, 2).switchMap(new Func1<Integer, Observable<String>>() {\n" +
                "   @Override\n" +
                "   public Observable<String> call(Integer integer) {\n" +
                "       int delay = 2 - integer;// 0延迟2s，1延迟1s，2无延迟\n" +
                "       return Observable.just(\"A\" + integer).delay(delay, TimeUnit.SECONDS);\n" +
                "   }\n" +
                "});");

        btnSubscribe.setText("flatMap");
        btnSubscribe2.setText("concatMap");
        btnSubscribe2.setVisibility(View.VISIBLE);
        btnSubscribe3.setText("switchMap");
        btnSubscribe3.setVisibility(View.VISIBLE);

        observable = Observable.just(0, 1, 2).flatMap(new Function<Integer, ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> apply(Integer integer) throws Exception {
                int delay = 2 - integer;// 0延迟2s，1延迟1s，2无延迟
                return Observable.just("A" + integer).delay(delay, TimeUnit.SECONDS);
            }
        });
        concatMapObservable = Observable.just(0, 1, 2).concatMap(new Function<Integer, ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> apply(Integer integer) throws Exception {
                int delay = 2 - integer;// 0延迟2s，1延迟1s，2无延迟
                return Observable.just("A" + integer).delay(delay, TimeUnit.SECONDS);
            }
        });
        switchMapObservable = Observable.just(0, 1, 2).switchMap(new Function<Integer, ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> apply(Integer integer) throws Exception {
                int delay = 2 - integer;// 0延迟2s，1延迟1s，2无延迟
                return Observable.just("A" + integer).delay(delay, TimeUnit.SECONDS);
            }
        });
    }

    @Override
    protected void initData() {
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                tvBottom.setText(sb);
                observable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
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
                tvBottom.setText(sb);
                concatMapObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
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
                    public void onNext(String o) {
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
                tvBottom.setText(sb);
                switchMapObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
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
                    public void onNext(String o) {
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
