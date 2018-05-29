package com.zmm.cniao5playtest.presenter.contract;

import com.zmm.cniao5playtest.bean.IndexBean;
import com.zmm.cniao5playtest.ui.BaseView;


/**
 * Created by Ivan on 2017/1/3.
 */

public interface RecommendContract {


    interface View extends BaseView {

        void  showResult(IndexBean indexBean);


//        void onRequestPermissonSuccess();
//        void onRequestPermissonError();
    }


}
