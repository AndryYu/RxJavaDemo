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
import io.reactivex.observables.GroupedObservable;

public class RxGroupByActivity extends BaseOperationActivity {

    private Observable<GroupedObservable<Boolean, Integer>> observable;

    @Override
    protected void initView() {
        tvTop.setText("GroupBy\n\n" +
                "将一个Observable<T>进行分组加工成为一个Observable<GroupedObservable<K, T>>，观察者onNext()收到的是GroupedObservable<K, T>\n\n" +
                "Observable<GroupedObservable<Boolean, Integer>> observable = Observable.just(0, 1, 2, 3, 4, 5).groupBy(new Func1<Integer, Boolean>() {\n" +
                "   @Override\n" +
                "   public Boolean call(Integer integer) {\n" +
                "       return integer % 2 == 0;//按奇数偶数分组\n" +
                "   }\n" +
                "}, new Func1<Integer, Integer>() {\n" +
                "   @Override\n" +
                "   public Integer call(Integer integer) {\n" +
                "       return integer % 2 == 0 ? integer * 2 : integer;//偶数就乘2，第二个Func1可以不写，就是返回原来的值\n" +
                "   }\n" +
                "});\n\n" +
                "观察者\n" +
                "public void onNext(GroupedObservable<Boolean, Integer> groupedObservable) {\n" +
                "   if (groupedObservable.getKey()) {//例子只处理偶数\n" +
                "       ...\n" +
                "   }\n" +
                "}");

        observable = Observable.just(0, 1, 2, 3, 4, 5).groupBy(new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) throws Exception {
                return integer % 2 == 0;//按奇数偶数分组
            }

        }, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                return integer % 2 == 0 ? integer * 2 : integer;//偶数就乘2，第二个Func1可以不写，就是返回原来的值
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
                observable.subscribe(new Observer<GroupedObservable<Boolean,Integer>>() {
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
                    public void onNext(GroupedObservable<Boolean, Integer> groupedObservable) {
                        sb.append("onNext() ");
                        sb.append(groupedObservable);
                        sb.append("\n");
                        tvBottom.setText(sb);
                        if (groupedObservable.getKey()) {//例子只处理偶数
                            groupedObservable.subscribe(new Observer<Integer>() {
                                @Override
                                public void onComplete() {
                                    sb.append("---sub onComplete()");
                                    sb.append("\n");
                                    tvBottom.setText(sb);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    sb.append("---sub onError() ");
                                    sb.append(e.getMessage());
                                    sb.append("\n");
                                    tvBottom.setText(sb);
                                }

                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Integer integer) {
                                    sb.append("---sub onNext() ");
                                    sb.append(integer);
                                    sb.append("\n");
                                    tvBottom.setText(sb);
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}
