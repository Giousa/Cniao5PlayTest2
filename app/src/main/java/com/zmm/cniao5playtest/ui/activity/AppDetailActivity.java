package com.zmm.cniao5playtest.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zmm.cniao5playtest.R;
import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.common.Constant;
import com.zmm.cniao5playtest.common.imageloader.ImageLoader;
import com.zmm.cniao5playtest.common.util.DensityUtil;
import com.zmm.cniao5playtest.di.component.AppComponent;
import com.zmm.cniao5playtest.presenter.AppDetailPresenter;
import com.zmm.cniao5playtest.ui.fragment.AppDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/30
 * Time:下午11:04
 */

public class AppDetailActivity extends BaseActivity<AppDetailPresenter> {

    @BindView(R.id.view_temp)
    View mViewTemp;
    @BindView(R.id.img_icon)
    ImageView mImgIcon;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.txt_name)
    TextView mTxtName;
    @BindView(R.id.view_coordinator)
    CoordinatorLayout mToolbarLayout;
    @BindView(R.id.view_content)
    FrameLayout mViewContent;

    private AppInfo mAppInfo;

    @Override
    protected int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    protected void init() {

        mAppInfo = (AppInfo) getIntent().getSerializableExtra(Constant.APPINFO);

        //=======================
        ImageLoader.load(Constant.BASE_IMG_URL+mAppInfo.getIcon(),mImgIcon);
        mTxtName.setText(mAppInfo.getDisplayName());




        mToolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //=======================

        View view = mAppApplication.getView();

        Bitmap bitmap = getViewImageCache(view);

        if (bitmap != null) {

            mViewContent.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }

        //确定点击的View，所在位置
        int[] location = new int[2];
        view.getLocationOnScreen(location);

        int left = location[0];
        int top = location[1];

        //获取状态栏高度，目的是减去这个高度，防止偏移
        int statusBarH = DensityUtil.getStatusBarH(this);

        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(mViewTemp.getLayoutParams());

        marginLayoutParams.topMargin=top-statusBarH;
        marginLayoutParams.leftMargin = left;
        marginLayoutParams.width = view.getWidth();
        marginLayoutParams.height =view.getHeight();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(marginLayoutParams);

        mViewTemp.setLayoutParams(params);

        open();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    private Bitmap getViewImageCache(View view) {


        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();

        if (bitmap == null)
            return null;


        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());

        view.destroyDrawingCache();

        return bitmap;


    }

    private void open() {

        int h = DensityUtil.getScreenH(this);


        ObjectAnimator animator = ObjectAnimator.ofFloat(mViewTemp, "scaleY", 1f, (float) h);

        animator.setStartDelay(500);
        animator.setDuration(1000);

        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                mViewTemp.setBackgroundColor(getResources().getColor(R.color.white));

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                mViewTemp.setVisibility(View.GONE);
                mToolbarLayout.setVisibility(View.VISIBLE);

                //动画结束，进入Fragment
                initFragment();
            }
        });


        animator.start();
    }

    private void initFragment() {


        AppDetailFragment fragment = new AppDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(Constant.APPINFO_ID, mAppInfo.getId());//这里的values就是我们要传的值
        fragment.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.view_content, fragment);
        transaction.commitAllowingStateLoss();


    }

}
