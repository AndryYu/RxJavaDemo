package com.andryyu.rxjavademo.rxjava2.transform;


import android.view.View;

import com.andryyu.rxjavademo.base.BaseOperationActivity;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

public class RxScanActivity extends BaseOperationActivity {

    private Observable<String> observable;

    @Override
    protected void initView() {
        tvTop.setText("call(s, s2)的返回值就是下一次的s\n\n" +
                "Observable<String> observable = Observable.just(\"A\", \"B\", \"C\").scan(new Func2<String, String, String>() {\n" +
                "   @Override\n" +
                "   public String call(String s, String s2) {\n" +
                "       return s + s2;\n" +
                "   }\n" +
                "});");

        observable = Observable.just("A", "B", "C").scan(new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return s + s2;
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
                observable.subscribe(new Observer<String>() {
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
