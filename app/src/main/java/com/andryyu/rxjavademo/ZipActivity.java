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
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class ZipActivity extends AppCompatActivity {

    private String TAG = ZipActivity.class.getSimpleName();
    private Button btnZip,btnResult;
    private TextView tvResult;
    private StringBuilder builder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip);

        btnZip = findViewById(R.id.btn_zip);
        btnResult = findViewById(R.id.btn_result);
        tvResult = findViewById(R.id.tv_result);
        btnZip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.delete(0, builder.length());
                initZip();
            }
        });
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResult.setText(builder.toString());
            }
        });
    }

    private void initZip(){
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Thread.sleep(1000);

                Log.d(TAG, "emit 2");
                emitter.onNext(2);

                Log.d(TAG, "emit 3");
                emitter.onNext(3);

                Log.d(TAG, "emit 4");
                emitter.onNext(4);

                Log.d(TAG, "emit complete1");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "emit A");
                emitter.onNext("A");

                Log.d(TAG, "emit B");
                emitter.onNext("B");

                Log.d(TAG, "emit C");
                emitter.onNext("C");

                Log.d(TAG, "emit complete2");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                builder.append("===OnNext===" + value + "\n");
                Log.d(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                builder.append("===onComplete===\n");
                Log.d(TAG, "onComplete");
            }
        });
    }
}
