package com.kaiwukj.android.communityhui.mvp.presenter;

import android.app.Application;

import com.kaiwukj.android.communityhui.mvp.contract.ChatContract;
import com.kaiwukj.android.mcas.di.scope.ActivityScope;
import com.kaiwukj.android.mcas.http.imageloader.ImageLoader;
import com.kaiwukj.android.mcas.integration.AppManager;
import com.kaiwukj.android.mcas.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class ChatPresenter extends BasePresenter<ChatContract.Model, ChatContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ChatPresenter(ChatContract.Model model, ChatContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
