package com.zmm.cniao5playtest.di.module;

import com.zmm.cniao5playtest.data.CagegoryModel;
import com.zmm.cniao5playtest.data.http.ApiService;
import com.zmm.cniao5playtest.presenter.contract.CategoryContract;

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
public class CategoryModule {



    private CategoryContract.CategoryView mView;

    public CategoryModule(CategoryContract.CategoryView view){


        this.mView = view;
    }





    @Provides
    public CategoryContract.CategoryView provideView(){

        return mView;
    }



    @Provides
    public CategoryContract.ICagegoryModel privodeModel(ApiService apiService){

        return  new CagegoryModel(apiService);
    }












}
