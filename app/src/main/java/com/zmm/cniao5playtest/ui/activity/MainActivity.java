package com.zmm.cniao5playtest.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.cniao5playtest.R;
import com.zmm.cniao5playtest.bean.User;
import com.zmm.cniao5playtest.common.Constant;
import com.zmm.cniao5playtest.common.font.Cniao5Font;
import com.zmm.cniao5playtest.common.imageloader.GlideCircleTransform;
import com.zmm.cniao5playtest.common.rx.RxBus;
import com.zmm.cniao5playtest.common.util.ACache;
import com.zmm.cniao5playtest.common.util.PermissionUtil;
import com.zmm.cniao5playtest.di.component.AppComponent;
import com.zmm.cniao5playtest.ui.adapter.ViewPagerAdapter;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {


    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;


    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;


    private View headerView;
    private ImageView mUserHeadView;
    private TextView mTextUserName;



    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

        //Rxjava2.0版本

//        RxBus.get().register(this);

        RxBus.getDefault().toObservable(User.class).subscribe(new Consumer<User>() {
            @Override
            public void accept(User user) throws Exception {
                initUserHeadView(user);
            }
        });


        PermissionUtil.requestPermisson(this, Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {

                        if(aBoolean){
                            initDrawerLayout();

                            initTablayout();

                            initUser();
                        }else {
                            //------
                        }
                    }
                });



    }

    private void initTablayout() {


        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(adapter.getCount());
        mViewPager.setAdapter(adapter);


        mTabLayout.setupWithViewPager(mViewPager);



    }

    private void initDrawerLayout() {


        headerView = mNavigationView.getHeaderView(0);

        mUserHeadView = (ImageView) headerView.findViewById(R.id.img_avatar);
        mUserHeadView.setImageDrawable(new IconicsDrawable(this, Cniao5Font.Icon.cniao_head).colorRes(R.color.white));

        mTextUserName = (TextView) headerView.findViewById(R.id.txt_username);




        mNavigationView.getMenu().findItem(R.id.menu_app_update).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_loop));
        mNavigationView.getMenu().findItem(R.id.menu_download_manager).setIcon(new IconicsDrawable(this, Cniao5Font.Icon.cniao_download));
        mNavigationView.getMenu().findItem(R.id.menu_app_uninstall).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline));
        mNavigationView.getMenu().findItem(R.id.menu_setting).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_gear_outline));

        mNavigationView.getMenu().findItem(R.id.menu_logout).setIcon(new IconicsDrawable(this, Cniao5Font.Icon.cniao_shutdown));

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {

                    case R.id.menu_logout:

                        logout();

                        break;



                }


                return false;
            }
        });


        mToolBar.inflateMenu(R.menu.toolbar_menu);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);

        drawerToggle.syncState();

        mDrawerLayout.addDrawerListener(drawerToggle);


    }

    private void logout() {

        ACache aCache = ACache.get(this);

        aCache.put(Constant.TOKEN,"");
        aCache.put(Constant.USER,"");

        mUserHeadView.setImageDrawable(new IconicsDrawable(this, Cniao5Font.Icon.cniao_head).colorRes(R.color.white));
        mTextUserName.setText("未登录");

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

        Toast.makeText(MainActivity.this,"您已退出登录",Toast.LENGTH_LONG).show();
    }


//    @Subscribe
//    public void getUser(User user){
//
//        initUserHeadView(user);
//
//
//    }

    private void initUserHeadView(User user){

        Glide.with(this).load(user.getLogo_url()).transform(new GlideCircleTransform(this)).into(mUserHeadView);

        mTextUserName.setText(user.getUsername());
    }

    private void initUser(){

        Object objUser= ACache.get(this).getAsObject(Constant.USER);

        if(objUser ==null){

            headerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
            });

        }
        else{

            User user = (User) objUser;
            initUserHeadView(user);

        }


    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        RxBus.get().unregister(this);
//    }
}