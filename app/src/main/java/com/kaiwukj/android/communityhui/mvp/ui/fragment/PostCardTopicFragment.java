package com.kaiwukj.android.communityhui.mvp.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
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
import com.kaiwukj.android.communityhui.app.constant.ExtraCons;
import com.kaiwukj.android.communityhui.di.component.DaggerSocialCircleComponent;
import com.kaiwukj.android.communityhui.di.module.SocialCircleModule;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.PostCardRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardDetailResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.PostCardPickImageAdapter;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.kaiwukj.android.mcas.utils.McaUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.GridSpacingItemDecoration;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
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
 * @desc 发布帖子
 */
public class PostCardTopicFragment extends BaseSwipeBackFragment<SocialCirclePresenter> implements SocialCircleContract.View {
    @BindView(R.id.rv_social_circle_card_imgs)
    RecyclerView mPostCardImageRv;

    @BindView(R.id.rl_social_circle_card_type)
    TextView mPostCardTypeTv;


    @BindView(R.id.et_social_circle_post_card_title)
    EditText mTitleEt;

    @BindView(R.id.et_social_circle_card_content)
    EditText mContentEt;
    public static final String POST_CARD_TOPIC_FRAGMENT = "POST_CARD_TOPIC_FRAGMENT";
    private static final int REQUEST_CODE_CHOOSE_IMAGE = 1;
    private static final int REQUEST_CODE_CHOOSE_TYPE = 2;
    private List<ImageItem> voucherImage = new ArrayList<>();
    private PostCardPickImageAdapter mPostCardAdapter;
    //选择图片的按钮
    private ImageItem pickItem;
    //发帖请求的实体类
    private PostCardRequest mCardRequest = new PostCardRequest();
    @Inject
    List<CircleCardResult> mCardResults;
    private QMUITipDialog dialog;

    public static PostCardTopicFragment newInstance() {
        PostCardTopicFragment fragment = new PostCardTopicFragment();
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_circle_post_card_topic, container, false));
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.requestCircleCardList();
        initLayout();
        processClick();
    }

    private void initLayout() {
        if (getActivity() != null) {
            QMUITopBar topBar = this.getActivity().findViewById(R.id.qtb_social_circle);
            if (topBar != null)
                initTopBar(topBar);
        }
        pickItem = new ImageItem();
        pickItem.path = McaUtils.getResourcesUri(R.drawable.icon_social_circle_add_image, getContext());
        voucherImage.add(pickItem);
        mPostCardAdapter = new PostCardPickImageAdapter(R.layout.recycle_item_post_card_pick_image, voucherImage, getContext());
        mPostCardImageRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mPostCardImageRv.addItemDecoration(new GridSpacingItemDecoration(30, 20, true));
        mPostCardImageRv.setAdapter(mPostCardAdapter);
    }

    private void processClick() {
        //选择类型
        mPostCardTypeTv.setOnClickListener(view -> {
            startForResult(PostCardTypeFragment.newInstance(mCardResults), REQUEST_CODE_CHOOSE_TYPE);
        });

        mPostCardAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (voucherImage.get(position).path.equals(McaUtils.getResourcesUri(R.drawable.icon_social_circle_add_image, getContext()))) {
                //选择图片
                requestPermissions();
            }
        });

    }

    private void initTopBar(QMUITopBar topBar) {
        topBar.addLeftBackImageButton().setOnClickListener(view -> killMyself());
        topBar.setTitle(getString(R.string.social_circle_post_topic));
        topBar.addRightTextButton(getString(R.string.social_circle_post_action), R.id.qmui_top_right_btn).setOnClickListener(view -> {
            String title = mTitleEt.getText().toString();
            if (McaUtils.isEmpty(title)) {
                showMessage(getString(R.string.post_card_input_title_hint));
                return;
            }
            mCardRequest.setTitle(title);
            String content = mContentEt.getText().toString();
            if (McaUtils.isEmpty(content)) {
                showMessage(getString(R.string.post_card_input_content_hint));
                return;
            }
            mCardRequest.setContent(content);
            if (mCardRequest.getType() == null) {
                showMessage(getString(R.string.post_card_choose_type_hint));
                return;
            } else {
                if (mCardRequest.getType() == -1 && mCardRequest.getType() == 0) {
                    showMessage(getString(R.string.post_card_choose_type_hint));
                    return;
                }
            }
            assert mPresenter != null;
            List<String> paths = new ArrayList<>();
            voucherImage.remove(pickItem);
            if (voucherImage.size() > 0) {
                for (ImageItem item : voucherImage) {
                    paths.add(item.path);
                }
                mPresenter.requestQIToken(mCardRequest, paths);
            } else {
                mPresenter.postSocialCard(mCardRequest);
            }

            view.setEnabled(false);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            List listImg = (List<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            voucherImage.clear();
            assert listImg != null;
            if (listImg.size() == 9) {
                voucherImage.addAll(listImg);
            } else {
                voucherImage.addAll(listImg);
                voucherImage.add(pickItem);
            }
            mPostCardAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE_TYPE) {
            if (data != null) {
                int type = data.getInt(ExtraCons.EXTRA_KEY_POST_CARD_TYPE, -1);
                String typeStr = data.getString(ExtraCons.EXTRA_KEY_POST_CARD_TYPE_NAME);
                mCardRequest.setType(type);
                mPostCardTypeTv.setText(typeStr);
            }

        }
    }

    @SuppressLint("CheckResult")
    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(getActivity());
        rxPermission
                .requestEach(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        ImagePicker imagePick = ImagePicker.getInstance();
                        imagePick.isFreeCrop = false;
                        imagePick.setMultiMode(true);
                        Intent intent = new Intent(getContext(), ImageGridActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_CHOOSE_IMAGE);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框

                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』

                    }
                });

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
        new Handler().postDelayed(() -> {
            dialog.dismiss();
            if (message.equals(getString(R.string.social_post_card_success))) {
                SocialCircleFragment.isRefreshList = true;
                killMyself();
            }
        }, 800);
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
        return mContext;
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
}
