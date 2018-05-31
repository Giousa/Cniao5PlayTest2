package com.zmm.cniao5playtest.di.module;


import com.zmm.cniao5playtest.data.AppInfoModel;
import com.zmm.cniao5playtest.data.http.ApiService;

import dagger.Module;
import dagger.Provides;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.di
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */




@Module
public class AppModelModule {


    @Provides
    public AppInfoModel provideModel(ApiService apiService){

        return  new AppInfoModel(apiService);
    }




}
