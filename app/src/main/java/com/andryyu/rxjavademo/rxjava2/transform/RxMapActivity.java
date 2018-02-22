package com.andryyu.rxjavademo.rxjava2.transform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andryyu.rxjavademo.base.BaseOperationActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class RxMapActivity extends BaseOperationActivity {

    private Observable<String> observable;

    @Override
    protected void initView() {
        tvTop.setText("对Observable发射的每一项数据应用一个函数，执行变换操作。\n" +
                "cast操作符将原始Observable发射的每一项数据都强制转换为一个指定的类型，然后再发射数据，它是map的一个特殊版本。其实只是强转而已，比较简单，例子就不实现了。\n\n" +
                "Observable<String> observable = Observable.just(0, 1, 2).map(new Func1<Integer, String>() {\n" +
                "   @Override\n" +
                "   public String call(Integer integer) {\n" +
                "       return \"A\" + integer;// 0 -> A0\n" +
                "   }\n" +
                "});\n" +
                "//Observable<String> observable = Observable.just(object).cast(String.class)");

        observable = Observable.just(0, 1, 2).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "A" + integer;// 0 -> A0
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
