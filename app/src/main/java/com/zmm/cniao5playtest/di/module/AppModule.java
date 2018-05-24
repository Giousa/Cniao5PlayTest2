package com.zmm.cniao5playtest.di.module;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/23
 * Time:下午5:59
 */

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){

        return mApplication;
    }

    @Provides
    @Singleton
    public Gson provideGson(){

        return new Gson();
    }
}
