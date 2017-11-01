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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ThreadSwitchActivity extends AppCompatActivity {

    private String TAG = ThreadSwitchActivity.class.getSimpleName();
    private Button btnSwitch;
    private TextView tvResult;
    private StringBuilder builder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_switch);

        btnSwitch = findViewById(R.id.btn_switch);
        tvResult = findViewById(R.id.tv_result);
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.delete(0, builder.length());
                initSwitchThread();
            }
        });
    }

    private void initSwitchThread(){
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                builder.append("Observable thread is : " + Thread.currentThread().getName() + "\n");
                builder.append("emit 1\n");
                emitter.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                builder.append("Observer thread is :" + Thread.currentThread().getName() + "\n");
                builder.append("onNext: " + integer + "\n");
                tvResult.setText(builder.toString());
            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        builder.append("After observeOn(mainThread), current thread is: " + Thread.currentThread().getName() + "\n");
                    }
                })
                .subscribe(consumer);

    }
}
