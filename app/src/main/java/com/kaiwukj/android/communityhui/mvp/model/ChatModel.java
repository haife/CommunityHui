package com.kaiwukj.android.communityhui.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.kaiwukj.android.communityhui.mvp.contract.ChatContract;
import com.kaiwukj.android.mcas.di.scope.ActivityScope;
import com.kaiwukj.android.mcas.integration.IRepositoryManager;
import com.kaiwukj.android.mcas.mvp.BaseModel;

import javax.inject.Inject;



@ActivityScope
public class ChatModel extends BaseModel implements ChatContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ChatModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}