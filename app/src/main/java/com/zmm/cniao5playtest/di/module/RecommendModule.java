package com.zmm.cniao5playtest.di.module;

import com.zmm.cniao5playtest.presenter.contract.AppInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/23
 * Time:下午3:51
 */

@Module(includes = AppModelModule.class)
public class RecommendModule {


    private AppInfoContract.View mView;

    public RecommendModule(AppInfoContract.View view) {
        mView = view;
    }


    @Provides
    public AppInfoContract.View proviceRecommendView(){

        return mView;
    }

}
