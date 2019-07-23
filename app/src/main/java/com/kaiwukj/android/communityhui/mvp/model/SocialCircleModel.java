package com.kaiwukj.android.communityhui.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.api.service.CircleService;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CircleHomeRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHotResult;
import com.kaiwukj.android.mcas.di.scope.ActivityScope;
import com.kaiwukj.android.mcas.integration.IRepositoryManager;
import com.kaiwukj.android.mcas.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.RequestBody;

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

    private RequestBody getRequestBody(String postJson) {
        return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), postJson);
    }


    @Override
    public Observable<CircleHomeResult> requestCircleHomeList(CircleHomeRequest request, boolean refresh) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).requestCircleHomeData(getRequestBody(mGson.toJson(request))))
                .flatMap((Function<Observable<CircleHomeResult>, ObservableSource<CircleHomeResult>>) observable -> observable);
    }

    @Override
    public Observable<CircleCardResult> requestCircleCardList() {
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).requestCircleCardData())
                .flatMap((Function<Observable<CircleCardResult>, ObservableSource<CircleCardResult>>) observable -> observable);

    }

    @Override
    public Observable<CircleHotResult> requestCircleHotList() {
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).requestCircleHotData())
                .flatMap((Function<Observable<CircleHotResult>, ObservableSource<CircleHotResult>>) observable -> observable);
    }
}