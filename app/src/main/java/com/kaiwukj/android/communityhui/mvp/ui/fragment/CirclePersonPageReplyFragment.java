package com.kaiwukj.android.communityhui.mvp.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment;
import com.kaiwukj.android.communityhui.di.component.DaggerSocialCircleComponent;
import com.kaiwukj.android.communityhui.di.module.SocialCircleModule;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CirclePersonPageRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardDetailResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.PersonPageCardCommentResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.CirclePersonPageCommentAdapter;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 圈子个人中心回复
 */
public class CirclePersonPageReplyFragment extends BaseSupportFragment<SocialCirclePresenter> implements SocialCircleContract.View {

    @BindView(R.id.rv_circle_parson_page_card)
    RecyclerView mReplydRv;

    @Inject
    RecyclerView.LayoutManager mLayoutManager;


    @BindView(R.id.smart_refresh_circle_person_page)
    SmartRefreshLayout mSmartRefresh;

    @Inject
    List<PersonPageCardCommentResult> mPageCardCommentList;
    @Inject
    CirclePersonPageCommentAdapter mPageCommentAdapter;

    private int pageNum = 1;
    CirclePersonPageRequest request = new CirclePersonPageRequest();
    private int mUserId;

    public static CirclePersonPageReplyFragment newInstance(int userId) {
        Bundle args = new Bundle();
        CirclePersonPageReplyFragment fragment = new CirclePersonPageReplyFragment();
        fragment.mUserId = userId;
        fragment.setArguments(args);
        return fragment;
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
    public Context getCtx() {
        return getContext();
    }


    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle_person_page_card, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        assert mPresenter != null;
        request.setOtherUserId(mUserId);
        mPresenter.queryReplyList(request);
        mReplydRv.setLayoutManager(mLayoutManager);
        mReplydRv.addItemDecoration(new RecycleViewDivide(LinearLayoutManager.VERTICAL, null, 2, ContextCompat.getColor(getContext(), R.color.window_background_color)));
        mReplydRv.setAdapter(mPageCommentAdapter);
        mSmartRefresh.setOnLoadMoreListener(refreshLayout -> {
            pageNum++;
            request.setPageNum(pageNum);
            mPresenter.queryCircleMyNoteList(request);
        });
    }


    @Override
    public void finishRefresh() {

    }

    @Override
    public void finishLoadMore(@Nullable boolean noData) {
        mSmartRefresh.finishLoadMore();
        if (noData)
            mSmartRefresh.finishLoadMoreWithNoMoreData();
    }

    @Override
    public void onGetCardDetailResult(CircleCardDetailResult result) {

    }

    @Override
    public void onGetOtherHomePageData(SocialUserHomePageResult result) {

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
