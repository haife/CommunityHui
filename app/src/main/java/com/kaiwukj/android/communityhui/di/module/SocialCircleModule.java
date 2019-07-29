package com.kaiwukj.android.communityhui.di.module;

import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardCommentResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHotResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyFansListResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.PersonPageCardCommentResult;
import com.kaiwukj.android.communityhui.mvp.model.SocialCircleModel;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.CirclePersonMyFansAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.CirclePersonPageCardAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.CirclePersonPageCommentAdapter;
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SocialCardCommentAdapter;
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
        return new SocialCircleTopicAdapter(R.layout.recycle_item_social_circle_topic, list, view.getCtx());
    }

    @ActivityScope
    @Provides
    List<CircleCardCommentResult> provideCommentList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    SocialCardCommentAdapter provideCommentAdapter(List<CircleCardCommentResult> list) {
        return new SocialCardCommentAdapter(R.layout.recycle_item_card_comment_layout, list, view.getCtx());
    }

    /*---------------------TA的主页------------------------*/

    @ActivityScope
    @Provides
    CirclePersonPageCardAdapter provideCirclePersonPageCardAdapter(List<CircleHomeResult> list) {
        return new CirclePersonPageCardAdapter(R.layout.recycle_item_circle_person_card, list, view.getCtx());
    }

    @ActivityScope
    @Provides
    List<PersonPageCardCommentResult> providePersonPageCommentList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    CirclePersonPageCommentAdapter provideCirclePersonPageCommentAdapter(List<PersonPageCardCommentResult> list) {
        return new CirclePersonPageCommentAdapter(R.layout.recycle_item_circle_person_reply, list, view.getCtx());
    }


    @ActivityScope
    @Provides
    List<MyFansListResult> providePersonFansCommentList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    CirclePersonMyFansAdapter provideCircleFansAdapter(List<MyFansListResult> list) {
        return new CirclePersonMyFansAdapter(R.layout.recycle_item_circle_mine_fans, list, view.getCtx());
    }

}