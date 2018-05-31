package com.zmm.cniao5playtest.di.component;


import com.zmm.cniao5playtest.di.FragmentScope;
import com.zmm.cniao5playtest.di.module.AppDetailModule;
import com.zmm.cniao5playtest.ui.fragment.AppDetailFragment;

import dagger.Component;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.di
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

@FragmentScope
@Component(modules = {AppDetailModule.class},dependencies = {AppComponent.class})
public interface AppDetailComponent {



    void inject(AppDetailFragment appDetailFragment);

}
