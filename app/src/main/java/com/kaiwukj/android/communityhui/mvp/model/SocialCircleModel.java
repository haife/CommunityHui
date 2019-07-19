package com.kaiwukj.android.communityhui.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.mcas.di.scope.ActivityScope;
import com.kaiwukj.android.mcas.integration.IRepositoryManager;
import com.kaiwukj.android.mcas.mvp.BaseModel;

import javax.inject.Inject;

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/19
 * @desc
 */
@ActivityScope
public class SocialCircleModel extends BaseModel implements SocialCircleContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SocialCircleModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}