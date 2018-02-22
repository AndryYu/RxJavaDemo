package com.andryyu.rxjavademo.rxjava2.transform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.andryyu.rxjavademo.base.BaseOperationActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxBufferActivity extends BaseOperationActivity {

    private Observable<List<Integer>> observable;

    @Override
    protected void initView() {
        tvTop.setText("Buffer\n\n" +
                "定期收集Observable的数据放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一个值。buffer有多个重载方法，例子不一一列举。\n" +
                "注意：如果原来的Observable发射了一个onError通知，Buffer会立即传递这个通知，而不是首先发射缓存的数据，即使在这之前缓存中包含了原始Observable发射的数据。\n\n" +
                "Observable<List<Integer>> observable = Observable.just(0, 1, 2).buffer(2);");

        observable = Observable.just(0, 1, 2).buffer(2);
    }

    @Override
    protected void initData() {
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                observable.subscribe(new Observer<List<Integer>>() {
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
                    public void onNext(List<Integer> o) {
                        sb.append("onNext() ");
                        for (Integer i : o) {
                            sb.append(i);
                            sb.append(" ");
                        }
                        sb.append("\n");
                        tvBottom.setText(sb);
                    }
                });
            }
        });
    }
}
