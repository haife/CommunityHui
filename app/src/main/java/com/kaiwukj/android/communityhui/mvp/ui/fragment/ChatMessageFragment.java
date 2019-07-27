package com.kaiwukj.android.communityhui.mvp.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment;
import com.kaiwukj.android.communityhui.di.component.DaggerChatComponent;
import com.kaiwukj.android.communityhui.mvp.contract.ChatContract;
import com.kaiwukj.android.communityhui.mvp.presenter.ChatPresenter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HomeViewPagerAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.widget.home.ScaleTransitionPagerTitleView;
import com.kaiwukj.android.mcas.di.component.AppComponent;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/19
 * @desc 消息
 */
public class ChatMessageFragment extends BaseSupportFragment<ChatPresenter> implements ChatContract.View {

    @BindView(R.id.magic_indicator_chat_message)
    MagicIndicator mChatMagicIndicator;

    @BindView(R.id.view_pager_chat_message_container)
    ViewPager mChatPagerContainer;
    private ISupportFragment[] mFragments = new ISupportFragment[2];
    private List<Fragment> mHomeFragmentList = new ArrayList<>();
    private ChatListFragment mChatListFragment;

    public static ChatMessageFragment newInstance() {
        Bundle args = new Bundle();
        ChatMessageFragment fragment = new ChatMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerChatComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_message, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        List<String> magicIndicatorContentList = new ArrayList<>();
        magicIndicatorContentList.add("消息");
        magicIndicatorContentList.add("通知");
        initMagicIndicatorView(magicIndicatorContentList);
    }


    public void initMagicIndicatorView(List<String> magicIndicatorContentList) {
        CommonNavigator mMIndicatorNavigator = new CommonNavigator(getContext());
        mMIndicatorNavigator.setLeftPadding(10);
        mMIndicatorNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return magicIndicatorContentList == null ? 0 : magicIndicatorContentList.size();
            }

            @Override
            public IPagerTitleView getTitleView(final Context context, int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                simplePagerTitleView.setTypeface(Typeface.createFromAsset(context.getAssets(), "PingFangSC-Medium-Bold.ttf"));
                simplePagerTitleView.setText(magicIndicatorContentList.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(context, R.color.home_color_hot_service_text));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(context, R.color.common_text_dark_color));
                //  simplePagerTitleView.setOnClickListener(v -> mChatPagerContainer.setCurrentItem(index));
                return simplePagerTitleView;

            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setRoundRadius(20);
                indicator.setLineHeight(10f);
                indicator.setColors(ContextCompat.getColor(context, R.color.common_text_dark_color));
                return indicator;
            }
        });

        mChatListFragment = ChatListFragment.newInstance();
        mHomeFragmentList.add(mChatListFragment);
        mChatMagicIndicator.setNavigator(mMIndicatorNavigator);
        ViewPagerHelper.bind(mChatMagicIndicator, mChatPagerContainer);
        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getChildFragmentManager(), mHomeFragmentList);
        mChatPagerContainer.setAdapter(homeViewPagerAdapter);
    }



    @Override
    public void post(Runnable runnable) {
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

    }
}
