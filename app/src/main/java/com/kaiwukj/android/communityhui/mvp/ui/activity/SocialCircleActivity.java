package com.kaiwukj.android.communityhui.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackActivity;
import com.kaiwukj.android.communityhui.app.constant.ARouterUrlKt;
import com.kaiwukj.android.communityhui.di.component.DaggerSocialCircleComponent;
import com.kaiwukj.android.communityhui.di.module.SocialCircleModule;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardDetailResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.CircleCardDetailFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.PostCardTopicFragment;
import com.kaiwukj.android.mcas.di.component.AppComponent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;


/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/19
 * @desc
 */
@Route(path = ARouterUrlKt.SocialCircleUrl)
public class SocialCircleActivity extends BaseSwipeBackActivity<SocialCirclePresenter> implements SocialCircleContract.View {
    @Autowired(name = FRAGMENT_KEY)
    String mFragmentKey;

    public static final String FRAGMENT_KEY = "FRAGMENT_KEY";

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSocialCircleComponent
                .builder()
                .appComponent(appComponent)
                .socialCircleModule(new SocialCircleModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_social_circle;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (CircleCardDetailFragment.CIRCLE_CARD_DETAIL.equals(mFragmentKey)) {
            loadRootFragment(R.id.fl_social_circle_container, CircleCardDetailFragment.newInstance(1));
        } else if (PostCardTopicFragment.POST_CARD_TOPIC_FRAGMENT.equals(mFragmentKey)) {
            loadRootFragment(R.id.fl_social_circle_container, PostCardTopicFragment.newInstance());
        }

    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void finishLoadMore(@Nullable boolean noData) {

    }

    @Override
    public void onGetCardDetailResult(CircleCardDetailResult result) {

    }

    @Override
    public void onGetOtherHomePageData(SocialUserHomePageResult result) {

    }

    @Override
    public Context getCtx() {
        return this.mContext;
    }
}
