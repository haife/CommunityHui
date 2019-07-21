package com.kaiwukj.android.communityhui.mvp.ui.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackActivity;
import com.kaiwukj.android.communityhui.app.constant.ARouterUrlKt;
import com.kaiwukj.android.communityhui.di.component.DaggerSocialCircleComponent;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleListAdapter;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout;
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

@Route(path = ARouterUrlKt.CircleListUrl)
public class SocialCircleListActivity extends BaseSwipeBackActivity<SocialCirclePresenter> implements SocialCircleContract.View {
    @BindView(R.id.rv_social_circle_list)
    RecyclerView mCircleListRv;
    @BindView(R.id.qtb_social_circle_list)
    QMUITopBar mTopBar;
    @BindView(R.id.collapsing_social_circle_top_bar_list_layout)
    QMUICollapsingTopBarLayout mCollapsingTopBarLayout;

    private SocialCircleListAdapter mCircleListAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSocialCircleComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
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
        setStatusBarFullTransparent();
        setFitSystemWindow(true);
        initTopBar();
        List<HRecommendMultiItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new HRecommendMultiItemEntity(""));
        }
        mCircleListAdapter = new SocialCircleListAdapter(R.layout.recycle_item_circle_with_photo_layout, list);
        mCircleListRv.setLayoutManager(new LinearLayoutManager(this));
        mCircleListRv.addItemDecoration(new RecycleViewDivide(LinearLayoutManager.VERTICAL, null, 2, ContextCompat.getColor(this, R.color.window_background_color)));
        mCircleListRv.setAdapter(mCircleListAdapter);
    }


    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(v -> killMyself());
        mCollapsingTopBarLayout.setTitle("闲置圈");
    }


    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void killMyself() {
        onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
