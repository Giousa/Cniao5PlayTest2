package com.zmm.cniao5playtest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;
import com.zmm.cniao5playtest.R;
import com.zmm.cniao5playtest.common.Constant;
import com.zmm.cniao5playtest.common.util.ACache;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.pathView)
    PathView mPathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        mPathView.getPathAnimator()
                .delay(50)
                .duration(1000)
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {

                        String isShowGuide =  ACache.get(WelcomeActivity.this).getAsString(Constant.IS_SHOW_GUIDE);

                        // 第一次启动进入引导页面
                        if(null == isShowGuide){
                            startActivity(new Intent(WelcomeActivity.this,GuideActivity.class));

                        }
                        else{
                            startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                        }

                        finish();
                    }
                })
                .interpolator(new AccelerateDecelerateInterpolator())
                .start();
    }
}
