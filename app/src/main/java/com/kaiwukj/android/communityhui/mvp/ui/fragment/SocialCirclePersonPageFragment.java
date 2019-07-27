package com.kaiwukj.android.communityhui.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment;
import com.kaiwukj.android.communityhui.di.component.DaggerSocialCircleComponent;
import com.kaiwukj.android.communityhui.di.module.SocialCircleModule;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardDetailResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult;
import com.kaiwukj.android.communityhui.mvp.presenter.SocialCirclePresenter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HomeViewPagerAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.widget.home.ScaleTransitionPagerTitleView;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms;
import com.kaiwukj.android.mcas.utils.McaUtils;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
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
public class SocialCirclePersonPageFragment extends BaseSwipeBackFragment<SocialCirclePresenter> implements SocialCircleContract.View {
    @BindView(R.id.view_pager_circle_person_page_container)
    ViewPager mPersonContainer;

    @BindView(R.id.magic_indicator_circle_person_page)
    MagicIndicator mPersonPageMagic;

    @BindView(R.id.riv_circle_person_page_info_photo)
    QMUIRadiusImageView mHeadIv;

    @BindView(R.id.riv_circle_person_page_name)
    TextView mNameTv;

    @BindView(R.id.riv_circle_person_page_sign)
    TextView mSignTv;


    public static final String SOCIAL_CIRCLE_PERSON_PAGEF_RAGMENT = "SOCIAL_CIRCLE_PERSON_PAGEF_RAGMENT";
    private List<Fragment> mFragmentList = new ArrayList<>();
    private String mUserId;

    public static SocialCirclePersonPageFragment newInstance(String userId) {
        SocialCirclePersonPageFragment fragment = new SocialCirclePersonPageFragment();
        fragment.mUserId = userId;
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_circle_person_page, container, false));
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        assert mPresenter != null;
        mPresenter.requestSocialHomePage(mUserId);
        QMUITopBar topBar = this.getActivity().findViewById(R.id.qtb_social_circle);
        initTopBar(topBar);
    }

    private void initTopBar(QMUITopBar topBar) {
        topBar.addLeftBackImageButton().setOnClickListener(view -> killMyself());
        topBar.setTitle(getString(R.string.social_circle_person_page_title));
    }


    @Override
    public void onGetOtherHomePageData(SocialUserHomePageResult result) {
        GlideArms.with(getContext()).load(result.getHeadImg()).centerCrop().into(mHeadIv);
        mNameTv.setText(result.getNickName());
        mSignTv.setText(result.getPerSign());
        List<String> list = new ArrayList<>();
        list.add(result.getNoteCount() + "\n帖子");
        list.add(result.getReplyCount() + "\n回复");
        list.add(result.getFocusedCount() + "\n关注");
        list.add(result.getFansCount() + "\n粉丝");
        mFragmentList.add(SocialCircleListFragment.newInstance());
        mFragmentList.add(SocialCircleListFragment.newInstance());
        mFragmentList.add(SocialCircleListFragment.newInstance());
        mFragmentList.add(SocialCircleListFragment.newInstance());
        initMagicIndicatorView(list);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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


    private void initMagicIndicatorView(List<String> magicIndicatorContentList) {
        CommonNavigator mMIndicatorNavigator = new CommonNavigator(getContext());
        mMIndicatorNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return magicIndicatorContentList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                ScaleTransitionPagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(getContext());
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
                simplePagerTitleView.setText(magicIndicatorContentList.get(index));
                simplePagerTitleView.setWidth(McaUtils.getScreenWidth(context) / 4);
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(context, R.color.home_color_hot_service_text));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(context, R.color.common_text_dark_color));
                simplePagerTitleView.setOnClickListener(view -> mPersonContainer.setCurrentItem(index));
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(getContext());
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setLineHeight(4.0f);
                indicator.setColors(ContextCompat.getColor(context, R.color.common_text_dark_color));
                return indicator;

            }
        });


        mPersonPageMagic.setNavigator(mMIndicatorNavigator);
        ViewPagerHelper.bind(mPersonPageMagic, mPersonContainer);
        //bind fragmentViewPager
        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getChildFragmentManager(), mFragmentList);
        mPersonContainer.setAdapter(homeViewPagerAdapter);
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

    @Override
    public Context getCtx() {
        return getContext();
    }
}
