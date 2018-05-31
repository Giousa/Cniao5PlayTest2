package com.zmm.cniao5playtest.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.zmm.cniao5playtest.R;
import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.common.Constant;
import com.zmm.cniao5playtest.common.imageloader.ImageLoader;
import com.zmm.cniao5playtest.common.util.DateUtils;
import com.zmm.cniao5playtest.di.component.AppComponent;
import com.zmm.cniao5playtest.di.component.DaggerAppDetailComponent;
import com.zmm.cniao5playtest.di.module.AppDetailModule;
import com.zmm.cniao5playtest.di.module.AppModelModule;
import com.zmm.cniao5playtest.presenter.AppDetailPresenter;
import com.zmm.cniao5playtest.presenter.contract.AppInfoContract;
import com.zmm.cniao5playtest.ui.adapter.AppInfoAdapter;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/5/31
 * Time:下午1:23
 */

public class AppDetailFragment extends ProgressFragment<AppDetailPresenter> implements AppInfoContract.AppDetailView {

    @BindView(R.id.view_gallery)
    LinearLayout mViewGallery;
    @BindView(R.id.expandable_text)
    TextView mExpandableText;
    @BindView(R.id.expand_collapse)
    ImageButton mExpandCollapse;
    @BindView(R.id.view_introduction)
    ExpandableTextView mViewIntroduction;
    @BindView(R.id.txt_update_time)
    TextView mTxtUpdateTime;
    @BindView(R.id.txt_version)
    TextView mTxtVersion;
    @BindView(R.id.txt_apk_size)
    TextView mTxtApkSize;
    @BindView(R.id.txt_publisher)
    TextView mTxtPublisher;
    @BindView(R.id.txt_publisher2)
    TextView mTxtPublisher2;
    @BindView(R.id.recycler_view_same_dev)
    RecyclerView mRecyclerViewSameDev;
    @BindView(R.id.recycler_view_relate)
    RecyclerView mRecyclerViewRelate;

    private int mAppId;

    private AppInfoAdapter mAdapter;

    private LayoutInflater mInflater;

//    public AppDetailFragment(int appId) {
//        mAppId = appId;
//    }

    @Override
    protected void init() {
        mInflater = LayoutInflater.from(getActivity());


        mAppId = (int) getArguments().get(Constant.APPINFO_ID);

        System.out.println("mAppId = "+mAppId);

        mPresenter.getAppDetail(mAppId);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_app_detail;
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerAppDetailComponent.builder().appComponent(appComponent)
                .appDetailModule(new AppDetailModule(this))
                .appModelModule(new AppModelModule())
                .build().inject(this);
    }

    @Override
    public void showAppDetail(AppInfo appInfo) {


        showScreenshot(appInfo.getScreenshot());

        mViewIntroduction.setText(appInfo.getIntroduction());


        mTxtUpdateTime.setText(DateUtils.formatDate(appInfo.getUpdateTime()));
        mTxtApkSize.setText((appInfo.getApkSize() / 1014 / 1024) + " Mb");
        mTxtVersion.setText(appInfo.getVersionName());
        mTxtPublisher.setText(appInfo.getPublisherName());
        mTxtPublisher2.setText(appInfo.getPublisherName());


        mAdapter = AppInfoAdapter.builder().layout(R.layout.template_appinfo2)
                .build();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewSameDev.setLayoutManager(layoutManager);


        mAdapter.addData(appInfo.getSameDevAppInfoList());
        mRecyclerViewSameDev.setAdapter(mAdapter);

        /////////////////////////////////////////////

        mAdapter = AppInfoAdapter.builder().layout(R.layout.template_appinfo2)
                .build();

        mRecyclerViewRelate.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter.addData(appInfo.getRelateAppInfoList());
        mRecyclerViewRelate.setAdapter(mAdapter);

    }


    private void showScreenshot(String screentShot) {


        List<String> urls = Arrays.asList(screentShot.split(","));


        for (String url : urls) {

            ImageView imageView = (ImageView) mInflater.inflate(R.layout.template_imageview, mViewGallery, false);

            ImageLoader.load(Constant.BASE_IMG_URL + url, imageView);

            mViewGallery.addView(imageView);

        }


    }

}
