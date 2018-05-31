package com.zmm.cniao5playtest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsLayoutInflater2;
import com.zmm.cniao5playtest.AppApplication;
import com.zmm.cniao5playtest.di.component.AppComponent;
import com.zmm.cniao5playtest.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/24
 * Time:下午1:13
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private Unbinder mUnbinder;
    protected AppApplication mAppApplication;

    @Inject
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));

        super.onCreate(savedInstanceState);


        setContentView(setLayout());

        mUnbinder = ButterKnife.bind(this);

        mAppApplication = (AppApplication) getApplication();
        setupActivityComponent(mAppApplication.getAppComponent());

        init();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

    protected void startActivity(Class c){
        this.startActivity(new Intent(this,c));
    }

    protected abstract int setLayout();

    protected abstract void init();

    protected abstract void setupActivityComponent(AppComponent appComponent);

}
