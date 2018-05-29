package com.zmm.cniao5playtest.di.component;

import android.app.Application;

import com.google.gson.Gson;
import com.zmm.cniao5playtest.common.rx.RxErrorHandler;
import com.zmm.cniao5playtest.data.http.ApiService;
import com.zmm.cniao5playtest.di.module.AppModule;
import com.zmm.cniao5playtest.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/23
 * Time:下午6:01
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    //将HttpModule中的ApiService暴露出来，以便于其他依赖于AppComponent的Component调用
    ApiService getApiService();

    Application getApplication();

    RxErrorHandler getRxErrorHandler();

    Gson getGson();
}
