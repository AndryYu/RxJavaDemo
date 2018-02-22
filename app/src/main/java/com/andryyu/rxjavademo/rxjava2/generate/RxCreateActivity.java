package com.andryyu.rxjavademo.rxjava2.generate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andryyu.rxjavademo.R;
import com.andryyu.rxjavademo.base.BaseActivity;
import com.andryyu.rxjavademo.base.BaseOperationActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxCreateActivity extends BaseOperationActivity {


    private Observable<Integer> observable;

    @Override
    protected void initView() {
        tvTop.setText("Create\n\n" +
                "从头开始创建一个Observable\n\n" +
                "observable = Observable.create(new ObservableOnSubscribe<Integer>(){\n" +
                "            @Override\n" +
                "            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {\n" +
                "                try {\n" +
                "                    for (int i = 0; i < 3; i++) {\n" +
                "                        emitter.onNext(i);\n" +
                "                    }\n" +
                "                    emitter.onComplete();\n" +
                "                } catch (Exception e) {\n" +
                "                    emitter.onError(e);\n" +
                "                }\n" +
                "            }\n" +
                "        });");
    }


    @Override
    protected void initData() {
        observable =  Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                try {
                    for (int i = 0; i < 3; i++) {
                        emitter.onNext(i);
                        Log.d("RxJavaAPI", "create call() " + i);
                    }
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });

        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                observable.subscribe(new Observer<Integer>() {

                    @Override
                    public void onError(Throwable e) {
                        sb.append("onError() ");
                        sb.append(e.getMessage());
                        sb.append("\n");
                        tvBottom.setText(sb);
                    }

                    @Override
                    public void onComplete() {
                        sb.append("onCompleted()");
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
