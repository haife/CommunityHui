package com.kaiwukj.android.communityhui.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment;
import com.kaiwukj.android.communityhui.di.component.DaggerSocialCircleComponent;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleListAdapter;
import com.kaiwukj.android.mcas.di.component.AppComponent;

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
 * @desc 社交圈子帖子
 */
public class CircleCardDetailFragment extends BaseSwipeBackFragment<SocialCirclePresenter> implements SocialCircleContract.View {
    @BindView(R.id.rv_social_circle)
    RecyclerView mCircleRv;
    private SocialCircleListAdapter mCircleListAdapter;

    public static CircleCardDetailFragment newInstance() {
        CircleCardDetailFragment fragment = new CircleCardDetailFragment();
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
        return inflater.inflate(R.layout.fragment_circle_card_detail, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        List<HRecommendMultiItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new HRecommendMultiItemEntity(""));
        }
        mCircleListAdapter = new SocialCircleListAdapter(R.layout.recycle_item_circle_with_photo_layout, list);
        mCircleRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mCircleRv.addItemDecoration(new RecycleViewDivide(LinearLayoutManager.VERTICAL, null, 20, ContextCompat.getColor(getContext(), R.color.window_background_color)));
        mCircleRv.setAdapter(mCircleListAdapter);
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
