package com.kaiwukj.android.communityhui.mvp.ui.activity;

import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackActivity;
import com.kaiwukj.android.communityhui.app.constant.ARouterUrlKt;
import com.kaiwukj.android.communityhui.app.constant.ExtraCons;
import com.kaiwukj.android.communityhui.di.component.DaggerSocialCircleComponent;
import com.kaiwukj.android.communityhui.di.module.SocialCircleModule;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleListAdapter;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout;
import com.qmuiteam.qmui.widget.QMUITopBar;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

@Route(path = ARouterUrlKt.CircleListUrl)
public class SocialCircleListActivity extends BaseSwipeBackActivity<SocialCirclePresenter> implements SocialCircleContract.View {
    @BindView(R.id.rv_social_circle_list)
    RecyclerView mCircleListRv;
    @BindView(R.id.qtb_social_circle_list)
    QMUITopBar mTopBar;
    @BindView(R.id.collapsing_social_circle_top_bar_list_layout)
    QMUICollapsingTopBarLayout mCollapsingTopBarLayout;
    @Inject
    SocialCircleListAdapter mCircleListAdapter;

    @Autowired(name = ExtraCons.CIRCLE_TOPIC_TYPE_ID)
    int typeId;

    @Autowired(name = ExtraCons.CIRCLE_TOPIC_TYPE_TITLE)
    String  title;

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
        // McaUtils.statuInScreen(this);

        return R.layout.activity_social_circle_list;

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initTopBar();
//        mPresenter.getHomeRecommendData(typeId,1,false);
//        mCircleListRv.setLayoutManager(new LinearLayoutManager(this));
//        mCircleListRv.addItemDecoration(new RecycleViewDivide(LinearLayoutManager.VERTICAL, null, 2, ContextCompat.getColor(this, R.color.window_background_color)));
//        mCircleListRv.setAdapter(mCircleListAdapter);
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
    public void finishLoadMore(@Nullable boolean noData) {

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
