package com.andryyu.rxjavademo.rxjava2.error;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andryyu.rxjavademo.base.BaseOperationActivity;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class RxCatchActivity extends BaseOperationActivity {

    private Observable<Integer> onErrorReturnObservable;
    private Observable<Integer> onErrorResumeNextObservable;
    private Observable<Integer> onExceptionResumeNextObservable;

    @Override
    protected void initView() {
        tvTop.setText("Catch操作符拦截原始Observable的onError通知，将它替换为其它的数据项或数据序列，让产生的Observable能够正常终止或者根本不终止。\n" +
                "onErrorReturn\n" +
                "让Observable遇到错误时发射一个特殊的项并且正常终止。\n" +
                "\n" +
                "onErrorResumeNext\n" +
                "让Observable在遇到错误时开始发射第二个Observable的数据序列。\n" +
                "\n" +
                "onExceptionResumeNext\n" +
                "和onErrorResumeNext很相似，不同的是，如果onError收到的Throwable不是一个Exception，它会将错误传递给观察者的onError方法，不会使用备用的Observable。\n\n" +
                "onErrorReturnObservable = Observable.create(new Observable.OnSubscribe<Integer>() {\n" +
                "   @Override\n" +
                "   public void call(Subscriber<? super Integer> subscriber) {\n" +
                "       subscriber.onError(new Error(\"Test Error\"));\n" +
                "   }\n" +
                "}).onErrorReturn(new Func1<Throwable, Integer>() {\n" +
                "   @Override\n" +
                "   public Integer call(Throwable throwable) {\n" +
                "       return 999;\n" +
                "   }\n" +
                "});\n" +
                "\n" +
                "onErrorResumeNextObservable = Observable.create(new Observable.OnSubscribe<Integer>() {\n" +
                "   @Override\n" +
                "   public void call(Subscriber<? super Integer> subscriber) {\n" +
                "       subscriber.onError(new Error(\"Test Error\"));\n" +
                "   }\n" +
                "}).onErrorResumeNext(Observable.just(0, 1, 2));\n" +
                "\n" +
                "onExceptionResumeNextObservable = Observable.create(new Observable.OnSubscribe<Integer>() {\n" +
                "   @Override\n" +
                "   public void call(Subscriber<? super Integer> subscriber) {\n" +
                "       subscriber.onError(new Error(\"Test Error\"));\n" +
                "   }\n" +
                "}).onExceptionResumeNext(Observable.just(0, 1, 2));");

        btnSubscribe.setText("onErrorReturn");
        btnSubscribe2.setText("onErrorResumeNext");
        btnSubscribe2.setVisibility(View.VISIBLE);
        btnSubscribe3.setText("onExceptionResumeNext");
        btnSubscribe3.setVisibility(View.VISIBLE);

        onErrorReturnObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onError(new Error("Test Error"));
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                return 999;
            }
        });
        onErrorResumeNextObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onError(new Error("Test Error"));
            }
        }).onErrorResumeNext(Observable.just(0, 1, 2));



        onExceptionResumeNextObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onError(new Error("Test Error"));
            }
        }).onExceptionResumeNext(Observable.just(0, 1, 2));
    }

    @Override
    protected void initData() {
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                onErrorReturnObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
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
                onErrorResumeNextObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
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
                onExceptionResumeNextObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
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
    }
}
