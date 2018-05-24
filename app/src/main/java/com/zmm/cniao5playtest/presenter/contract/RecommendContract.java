package com.zmm.cniao5playtest.presenter.contract;

import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.presenter.BasePresenter;
import com.zmm.cniao5playtest.ui.BaseView;

import java.util.List;

/**
 * Created by Ivan on 2017/1/3.
 */

public interface RecommendContract {


    interface View extends BaseView {

        void showResult(List<AppInfo> datas);
        void showNodata();
        void showError(String msg);


    }


}
