package com.kaiwukj.android.communityhui.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.alibaba.android.arouter.launcher.ARouter;
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.HorizontalSpacesItemDecoration;
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment;
import com.kaiwukj.android.communityhui.app.constant.ARouterUrlKt;
import com.kaiwukj.android.communityhui.di.component.DaggerSocialCircleComponent;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.communityhui.mvp.ui.activity.SocialCircleActivity;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleListAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleTopicAdapter;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.kaiwukj.android.mcas.utils.McaUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/19
 * @desc 社交圈子
 */
public class SocialCircleFragment extends BaseSupportFragment<SocialCirclePresenter> implements SocialCircleContract.View {
    @BindView(R.id.rv_social_circle)
    RecyclerView mCircleRv;

    @BindView(R.id.iv_btn_social_circle)
    ImageButton mPostTopicBt;
    private SocialCircleListAdapter mCircleListAdapter;

    public static SocialCircleFragment newInstance() {
        SocialCircleFragment fragment = new SocialCircleFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerSocialCircleComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_social_circle, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        List<HRecommendMultiItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new HRecommendMultiItemEntity(""));
        }
        mCircleListAdapter = new SocialCircleListAdapter(R.layout.recycle_item_circle_with_photo_layout, list);
        mCircleRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mCircleRv.addItemDecoration(new RecycleViewDivide(LinearLayoutManager.VERTICAL, null, 2, ContextCompat.getColor(getContext(), R.color.window_background_color)));
        mCircleRv.setAdapter(mCircleListAdapter);
        View topicView = LayoutInflater.from(getContext()).inflate(R.layout.header_social_circle_topic, null);
        mCircleListAdapter.addHeaderView(topicView);
        RecyclerView topicRv = topicView.findViewById(R.id.rv_header_circle_topic);
        SocialCircleTopicAdapter topicAdapter = new SocialCircleTopicAdapter(R.layout.recycle_item_social_circle_topic, list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        topicRv.addItemDecoration(new HorizontalSpacesItemDecoration(14));
        McaUtils.configRecyclerView(topicRv, manager);
        topicRv.setAdapter(topicAdapter);

        topicAdapter.setOnItemClickListener((adapter, view, position) -> {
            ARouter.getInstance().build(ARouterUrlKt.CircleListUrl).navigation();
        });

        mCircleListAdapter.setOnItemClickListener((adapter, view, position) -> {
            ARouter.getInstance().build(ARouterUrlKt.SocialCircleUrl).withString(SocialCircleActivity.FRAGMENT_KEY, CircleCardDetailFragment.CIRCLE_CARD_DETAIL).navigation();
        });

        mPostTopicBt.setOnClickListener(view -> ARouter.getInstance().build(ARouterUrlKt.SocialCircleUrl).withString(SocialCircleActivity.FRAGMENT_KEY, PostCardTopicFragment.POST_CARD_TOPIC_FRAGMENT).navigation());

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
    public void killMyself() {

    }

    @Override
    public void post(Runnable runnable) {

    }
}
