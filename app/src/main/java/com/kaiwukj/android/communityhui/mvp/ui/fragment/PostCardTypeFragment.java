package com.kaiwukj.android.communityhui.mvp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment;
import com.kaiwukj.android.communityhui.app.constant.ExtraCons;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.qmuiteam.qmui.widget.QMUITopBar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/19
 * @desc 发布帖子
 */
public class PostCardTypeFragment extends BaseSwipeBackFragment {
    @BindView(R.id.rv_post_car_type)
    RecyclerView mPostCardImageRv;

    private List<CircleCardResult> mCardResults;
    private int RESULT_OK = 1;


    public static PostCardTypeFragment newInstance(List<CircleCardResult> cardResults) {
        PostCardTypeFragment fragment = new PostCardTypeFragment();
        fragment.mCardResults = cardResults;
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle_post_card_topic, container, false);
        ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        assert getActivity() != null;
        initTopBar(getActivity().findViewById(R.id.qtb_social_circle));
        mPostCardImageRv.setLayoutManager(new LinearLayoutManager(getContext()));
        CardTypeAdapter adapter = new CardTypeAdapter(R.layout.recycle_item_post_card_type, mCardResults);
        mPostCardImageRv.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putInt(ExtraCons.EXTRA_KEY_POST_CARD_TYPE, mCardResults.get(position).getId());
            setFragmentResult(RESULT_OK, bundle);
            getActivity().onBackPressed();
        });
    }

    private void initTopBar(QMUITopBar topBar) {
        topBar.addLeftBackImageButton().setOnClickListener(view -> getActivity().onBackPressed());
        topBar.setTitle(getString(R.string.social_circle_topic_type));
    }


    @Override
    public void post(Runnable runnable) {

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
