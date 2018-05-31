package com.zmm.cniao5playtest.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zmm.cniao5playtest.R;
import com.zmm.cniao5playtest.bean.Banner;
import com.zmm.cniao5playtest.bean.IndexBean;
import com.zmm.cniao5playtest.common.imageloader.ImageLoader;
import com.zmm.cniao5playtest.ui.activity.AppDetailActivity;
import com.zmm.cniao5playtest.ui.widget.BannerLayout;
import com.zmm.cniao5playtest.ui.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.ui.adapter
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class IndexMultipleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  implements View.OnClickListener{


    public static final int TYPE_BANNER = 1;
    private static final int TYPE_ICON = 2;
    private static final int TYPE_APPS = 3;
    private static final int TYPE_GAMES = 4;



    private IndexBean mIndexBean;

    private LayoutInflater mLayoutInflater;

    private Context mContext;

    public IndexMultipleAdapter(Context context) {

        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(IndexBean indexBean) {

        mIndexBean = indexBean;
    }


    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_ICON;
        } else if (position == 2) {
            return TYPE_APPS;
        } else if (position == 3) {
            return TYPE_GAMES;
        }

        return 0;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == TYPE_BANNER) {

            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.template_banner, parent, false));
        } else if (viewType == TYPE_ICON) {

            return new NavIconViewHolder(mLayoutInflater.inflate(R.layout.template_nav_icon, parent, false));

        }
        else if(viewType==TYPE_APPS){

            //传值null，就会自动计算高度，若是parent，不会重新计算高度
            return  new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false),TYPE_APPS);
        }
        else if(viewType==TYPE_GAMES){

            return  new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false),TYPE_GAMES);
        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (position == 0) {

            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;

            List<Banner> banners =  mIndexBean.getBanners();
            List<String> urls = new ArrayList<>(banners.size());

            for (Banner banner : banners){

                urls.add(banner.getThumbnail());
            }

            bannerViewHolder.mBanner.setViewUrls(urls);

            bannerViewHolder.mBanner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {
//                    banners.get(position)
                    Toast.makeText(mContext,position+"  图片被点击了",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (position==1){

            NavIconViewHolder bannerViewHolder = (NavIconViewHolder) holder;

            bannerViewHolder.mLayoutHotApp.setOnClickListener(this);
            bannerViewHolder.mLayoutHotGame.setOnClickListener(this);
            bannerViewHolder.mLayoutHotSubject.setOnClickListener(this);

        }
        else {
            AppViewHolder viewHolder = (AppViewHolder) holder;



            AppInfoAdapter appInfoAdapter =  AppInfoAdapter.builder().showBrief(true).showCategoryName(false).showPosition(false).build();

            if(viewHolder.type==TYPE_APPS){
                viewHolder.mText.setText("热门应用");
                appInfoAdapter.addData(mIndexBean.getRecommendApps());
            }
            else{
                viewHolder.mText.setText("热门游戏");
                appInfoAdapter.addData(mIndexBean.getRecommendGames());
            }

            viewHolder.mRecyclerView.setAdapter(appInfoAdapter);

            viewHolder.mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Toast.makeText(mContext,"position = "+position+",类型："+TYPE_APPS,Toast.LENGTH_SHORT).show();
                }
            });



        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext,"栏目被点击了",Toast.LENGTH_SHORT).show();
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.banner)
        BannerLayout mBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            mBanner.setImageLoader(new ImgLoader());
        }
    }

    class NavIconViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.layout_hot_app)
        LinearLayout mLayoutHotApp;
        @BindView(R.id.layout_hot_game)
        LinearLayout mLayoutHotGame;
        @BindView(R.id.layout_hot_subject)
        LinearLayout mLayoutHotSubject;

        NavIconViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    class AppViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.text)
        TextView mText;
        @BindView(R.id.recycler_view)
        RecyclerView mRecyclerView;


        int type;

        public AppViewHolder(View itemView, int type) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.type = type;

            initRecyclerView();


        }

        private void initRecyclerView() {

            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext) {

                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });

            DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST);

            mRecyclerView.addItemDecoration(itemDecoration);

        }
    }

    class  ImgLoader implements BannerLayout.ImageLoader{


        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoader.load(path,imageView);
        }
    }

}
