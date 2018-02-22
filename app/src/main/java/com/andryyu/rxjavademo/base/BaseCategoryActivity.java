package com.andryyu.rxjavademo.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andryyu.rxjavademo.R;
import com.andryyu.rxjavademo.modal.OperatorModel;
import com.andryyu.rxjavademo.ui.adapter.OperatorsAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract  class BaseCategoryActivity extends BaseActivity {

    @BindView(R.id.rv_operation)
    RecyclerView rvOperation;

    protected List<OperatorModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    private void init(){
        fillData();
        OperatorsAdapter adapter = new OperatorsAdapter(data) {
            @Override
            public void onItemClick(int position) {
                itemClick(position);
            }
        };

        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        rvOperation.setLayoutManager(new LinearLayoutManager(this));
        rvOperation.setItemAnimator(new DefaultItemAnimator());
        rvOperation.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        rvOperation.setAdapter(adapter);
    }

    protected  abstract void fillData();
    protected abstract void itemClick(int position);
}
