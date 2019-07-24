package com.kaiwukj.android.communityhui.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment;
import com.kaiwukj.android.communityhui.di.component.DaggerSocialCircleComponent;
import com.kaiwukj.android.communityhui.di.module.SocialCircleModule;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.api.Api;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CommentOtherRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardDetailResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCardCommentAdapter;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms;
import com.kaiwukj.android.mcas.utils.McaUtils;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @BindView(R.id.tv_circle_message_title)
    TextView mTitleTv;

    @BindView(R.id.qriv_circle_head_photo)
    QMUIRadiusImageView mUserHeadIv;

    @BindView(R.id.tv_circle_nick_name)
    TextView mNickNameTv;

    @BindView(R.id.tv_circle_publish_time)
    TextView mTimeTv;

    @BindView(R.id.tv_circle_type_tags)
    TextView mTagTv;

    @BindView(R.id.qbtn_collection)
    QMUIRoundButton mCollection;

    @BindView(R.id.tv_circle_message_content)
    TextView mContentTv;

    @BindView(R.id.tv_circle_comment_number)
    TextView mCommentNumberTv;

    @BindView(R.id.et_social_circle_reply_comment)
    EditText mCommentEt;

    @BindView(R.id.tv_social_circle_replay_comment_send)
    TextView mSubmitCommentTv;

    @BindView(R.id.rv_circle_card_detail_images)
    NineGridView mImageGroup;
    private ArrayList<ImageInfo> imageInfo = new ArrayList<>();
    @Inject
    RecyclerView.LayoutManager mLayoutManager;
    private List<CircleCardDetailResult.UnoteCommentListBean> mCommentListList;
    private SocialCardCommentAdapter mCommentAdapter;
    private QMUITopBar mTopBar;
    private CommentOtherRequest mCommentOtherRequest = new CommentOtherRequest();
    public static final String CIRCLE_CARD_DETAIL = "CIRCLE_CARD_DETAIL";

    private int mCardId;
    private QMUITipDialog dialog;

    public static CircleCardDetailFragment newInstance(int cardId) {
        CircleCardDetailFragment fragment = new CircleCardDetailFragment();
        fragment.mCardId = cardId;
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
        return inflater.inflate(R.layout.fragment_circle_card_detail, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        assert mPresenter != null;
        mPresenter.requestSocialCardDetail(mCardId);
        assert getActivity() != null;
        mTopBar = this.getActivity().findViewById(R.id.qtb_social_circle);
        initTopBar(mTopBar);
        initRvItemClick();
        mCommentOtherRequest.setNoteId(mCardId);
        mCommentListList = new ArrayList<>();
    }

    private void initRvItemClick() {
        mSubmitCommentTv.setOnClickListener(view -> {
            //评论别人
            if (McaUtils.isEmpty(mCommentEt.getText().toString())) {
                return;
            }
            mCommentOtherRequest.setContent(mCommentEt.getText().toString());

        });
        mCircleCardCommentRv.setNestedScrollingEnabled(false);
        mCircleCardCommentRv.setHasFixedSize(true);
    }

    private void initTopBar(QMUITopBar topBar) {
        topBar.addLeftBackImageButton().setOnClickListener(view -> killMyself());
        topBar.setTitle(getString(R.string.social_circle_theme_card));
    }

    @Override
    public void onGetCardDetailResult(CircleCardDetailResult result) {
        assert result != null;
        GlideArms.with(getContext()).load(Api.IMG_URL + result.getHeadImg()).circleCrop().into(mUserHeadIv);
        mTitleTv.setText(result.getTitle());
        mNickNameTv.setText(result.getNickName());
        mTimeTv.setText(result.getCreateTime());
        mTagTv.setText(result.getNoteType());
        mContentTv.setText(result.getContent());
        mCommentNumberTv.setText(result.getUnoteCommentList().size() == 0 ?
                getString(R.string.social_card_no_comment) : String.valueOf(result.getUnoteCommentList().size()));
        if (result.getImgList() != null) {
            for (int i = 0; i < result.getImgList().size(); i++) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(result.getImgList().get(i));
                info.setBigImageUrl(result.getImgList().get(i));
                imageInfo.add(info);
            }
        }
        mImageGroup.setSingleImageSize(McaUtils.getScreenWidth(getContext()) - McaUtils.dip2px(getContext(), 32));
        mImageGroup.setAdapter(new NineGridViewClickAdapter(getContext(), imageInfo));
        mCommentListList.addAll(result.getUnoteCommentList());
        mCommentAdapter = new SocialCardCommentAdapter(R.layout.recycle_item_card_comment_layout, mCommentListList, mContext);
        mCircleCardCommentRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mCircleCardCommentRv.setAdapter(mCommentAdapter);
        mCommentAdapter.notifyDataSetChanged();
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
        dialog = new QMUITipDialog.Builder(getContext()).setTipWord(message).create();
        dialog.setTitle(message);
        dialog.show();
        new Handler().postDelayed(() -> dialog.dismiss(), 800);
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

    @Override
    public Context getCtx() {
        return getContext();
    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void finishLoadMore(@Nullable boolean noData) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mCommentAdapter = null;
    }

}


