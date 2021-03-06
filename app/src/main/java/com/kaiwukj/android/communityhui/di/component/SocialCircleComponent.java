package com.kaiwukj.android.communityhui.di.component;

import com.kaiwukj.android.communityhui.di.module.SocialCircleModule;
import com.kaiwukj.android.communityhui.mvp.ui.activity.SocialCircleActivity;
import com.kaiwukj.android.communityhui.mvp.ui.activity.SocialCircleListActivity;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.CircleCardDetailFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.CirclePersonMyFansFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.CirclePersonPageCardFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.CirclePersonPageReplyFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.PostCardTopicFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.PostCardTypeFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.SocialCircleFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.SocialCircleListFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.SocialCirclePersonPageFragment;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.kaiwukj.android.mcas.di.scope.ActivityScope;

import dagger.Component;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/19
 * @desc
 */
@ActivityScope
@Component(modules = SocialCircleModule.class, dependencies = AppComponent.class)
public interface SocialCircleComponent {
    void inject(SocialCircleActivity activity);

    void inject(SocialCircleListActivity activity);

    void inject(SocialCircleFragment fragment);

    void inject(CircleCardDetailFragment fragment);

    void inject(PostCardTopicFragment fragment);

    void inject(SocialCirclePersonPageFragment fragment);

    void inject(SocialCircleListFragment fragment);

    void inject(PostCardTypeFragment fragment);

    void inject(CirclePersonPageCardFragment fragment);

    void inject(CirclePersonPageReplyFragment fragment);

    void inject(CirclePersonMyFansFragment fragment);
}