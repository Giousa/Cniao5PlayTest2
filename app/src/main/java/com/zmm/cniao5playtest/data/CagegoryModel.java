package com.zmm.cniao5playtest.data;


import com.zmm.cniao5playtest.bean.BaseBean;
import com.zmm.cniao5playtest.bean.Category;
import com.zmm.cniao5playtest.data.http.ApiService;
import com.zmm.cniao5playtest.presenter.contract.CategoryContract;

import java.util.List;

import io.reactivex.Observable;


/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.data
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class CagegoryModel implements CategoryContract.ICagegoryModel {



    private ApiService mApiService;

    public CagegoryModel(ApiService apiService){
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<Category>>> getCategories() {
        
        return mApiService.getCategories();
    }
}
