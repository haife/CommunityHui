package com.kaiwukj.android.communityhui.mvp.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackActivity;
import com.kaiwukj.android.communityhui.app.constant.ARouterUrlKt;
import com.kaiwukj.android.communityhui.app.constant.ExtraCons;
import com.kaiwukj.android.communityhui.di.component.DaggerSocialCircleComponent;
import com.kaiwukj.android.communityhui.di.module.SocialCircleModule;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CircleHomeRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardDetailResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleListAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.CircleCardDetailFragment;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

@Route(path = ARouterUrlKt.CircleListUrl)
public class SocialCircleListActivity extends BaseSwipeBackActivity<SocialCirclePresenter> implements SocialCircleContract.View {
    @BindView(R.id.rv_social_circle_list)
    RecyclerView mCircleListRv;
    @BindView(R.id.qtb_social_circle_top)
    QMUITopBar mTopBar;
    @BindView(R.id.collapsing_social_circle_top_bar_list_layout)
    QMUICollapsingTopBarLayout mCollapsingTopBarLayout;
    @BindView(R.id.smart_refresh_social_circle_list)
    SmartRefreshLayout mSmartRefreshLayout;
    @Inject
    SocialCircleListAdapter mCircleListAdapter;

    @Autowired(name = ExtraCons.CIRCLE_TOPIC_TYPE_ID)
    int typeId;

    @Autowired(name = ExtraCons.CIRCLE_TOPIC_TYPE_TITLE)
    String title;

    @Inject
    List<CircleHomeResult> mDataList;

    private int page = 1;
    private CircleHomeRequest request = new CircleHomeRequest();

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
        return R.layout.activity_social_circle_list;

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initTopBar();
        request.setType(typeId);
        request.setPageNum(page);
        assert mPresenter != null;
        mPresenter.getHomeRecommendData(request, false);
        initRecycle();

    }

    private void initRecycle() {
        mCircleListRv.setLayoutManager(new LinearLayoutManager(this));
        mCircleListRv.addItemDecoration(new RecycleViewDivide(LinearLayoutManager.VERTICAL, null, 2, ContextCompat.getColor(this, R.color.window_background_color)));
        mCircleListRv.setAdapter(mCircleListAdapter);
        View emptyView = LayoutInflater.from(mContext).inflate(R.layout.empty_view_common_container, null);
        ImageView iv = emptyView.findViewById(R.id.iv_empty_view_type);
        iv.setBackground(ContextCompat.getDrawable(mContext, R.mipmap.icon_empty_view_common));
        mCircleListAdapter.setEmptyView(emptyView);
        mSmartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            page += 1;
            request.setPageNum(page);
            assert mPresenter != null;
            mPresenter.getHomeRecommendData(request, false);
        });

        mCircleListAdapter.setOnItemClickListener((adapter, view, position) -> {
            ARouter.getInstance().build(ARouterUrlKt.SocialCircleUrl)
                    .withString(SocialCircleActivity.FRAGMENT_KEY, CircleCardDetailFragment.CIRCLE_CARD_DETAIL).withBoolean(SocialCircleActivity.IS_MY_CARD, mDataList.get(position).isMyUnote())
                    .withInt(SocialCircleActivity.FRAGMENT_KEY_CARD_ID, mDataList.get(position).getId()).navigation();
        });
    }


    private void initTopBar() {
        setStatusBarFullTransparent();
        setFitSystemWindow(true);
        mTopBar.addLeftBackImageButton().setOnClickListener(v -> killMyself());
        mCollapsingTopBarLayout.setTitle(title);
    }


    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void finishLoadMore(boolean noData) {
        if (noData)
            mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
        else
            mSmartRefreshLayout.finishLoadMore();

    }

    @Override
    public void onGetCardDetailResult(CircleCardDetailResult result) {

    }

    @Override
    public void onGetOtherHomePageData(SocialUserHomePageResult result) {

    }

    @Override
    public void killMyself() {
        onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public Context getCtx() {
        return this;
    }
}
