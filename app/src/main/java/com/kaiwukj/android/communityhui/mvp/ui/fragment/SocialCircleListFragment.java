package com.kaiwukj.android.communityhui.mvp.ui.fragment;

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
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class SocialCircleListFragment extends BaseSwipeBackFragment<SocialCirclePresenter> implements SocialCircleContract.View {
    @BindView(R.id.rv_fl_social_circle_list)
    RecyclerView mCircleListRv;


    private SocialCircleListAdapter mCircleListAdapter;


    public static SocialCircleListFragment newInstance() {
        Bundle args = new Bundle();
        SocialCircleListFragment fragment = new SocialCircleListFragment();
        fragment.setArguments(args);
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_social_circle_list, container, false));
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initTopBar();
        List<HRecommendMultiItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new HRecommendMultiItemEntity(""));
        }
        mCircleListAdapter = new SocialCircleListAdapter(R.layout.recycle_item_circle_with_photo_layout, list);
        mCircleListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mCircleListRv.addItemDecoration(new RecycleViewDivide(LinearLayoutManager.VERTICAL, null, 2, ContextCompat.getColor(getContext(), R.color.window_background_color)));
        mCircleListRv.setAdapter(mCircleListAdapter);

    }


    private void initTopBar() {

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
