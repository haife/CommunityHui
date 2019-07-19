package com.kaiwukj.android.communityhui.di.module;

import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.model.SocialCircleModel;

import dagger.Binds;
import dagger.Module;


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/19
 * @desc  
 */
@Module
public abstract class SocialCircleModule {

    @Binds
    abstract SocialCircleContract.Model bindSocialCircleModel(SocialCircleModel model);
}