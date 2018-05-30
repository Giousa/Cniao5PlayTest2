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
public class RecommendModule {


    private AppInfoContract.View mView;

    public RecommendModule(AppInfoContract.View view) {
        mView = view;
    }


    @Provides
    public AppInfoContract.View proviceRecommendView(){

        return mView;
    }

    @Provides
    public AppInfoModel proviceRecommendModel(ApiService apiService){

        return new AppInfoModel(apiService);
    }
}
