package com.andryyu.rxjavademo.rxjava2.generate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.andryyu.rxjavademo.base.BaseOperationActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxFromActivity extends BaseOperationActivity {

    private Observable<Integer> observable;
    private Observable<String> observable2;

    @Override
    protected void initView() {
        tvTop.setText("fromArray\n\n" +
                "这里它和just有着相同的功能，都是接受数据源并创建一个被观察者对象。不同的是fromArray可以接受可变长参数对象（其实就是一个数组），它会把数组中的每个元素发射出来。just也是可以输入多个参数，但是是定长的，SDK内置了一些重载的方法，最多可以输入10个参数\n\n" +
                "observable = Observable.fromArray(new Integer[]{0, 1, 2});\n\n" +
                "fromIterable\n\n" +
                "此方法接收一个继承自Iterable接口的参数，简单的说就是java中的集合类。因此你可以传入一个list集合等等\n\n" +
                "List<String> list=new ArrayList<>();\n" +
                "list.add(\"I\");\n"+
                "list.add(\"am\");\n"+
                "list.add(\"a\");\n"+
                "list.add(\"person!\");\n"+
                "observable2 = Observable.fromIterable(list);");

        List<String> list=new ArrayList<>();
        list.add("I");
        list.add("am");
        list.add("a");
        list.add("person!");

        observable = Observable.fromArray(new Integer[]{0, 1, 2});
        observable2 = Observable.fromIterable(list);
        btnSubscribe.setText("fromArray");
        btnSubscribe2.setText("fromIterable");
        btnSubscribe2.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                observable.subscribe(new Observer<Integer>() {
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
            public void onClick(View view) {
                sb.setLength(0);
                tvBottom.setText(sb.toString());
                observable2.subscribe(new Observer<String>() {
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
