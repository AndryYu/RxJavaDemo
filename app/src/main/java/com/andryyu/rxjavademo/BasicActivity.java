package com.andryyu.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BasicActivity extends AppCompatActivity {

    private String TAG = BasicActivity.class.getSimpleName();
    private Button btnRxjava;
    private Button btnDisposable;
    private TextView tvResult;
    private StringBuilder builder = new StringBuilder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        btnRxjava = findViewById(R.id.btn_rxjava);
        btnDisposable = findViewById(R.id.btn_disposable);
        tvResult = findViewById(R.id.tv_result);
        btnRxjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.delete(0, builder.length());
                builder.append("===Observable/Observer简单调用===\n");
                initObserver();
            }
        });
        btnDisposable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.delete(0, builder.length());
                builder.append("===Disposable===下流拦截===\n");
                initDisposable();
            }
        });
    }

    //简单调用
    private void initObserver(){
        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                builder.append("1\n");
                emitter.onNext(1);
                builder.append("2\n");
                emitter.onNext(2);
                builder.append("3\n");
                emitter.onNext(3);
                builder.append("onComplete\n");
                emitter.onComplete();
            }
        });
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                builder.append("===onSubscribe===" + "\n");
                Log.d(TAG, "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                builder.append("onNext==="+value + "\n");
                Log.d(TAG, "" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                builder.append("===complete===\n");
                Log.d(TAG, "complete");
                tvResult.setText(builder.toString());
            }
        };
        //建立连接
        observable.subscribe(observer);
    }

    private void initDisposable(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                builder.append("emit 1\n");
                emitter.onNext(1);
                builder.append("emit 2\n");
                emitter.onNext(2);
                builder.append("emit 3\n");
                emitter.onNext(3);
                builder.append("emit complete\n");
                emitter.onComplete();
                builder.append("emit 4\n");
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;
            private int i;

            @Override
            public void onSubscribe(Disposable d) {
                builder.append("===onSubscribe===" + "\n");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
                builder.append("===onNext===" + value + "\n");
                i++;
                if (i == 2) {
                    builder.append("===Disposed===" + "\n");
                    mDisposable.dispose();
                    builder.append("===Disposed==="+ mDisposable.isDisposed() + "\n");
                    tvResult.setText(builder.toString());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                builder.append("===onComplete===" + "\n");

            }
        });
    }
}
