package com.zmm.cniao5playtest.presenter.contract;

import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.bean.IndexBean;
import com.zmm.cniao5playtest.bean.PageBean;
import com.zmm.cniao5playtest.ui.BaseView;


/**
 * Created by Ivan on 2017/1/3.
 */

public interface AppInfoContract {


    interface View extends BaseView {

        void showResult(IndexBean indexBean);


//        void onRequestPermissonSuccess();
//        void onRequestPermissonError();
    }

    interface AppInfoView extends BaseView{

        void showResult(PageBean<AppInfo> page);

        void onLoadMoreComplete();

    }

    interface  AppDetailView extends BaseView{

        void showAppDetail(AppInfo appInfo);
    }

}
