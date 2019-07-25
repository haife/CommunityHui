package com.kaiwukj.android.communityhui.mvp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment;
import com.kaiwukj.android.communityhui.app.constant.ExtraCons;
import com.kaiwukj.android.communityhui.di.component.DaggerSocialCircleComponent;
import com.kaiwukj.android.communityhui.di.module.SocialCircleModule;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardDetailResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
 * @desc 发布帖子
 */
public class PostCardTypeFragment extends BaseSwipeBackFragment<SocialCirclePresenter> implements SocialCircleContract.View {
    @BindView(R.id.rv_post_car_type)
     RecyclerView mPostCardTypeRv;
    private List<CircleCardResult> mCardResults;
    private int RESULT_OK = 1;


    public static PostCardTypeFragment newInstance(List<CircleCardResult> cardResults) {
        PostCardTypeFragment fragment = new PostCardTypeFragment();
        fragment.mCardResults = cardResults;
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
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle_post_card_type, container, false);
        return attachToSwipeBack(view);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        assert getActivity() != null;
        initTopBar(getActivity().findViewById(R.id.qtb_social_circle));
        mPostCardTypeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        CardTypeAdapter adapter = new CardTypeAdapter(R.layout.recycle_item_post_card_type, mCardResults);
        mPostCardTypeRv.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putInt(ExtraCons.EXTRA_KEY_POST_CARD_TYPE, mCardResults.get(position).getId());
            bundle.putString(ExtraCons.EXTRA_KEY_POST_CARD_TYPE_NAME, mCardResults.get(position).getDicValue());
            setFragmentResult(RESULT_OK, bundle);
            getActivity().onBackPressed();
        });
    }

    private void initTopBar(QMUITopBar topBar) {
        topBar.setTitle(getString(R.string.social_circle_topic_type));
    }


    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public Context getCtx() {
        return this.getContext();
    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void finishLoadMore(@Nullable boolean noData) {

    }

    @Override
    public void onGetCardDetailResult(CircleCardDetailResult result) {

    }

    @Override
    public void onGetOtherHomePageData(SocialUserHomePageResult result) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void killMyself() {
        getActivity().onBackPressed();
    }

    /**
     * 类型适配器
     */
    static class CardTypeAdapter extends BaseQuickAdapter<CircleCardResult, BaseViewHolder> {

        public CardTypeAdapter(int layoutResId, @Nullable List<CircleCardResult> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CircleCardResult item) {
            helper.setText(R.id.tv_post_card_type, item.getDicValue());
        }
    }
}
