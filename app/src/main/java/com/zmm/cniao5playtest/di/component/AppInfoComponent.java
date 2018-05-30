package com.zmm.cniao5playtest.di.component;

import com.zmm.cniao5playtest.di.FragmentScope;
import com.zmm.cniao5playtest.di.module.AppInfoModule;
import com.zmm.cniao5playtest.ui.fragment.CategoryAppFragment;
import com.zmm.cniao5playtest.ui.fragment.GamesFragment;
import com.zmm.cniao5playtest.ui.fragment.TopListFragment;

import dagger.Component;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/30
 * Time:上午10:56
 */

@FragmentScope
@Component(modules = {AppInfoModule.class},dependencies = {AppComponent.class})
public interface AppInfoComponent {

    void injectTopListFragment(TopListFragment fragment);
    void injectGamesFragment(GamesFragment fragment);
    void injectCategoryAppFragment(CategoryAppFragment fragment);
}
