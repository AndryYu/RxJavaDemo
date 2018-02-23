package com.andryyu.rxjavademo.rxjava2.error;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andryyu.rxjavademo.base.BaseOperationActivity;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxRetryActivity extends BaseOperationActivity {

    private Observable<Integer> retryObservable;
    private Observable<Integer> retryWhenObservable;

    @Override
    protected void initView() {
        tvTop.setText("Retry操作符不会将原始Observable的onError通知传递给观察者，它会订阅这个Observable，再给它一次机会无错误地完成它的数据序列。Retry总是传递onNext通知给观察者，由于重新订阅，可能会造成数据项重复。无论收到多少次onError通知，无参数版本的retry都会继续订阅并发射原始Observable。接受单个count参数的retry会最多重新订阅指定的次数，如果次数超了，它不会尝试再次订阅，它会把最新的一个onError通知传递给它的观察者。\n" +
                "还有一个版本的retry接受一个谓词函数作为参数，这个函数的两个参数是：重试次数和导致发射onError通知的Throwable。这个函数返回一个布尔值，如果返回true，retry应该再次订阅和镜像原始的Observable，如果返回false，retry会将最新的一个onError通知传递给它的观察者。\n" +
                "retry操作符默认在trampoline调度器上执行。\n" +
                "retryWhen和retry类似，区别是，retryWhen将onError中的Throwable传递给一个函数，这个函数产生另一个Observable，retryWhen观察它的结果再决定是不是要重新订阅原始的Observable。如果这个Observable发射了一项数据，它就重新订阅，如果这个Observable发射的是onError通知，它就将这个通知传递给观察者然后终止。\n" +
                "retryWhen默认在trampoline调度器上执行，你可以通过参数指定其它的调度器。\n\n" +
                "retryObservable = Observable.create(new Observable.OnSubscribe<Integer>() {\n" +
                "   @Override\n" +
                "   public void call(Subscriber<? super Integer> subscriber) {\n" +
                "       sb.append(\"subscriber.onNext(0)\\n\");\n" +
                "       subscriber.onNext(0);\n" +
                "       int error = 1 / 0;\n" +
                "   }\n" +
                "}).subscribeOn(Schedulers.io()).retry(2);\n" +
                "\n" +
                "retryWhenObservable = Observable.create(new Observable.OnSubscribe<Integer>() {\n" +
                "   @Override\n" +
                "   public void call(Subscriber<? super Integer> subscriber) {\n" +
                "       sb.append(\"subscriber.onNext(1)\\n\");\n" +
                "       subscriber.onNext(1);\n" +
                "       int error = 1 / 0;\n" +
                "    }\n" +
                "}).retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {\n" +
                "   @Override\n" +
                "   public Observable<?> call(Observable<? extends Throwable> observable) {\n" +
                "       return observable.zipWith(Observable.range(1, 3), new Func2<Throwable, Integer, Integer>() {\n" +
                "           @Override\n" +
                "           public Integer call(Throwable throwable, Integer integer) {\n" +
                "               return integer;\n" +
                "           }\n" +
                "       }).flatMap(new Func1<Integer, Observable<?>>() {\n" +
                "           @Override\n" +
                "           public Observable<?> call(Integer o) {\n" +
                "               return Observable.timer(100 * o, TimeUnit.MILLISECONDS);\n" +
                "           }\n" +
                "       });\n" +
                "   }\n" +
                "});");

        btnSubscribe.setText("retry");
        btnSubscribe2.setText("retryWhen");
        btnSubscribe2.setVisibility(View.VISIBLE);

        retryObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                sb.append("subscriber.onNext(0)\n");
                emitter.onNext(0);
                int error = 1 / 0;
            }
        }).subscribeOn(Schedulers.io()).retry(2);

        retryWhenObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                sb.append("subscriber.onNext(1)\n");
                emitter.onNext(1);
                int error = 1 / 0;
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.zipWith(Observable.range(1, 3), new BiFunction<Throwable, Integer, Object>() {
                    @Override
                    public Object apply(Throwable throwable, Integer integer) throws Exception {
                        return integer;
                    }
                }).flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {
                        return Observable.timer(1000 * (Integer)o, TimeUnit.MILLISECONDS);
                    }
                });
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
                retryObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
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
                retryWhenObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
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
