package com.zmm.cniao5playtest.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.zmm.cniao5playtest.ui.BaseView;

/**
 * Created by Ivan on 2017/1/3.
 */

public class BasePresenter<M,V extends BaseView> {

    protected M mModel;
    protected V mView;

    protected Context mContext;

    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;

        initContext();
    }

    private void initContext(){

        if(mView instanceof Fragment){
            mContext = ((Fragment) mView).getActivity();
        }else {
            mContext = (Activity) mView;
        }
    }


}
