package com.kaiwukj.android.communityhui.mvp.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment;
import com.kaiwukj.android.communityhui.di.component.DaggerSocialCircleComponent;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.PostCardPickImageAdapter;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.kaiwukj.android.mcas.utils.McaUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.GridSpacingItemDecoration;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public static final String POST_CARD_TOPIC_FRAGMENT = "POST_CARD_TOPIC_FRAGMENT";
    public static final int REQUEST_CODE_CHOOSE_IMAGE = 1;
    private List<ImageItem> voucherImage = new ArrayList<>();
    private PostCardPickImageAdapter mPostCardAdapter;
    //选择图片的按钮
    private ImageItem pickItem;

    public static PostCardTopicFragment newInstance() {
        PostCardTopicFragment fragment = new PostCardTopicFragment();
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_circle_post_card_topic, container, false));
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (getActivity() != null) {
            QMUITopBar topBar = this.getActivity().findViewById(R.id.qtb_social_circle);
            if (topBar != null)
                initTopBar(topBar);
        }
        pickItem = new ImageItem();
        pickItem.path = McaUtils.getResourcesUri(R.mipmap.icon_tool_bar_left_back, getContext());
        voucherImage.add(pickItem);
        mPostCardAdapter = new PostCardPickImageAdapter(R.layout.recycle_item_post_card_pick_image, voucherImage, getContext());
        mPostCardImageRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mPostCardImageRv.addItemDecoration(new GridSpacingItemDecoration(30, 20, true));
        mPostCardImageRv.setAdapter(mPostCardAdapter);

        mPostCardAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (voucherImage.get(position).path.equals(McaUtils.getResourcesUri(R.mipmap.icon_tool_bar_left_back, getContext()))) {
                requestPermissions();

            }
        });


    }

    private void initTopBar(QMUITopBar topBar) {
        topBar.addLeftBackImageButton().setOnClickListener(view -> killMyself());
        topBar.setTitle(getString(R.string.social_circle_post_topic));
        topBar.addRightTextButton(getString(R.string.social_circle_post_action), R.id.qmui_top_right_btn).setOnClickListener(view -> {
            killMyself();
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
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
    }

    @Override
    public void killMyself() {
        Objects.requireNonNull(getActivity()).onBackPressed();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void post(Runnable runnable) {

    }
}
