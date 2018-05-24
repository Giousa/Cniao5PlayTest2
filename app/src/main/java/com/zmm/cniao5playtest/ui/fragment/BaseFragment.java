package com.zmm.cniao5playtest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zmm.cniao5playtest.AppApplication;
import com.zmm.cniao5playtest.di.component.AppComponent;
import com.zmm.cniao5playtest.di.component.DaggerRecommendComponent;
import com.zmm.cniao5playtest.di.module.RecommendModule;
import com.zmm.cniao5playtest.presenter.BasePresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/24
 * Time:下午1:25
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {



    private Unbinder mUnbinder;
    private AppApplication mAppApplication;
    private View mRootView;


    @Inject
    T mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(setLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);

        mAppApplication = (AppApplication) getActivity().getApplication();

        init();

        return mRootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupActivityComponent(mAppApplication.getAppComponent());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

    protected abstract int setLayout();

    protected abstract void init();

    protected abstract void setupActivityComponent(AppComponent appComponent);



}
