package com.zmm.cniao5playtest.ui.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.zmm.cniao5playtest.R;
import com.zmm.cniao5playtest.bean.AppInfo;
import com.zmm.cniao5playtest.di.component.AppComponent;
import com.zmm.cniao5playtest.di.component.DaggerRecommendComponent;
import com.zmm.cniao5playtest.di.module.RecommendModule;
import com.zmm.cniao5playtest.presenter.RecommendPresenter;
import com.zmm.cniao5playtest.presenter.contract.RecommendContract;
import com.zmm.cniao5playtest.ui.adapter.RecomendAppAdatper;
import com.zmm.cniao5playtest.ui.decoration.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends ProgressFragment<RecommendPresenter> implements RecommendContract.View {

    @BindView(R.id.recycle_view)
    RecyclerView mRecyclerView;

    private RecomendAppAdatper mAdatper;



    private ProgressDialog mProgressDialog;


    @Override
    protected int setLayout() {
        return R.layout.fragment_recomend;
    }


    @Override
    protected void init() {
        mProgressDialog = new ProgressDialog(getActivity());

        mPresenter.requestDatas();
    }

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {

        DaggerRecommendComponent.builder().appComponent(appComponent).recommendModule(new RecommendModule(this)).build().inject(this);


    }

    private void initRecycleView(List<AppInfo> datas){


        //为RecyclerView设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));

        //动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        mAdatper = new RecomendAppAdatper(getActivity(),datas);

        mRecyclerView.setAdapter(mAdatper);



    }


    @Override
    public void showResult(List<AppInfo> datas) {
        initRecycleView( datas);
    }

    @Override
    public void showNodata() {

        dismissLoading();
        Toast.makeText(getActivity(),"暂时无数据，请吃完饭再来", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String msg) {
        dismissLoading();
        Toast.makeText(getActivity(),"服务器开小差了："+msg, Toast.LENGTH_LONG).show();
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
