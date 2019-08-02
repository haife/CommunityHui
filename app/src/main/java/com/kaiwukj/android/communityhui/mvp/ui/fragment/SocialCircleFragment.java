package com.kaiwukj.android.communityhui.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.HorizontalSpacesItemDecoration;
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment;
import com.kaiwukj.android.communityhui.app.constant.ARouterUrlKt;
import com.kaiwukj.android.communityhui.app.constant.ExtraCons;
import com.kaiwukj.android.communityhui.di.component.DaggerSocialCircleComponent;
import com.kaiwukj.android.communityhui.di.module.SocialCircleModule;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CircleHomeRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardDetailResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHotResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.communityhui.mvp.ui.activity.SocialCircleActivity;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleListAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleTopicAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.widget.home.CircleScrollView;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.kaiwukj.android.mcas.utils.McaUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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

    @BindView(R.id.smart_refresh_circle)
    SmartRefreshLayout mRefreshView;

    @BindView(R.id.iv_btn_social_circle)
    ImageButton mPostTopicBt;

    @Inject
    List<CircleCardResult> mCardResults;

    @Inject
    List<CircleHotResult> mHotList;

    @Inject
    List<CircleHomeResult> mDataList;

    @Inject
    SocialCircleListAdapter mCircleListAdapter;

    @Inject
    SocialCircleTopicAdapter mCircleTopicAdapter;
    private TextView mHotTvTitle;
    private CircleScrollView mHotContainer;
    private int page = 1;
    private CircleHomeRequest request = new CircleHomeRequest(0);
    //是否在 Visibility 刷新数据
    public static boolean isRefreshList = false;
    private List<View> hotListView = new ArrayList<>();

    public static SocialCircleFragment newInstance() {
        return new SocialCircleFragment();
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerSocialCircleComponent
                .builder()
                .appComponent(appComponent)
                .socialCircleModule(new SocialCircleModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_social_circle, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initRecycleView();
        assert mPresenter != null;
        mPresenter.getHomeRecommendData(request, true);
        mPresenter.requestCircleCardList();
        mPresenter.requestCircleHotList();
    }

    private void initRecycleView() {
        mCircleRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mCircleRv.addItemDecoration(new RecycleViewDivide(LinearLayoutManager.VERTICAL, null, 2, ContextCompat.getColor(getContext(), R.color.window_background_color)));
        mCircleRv.setAdapter(mCircleListAdapter);
        View topicView = LayoutInflater.from(getContext()).inflate(R.layout.header_social_circle_topic, null);
        mCircleListAdapter.addHeaderView(topicView);
        mHotContainer = topicView.findViewById(R.id.custom_circle_scroll_hot_container);
        RecyclerView topicRv = topicView.findViewById(R.id.rv_header_circle_topic);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        topicRv.addItemDecoration(new HorizontalSpacesItemDecoration(14));
        McaUtils.configRecyclerView(topicRv, manager);
        topicRv.setAdapter(mCircleTopicAdapter);
        mCircleTopicAdapter.setOnItemClickListener((adapter, view, position) -> {
            ARouter.getInstance().build(ARouterUrlKt.CircleListUrl).withInt(ExtraCons.CIRCLE_TOPIC_TYPE_ID, mCardResults.get(position).getId())
                    .withString(ExtraCons.CIRCLE_TOPIC_TYPE_TITLE, mCardResults.get(position).getDicValue()).navigation();
        });
        mCircleListAdapter.setOnItemClickListener((adapter, view, position) -> {

            ARouter.getInstance().build(ARouterUrlKt.SocialCircleUrl).withString(SocialCircleActivity.FRAGMENT_KEY, CircleCardDetailFragment.CIRCLE_CARD_DETAIL)
                    .withInt(SocialCircleActivity.FRAGMENT_KEY_CARD_ID, mDataList.get(position).getId()).navigation();

        });

        mCircleListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            //点击头衔跳转到个人首页
            if (view.getId() == R.id.qriv_circle_head_photo) {
                if (!mDataList.get(position).isMyUnote()) {
                    ARouter.getInstance().build(ARouterUrlKt.SocialCircleUrl).withString(SocialCircleActivity.FRAGMENT_KEY, SocialCirclePersonPageFragment.SOCIAL_CIRCLE_PERSON_PAGE_FRAGMENT)
                            .withInt(ExtraCons.EXTRA_KEY_USER_ID, mDataList.get(position).getUserId()).navigation();
                }
            }
        });
        mRefreshView.setOnRefreshListener(refreshLayout -> {
            page = 1;
            refreshLayout.setEnableLoadMore(false);
            mHotList.clear();
            mCardResults.clear();
            assert mPresenter != null;
            mPresenter.getHomeRecommendData(request, true);
            mPresenter.requestCircleCardList();
            mPresenter.requestCircleHotList();
        });
        mRefreshView.setOnLoadMoreListener(refreshLayout -> {
            page += 1;
            refreshLayout.setEnableRefresh(false);
            request.setPageNum(page);
            assert mPresenter != null;
            mPresenter.getHomeRecommendData(request, false);
        });

        mPostTopicBt.setOnClickListener(view -> ARouter.getInstance().build(ARouterUrlKt.SocialCircleUrl).withString(SocialCircleActivity.FRAGMENT_KEY, PostCardTopicFragment.POST_CARD_TOPIC_FRAGMENT).navigation());
    }

    @Override
    public void finishRefresh() {
        mRefreshView.setEnableLoadMore(true);
        mRefreshView.finishRefresh();
    }

    @Override
    public void finishLoadMore(@Nullable boolean noData) {
        mRefreshView.setEnableRefresh(true);
        if (noData) {
            mRefreshView.finishLoadMore();
            mRefreshView.finishLoadMoreWithNoMoreData();
        }

    }

    @Override
    public void onSupportVisible() {
        super.onSupportInvisible();
        assert mPresenter != null;
        if (isRefreshList) {
            mDataList.clear();
            isRefreshList = false;
            mPresenter.getHomeRecommendData(request, true);
        }
    }

    @Override
    public void onGetCardDetailResult(CircleCardDetailResult result) {

    }

    @Override
    public void onGetOtherHomePageData(SocialUserHomePageResult result) {
    }

    @Override
    public void showLoading() {
        if (mHotList.size() == 0) {
            mHotContainer.setVisibility(View.GONE);
            return;
        }
        //圈子热门帖子滚动数据
        for (int i = 0; i < mHotList.size(); i++) {
            RelativeLayout itemView = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.custom_social_circle_hot_item, null);
            mHotTvTitle = itemView.findViewById(R.id.tv_social_circle_hot_title);
            mHotTvTitle.setText(mHotList.get(i).getTitle());
            hotListView.add(itemView);
        }
        mHotContainer.setViews(hotListView);
        mHotContainer.setOnItemClickListener((position, view) -> {
            ARouter.getInstance().build(ARouterUrlKt.SocialCircleUrl).withString(SocialCircleActivity.FRAGMENT_KEY, CircleCardDetailFragment.CIRCLE_CARD_DETAIL)
                    .withInt(SocialCircleActivity.FRAGMENT_KEY_CARD_ID, mHotList.get(position).getId()).navigation();
        });
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

    @Override
    public Context getCtx() {
        return getContext();
    }


}
