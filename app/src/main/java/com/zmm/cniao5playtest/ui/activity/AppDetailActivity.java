package com.zmm.cniao5playtest.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zmm.cniao5playtest.R;
import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.common.util.DensityUtil;
import com.zmm.cniao5playtest.di.component.AppComponent;
import com.zmm.cniao5playtest.presenter.AppDetailPresenter;
import com.zmm.cniao5playtest.presenter.contract.AppInfoContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/30
 * Time:下午11:04
 */

public class AppDetailActivity extends BaseActivity<AppDetailPresenter> implements AppInfoContract.AppDetailView {

    @BindView(R.id.view_content)
    FrameLayout mViewContent;
    @BindView(R.id.activity_app_detail)
    LinearLayout mActivityAppDetail;

    @Override
    protected int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    protected void init() {

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

        ViewGroup.MarginLayoutParams marginLayoutParams = new LinearLayout.LayoutParams(mViewContent.getLayoutParams());

        marginLayoutParams.topMargin = top - statusBarH;
        marginLayoutParams.leftMargin = left;
        marginLayoutParams.width = view.getWidth();
        marginLayoutParams.height = view.getHeight();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(marginLayoutParams);

        mViewContent.setLayoutParams(layoutParams);

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


        ObjectAnimator animator = ObjectAnimator.ofFloat(mViewContent, "scaleY", 1f, (float) h);

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mViewContent.setBackgroundColor(getResources().getColor(R.color.white));

            }
        });

        animator.setStartDelay(1000);
        animator.setDuration(10000);
        animator.start();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showAppDetail(AppInfo appInfo) {

    }
}
