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
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;


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
    @BindView(R.id.rv_circle_card_detail_comment)
    RecyclerView mCircleCardCommentRv;
    private QMUITopBar mTopBar;
    public static final String CIRCLE_CARD_DETAIL = "CIRCLE_CARD_DETAIL";

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
        if (getActivity() != null) {
            mTopBar = this.getActivity().findViewById(R.id.qtb_social_circle);
            if (mTopBar != null)
                initTopBar(mTopBar);
        }
        List<HRecommendMultiItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new HRecommendMultiItemEntity(""));
        }
        SocialCircleListAdapter circleListAdapter = new SocialCircleListAdapter(R.layout.recycle_item_card_comment_layout, list);
        mCircleCardCommentRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mCircleCardCommentRv.addItemDecoration(new RecycleViewDivide(LinearLayoutManager.VERTICAL, null, 2,
                ContextCompat.getColor(getContext(), R.color.common_divide_line_color)));
        mCircleCardCommentRv.setAdapter(circleListAdapter);


    }

    private void initTopBar(QMUITopBar topBar) {
        topBar.addLeftBackImageButton().setOnClickListener(view -> killMyself());
        topBar.setTitle(getString(R.string.social_circle_theme_card));
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

        getActivity().onBackPressed();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
