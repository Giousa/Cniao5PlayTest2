package com.zmm.cniao5playtest.di.module;

import com.zmm.cniao5playtest.data.AppInfoModel;
import com.zmm.cniao5playtest.data.http.ApiService;
import com.zmm.cniao5playtest.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/23
 * Time:下午3:51
 */

@Module
public class AppInfoModule {


    private AppInfoContract.AppInfoView mView;

    public AppInfoModule(AppInfoContract.AppInfoView view) {
        mView = view;
    }


    @Provides
    public AppInfoContract.AppInfoView proviceRecommendView(){

        return mView;
    }

    @Provides
    public AppInfoModel proviceModel(ApiService apiService){

        return new AppInfoModel(apiService);
    }
}
