package com.zmm.cniao5playtest.di.component;

import com.zmm.cniao5playtest.di.FragmentScope;
import com.zmm.cniao5playtest.di.module.RecommendModule;
import com.zmm.cniao5playtest.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/23
 * Time:下午3:53
 */

@FragmentScope
@Component(modules = {RecommendModule.class},dependencies = {AppComponent.class})
public interface RecommendComponent {

    void inject(RecommendFragment recommendFragment);
}
