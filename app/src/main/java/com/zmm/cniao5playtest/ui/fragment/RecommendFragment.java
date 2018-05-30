package com.zmm.cniao5playtest.ui.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zmm.cniao5playtest.R;
import com.zmm.cniao5playtest.bean.IndexBean;
import com.zmm.cniao5playtest.di.component.AppComponent;
import com.zmm.cniao5playtest.di.component.DaggerRecommendComponent;
import com.zmm.cniao5playtest.di.module.RecommendModule;
import com.zmm.cniao5playtest.presenter.RecommendPresenter;
import com.zmm.cniao5playtest.presenter.contract.AppInfoContract;
import com.zmm.cniao5playtest.ui.adapter.IndexMultipleAdapter;

import butterknife.BindView;

/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements AppInfoContract.View {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    private IndexMultipleAdapter mAdatper;



    private ProgressDialog mProgressDialog;


    @Override
    protected int setLayout() {
        return R.layout.fragment_recomend;
    }


    @Override
    protected void init() {
        mProgressDialog = new ProgressDialog(getActivity());

        initRecycleView();
        mPresenter.requestDatas();
//        mPresenter.requestPermission();
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerRecommendComponent.builder().appComponent(appComponent).recommendModule(new RecommendModule(this)).build().inject(this);


    }

    private void initRecycleView(){


        //为RecyclerView设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }


    @Override
    public void showResult(IndexBean indexBean) {

        mAdatper = new IndexMultipleAdapter(getActivity());

        mAdatper.setData(indexBean);

        mRecyclerView.setAdapter(mAdatper);

    }


    @Override
    public void showLoading() {

        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {

        if(mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onEmptyViewClick() {
        mPresenter.requestDatas();
    }


}
