package com.zmm.cniao5playtest.presenter;

import com.zmm.cniao5playtest.ui.BaseView;

/**
 * Created by Ivan on 2017/1/3.
 */

public class BasePresenter<M,V extends BaseView> {

    protected M mModel;
    protected V mView;

    public BasePresenter(M model, V view) {
        mModel = model;
        mView = view;
    }
}
