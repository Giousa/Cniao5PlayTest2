package com.zmm.cniao5playtest.di.component;


import com.zmm.cniao5playtest.di.FragmentScope;
import com.zmm.cniao5playtest.di.module.CategoryModule;
import com.zmm.cniao5playtest.ui.fragment.CategoryFragment;

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
@Component(modules = CategoryModule.class,dependencies = AppComponent.class)
public interface CategoryComponent {


    void inject(CategoryFragment fragment);
}
