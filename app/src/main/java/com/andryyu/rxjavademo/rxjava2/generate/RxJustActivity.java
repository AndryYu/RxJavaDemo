package com.andryyu.rxjavademo.rxjava2.generate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.andryyu.rxjavademo.base.BaseOperationActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxJustActivity extends BaseOperationActivity {

    private Observable<Integer[]> observable;

    @Override
    protected void initView() {
        tvTop.setText("Just\n\n" +
                "创建一个发射指定值的Observable，如果是just(0, 1, 2)就是会发射3次，如果是just(new Integer[]{0, 1, 2})只会发射一次\n\n" +
                "observable = Observable.just(new Integer[]{0, 1, 2});");
        observable = Observable.just(new Integer[]{0, 1, 2});
    }

    @Override
    protected void initData() {
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                observable.subscribe(new Observer<Integer[]>() {
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
                    public void onNext(Integer[] o) {
                        sb.append("onNext() ");
                        for (int i = 0; i < o.length; i++) {
                            sb.append(o[i]);
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
