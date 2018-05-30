package com.zmm.cniao5playtest.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.zmm.cniao5playtest.ui.bean.FragmentInfo;
import com.zmm.cniao5playtest.ui.fragment.CategoryFragment;
import com.zmm.cniao5playtest.ui.fragment.GamesFragment;
import com.zmm.cniao5playtest.ui.fragment.TopListFragment;
import com.zmm.cniao5playtest.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 2016/12/8.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {




    private List<FragmentInfo> mFragments = new ArrayList<>(4);


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        initFragments();
    }



    private void initFragments(){

        mFragments.add(new FragmentInfo("推荐",RecommendFragment.class));
        mFragments.add(new FragmentInfo ("排行", TopListFragment.class));


        mFragments.add(new FragmentInfo ("游戏", GamesFragment.class));
        mFragments.add(new FragmentInfo ("分类", CategoryFragment.class));

    }


    @Override
    public Fragment getItem(int position) {


        try {
            return (Fragment) mFragments.get(position).getFragment().newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return  null;

//        Fragment fragment = null;
//        switch (position){
//
//            case 0:
//                fragment = new RecommendFragment();
//                break;
//
//            case 1:
//                fragment = new TopListFragment();
//                break;
//
//            case 2:
//                fragment = new GamesFragment();
//                break;
//
//            case 3:
//                fragment = new CategoryFragment();
//                break;
//
//        }
//
//        return fragment;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }
}
