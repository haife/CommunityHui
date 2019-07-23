package com.kaiwukj.android.communityhui.di.module;

import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHotResult;
import com.kaiwukj.android.communityhui.mvp.model.SocialCircleModel;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleListAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCircleTopicAdapter;
import com.kaiwukj.android.mcas.di.scope.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.Module;
import dagger.Provides;


/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/19
 * @desc
 */
@Module
public class SocialCircleModule {

    private SocialCircleContract.View view;

    public SocialCircleModule(SocialCircleContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public SocialCircleContract.View provideView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    public SocialCircleContract.Model provideModel(SocialCircleModel model) {
        return model;
    }


    @ActivityScope
    @Provides
    RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(view.getCtx()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 500;
            }
        };
    }

    @ActivityScope
    @Provides
    List<CircleCardResult> provideCardList() {
        return new ArrayList<>();
    }


    @ActivityScope
    @Provides
    List<CircleHomeResult> provideHomeList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    List<CircleHotResult> provideHotList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    SocialCircleListAdapter provideCircleAdapter(List<CircleHomeResult> list) {
        return new SocialCircleListAdapter(R.layout.recycle_item_circle_with_photo_layout, list, view.getCtx());
    }
    @ActivityScope
    @Provides
    SocialCircleTopicAdapter provideTopicAdapter(List<CircleCardResult> list) {
        return new SocialCircleTopicAdapter(R.layout.recycle_item_social_circle_topic, list,view.getCtx());
    }

}