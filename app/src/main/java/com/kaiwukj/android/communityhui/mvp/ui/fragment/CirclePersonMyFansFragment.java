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
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CirclePersonFansRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardDetailResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyFansListResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.CirclePersonMyFansAdapter;
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
public class CirclePersonMyFansFragment extends BaseSupportFragment<SocialCirclePresenter> implements SocialCircleContract.View {

    @BindView(R.id.rv_circle_parson_page_card)
    RecyclerView mFansRv;

    @Inject
    RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.smart_refresh_circle_person_page)
    SmartRefreshLayout mSmartRefresh;

    @Inject
    List<MyFansListResult> mFansList;
    @Inject
    CirclePersonMyFansAdapter mFansAdapter;


    private int pageNum = 1;
    CirclePersonFansRequest request = new CirclePersonFansRequest();
    private int mUserId;

    //TODO 0：请求粉丝接口 1：请求关注的接口
    private int myRequestType = 0;

    public static CirclePersonMyFansFragment newInstance(int userId, int requestType) {
        Bundle args = new Bundle();
        CirclePersonMyFansFragment fragment = new CirclePersonMyFansFragment();
        fragment.mUserId = userId;
        fragment.myRequestType = requestType;
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
        switch (myRequestType) {
            case 0:
                mPresenter.queryMyAttentionList(request);
                break;
            case 1:
                mPresenter.queryFansList(request);
                break;
        }

        mFansRv.setLayoutManager(mLayoutManager);
        mFansRv.addItemDecoration(new RecycleViewDivide(LinearLayoutManager.VERTICAL, null, 2, ContextCompat.getColor(getContext(), R.color.window_background_color)));
        mFansRv.setAdapter(mFansAdapter);
        mSmartRefresh.setOnLoadMoreListener(refreshLayout -> {
            pageNum++;
            request.setPageNum(pageNum);
            mPresenter.queryFansList(request);
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
