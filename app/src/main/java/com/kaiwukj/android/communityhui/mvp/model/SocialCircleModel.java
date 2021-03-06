package com.kaiwukj.android.communityhui.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.kaiwukj.android.communityhui.mvp.contract.SocialCircleContract;
import com.kaiwukj.android.communityhui.mvp.http.api.service.CircleService;
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseQITokenResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CircleAttentionOthersRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CircleCardDetailRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CircleHomeRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CirclePersonFansRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CirclePersonMinePageRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CirclePersonPageRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CommentOtherRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.PostCardRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardCommentResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardDetailResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHotResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyFansListResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.PersonPageCardCommentResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult;
import com.kaiwukj.android.mcas.di.scope.ActivityScope;
import com.kaiwukj.android.mcas.integration.IRepositoryManager;
import com.kaiwukj.android.mcas.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

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


    @Override
    public Observable<CircleHomeResult> requestCircleHomeList(CircleHomeRequest request) {
        if (request.getType() == 0) {
            return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).requestCircleHomeData(getRequestBody(mGson.toJson(new Object()))))
                    .flatMap((Function<Observable<CircleHomeResult>, ObservableSource<CircleHomeResult>>) observable -> observable);
        } else {
            return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).requestCircleHomeData(getRequestBody(mGson.toJson(request))))
                    .flatMap((Function<Observable<CircleHomeResult>, ObservableSource<CircleHomeResult>>) observable -> observable);
        }

    }

    @Override
    public Observable<CircleCardResult> requestCircleCardList() {
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).requestCircleCardData())
                .flatMap((Function<Observable<CircleCardResult>, ObservableSource<CircleCardResult>>) observable -> observable);

    }

    @Override
    public Observable<CircleHotResult> requestCircleHotList() {
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).requestCircleHotData(getRequestBody(mGson.toJson(new Object()))))
                .flatMap((Function<Observable<CircleHotResult>, ObservableSource<CircleHotResult>>) observable -> observable);
    }

    @Override
    public Observable<BaseStatusResult> postSocialCard(PostCardRequest request) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).postCardRequest(getRequestBody(mGson.toJson(request))))
                .flatMap((Function<Observable<BaseStatusResult>, ObservableSource<BaseStatusResult>>) observable -> observable);

    }

    @Override
    public Observable<CircleCardDetailResult> requestSocialCardDetail(int id) {
        CircleCardDetailRequest request = new CircleCardDetailRequest(id);
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).requestCardDetail(getRequestBody(mGson.toJson(request))))
                .flatMap((Function<Observable<CircleCardDetailResult>, ObservableSource<CircleCardDetailResult>>) observable -> observable);
    }

    @Override
    public Observable<CircleCardCommentResult> requestCommentList(int id, int page) {
        CircleCardDetailRequest request = new CircleCardDetailRequest(id, page);
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).queryCommentList(getRequestBody(mGson.toJson(request))))
                .flatMap((Function<Observable<CircleCardCommentResult>, ObservableSource<CircleCardCommentResult>>) observable -> observable);
    }


    @Override
    public Observable<BaseStatusResult> requestCommentOther(CommentOtherRequest request) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class)
                .requestCommentMine(getRequestBody(mGson.toJson(request))))
                .flatMap((Function<Observable<BaseStatusResult>, ObservableSource<BaseStatusResult>>) observable -> observable);
    }


    @Override
    public Observable<SocialUserHomePageResult> requestSocialHomePage(SocialUserHomePageRequest request) {
        if ("0".equals(request.getId())) {
            request.setId(null);
        }
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class)
                .requestSocialHomePage(getRequestBody(mGson.toJson(request))))
                .flatMap((Function<Observable<SocialUserHomePageResult>, ObservableSource<SocialUserHomePageResult>>) observable -> observable);
    }

    @Override
    public Observable<BaseQITokenResult> requestQIToken() {
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).requestQiToken(getRequestBody(mGson.toJson(new Object()))))
                .flatMap((Function<Observable<BaseQITokenResult>, ObservableSource<BaseQITokenResult>>) observable -> observable);
    }

    @Override
    public Observable<CircleHomeResult> queryCircleMyNoteList(CirclePersonPageRequest request) {
        if ((request.getOtherUserId()) == 0) {
            CirclePersonMinePageRequest request1 = new CirclePersonMinePageRequest();
            request1.setPageNum(request.getPageNum());
            return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).queryMyNoteList(getRequestBody(mGson.toJson(request1))))
                    .flatMap((Function<Observable<CircleHomeResult>, ObservableSource<CircleHomeResult>>) observable -> observable);
        } else {
            return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).queryMyNoteList(getRequestBody(mGson.toJson(request))))
                    .flatMap((Function<Observable<CircleHomeResult>, ObservableSource<CircleHomeResult>>) observable -> observable);
        }

    }

    @Override
    public Observable<PersonPageCardCommentResult> queryReplyList(CirclePersonPageRequest request) {
        if ((request.getOtherUserId()) == 0) {
            CirclePersonMinePageRequest request1 = new CirclePersonMinePageRequest();
            request1.setPageNum(request.getPageNum());
            return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).queryReplyList(getRequestBody(mGson.toJson(request1))))
                    .flatMap((Function<Observable<PersonPageCardCommentResult>, ObservableSource<PersonPageCardCommentResult>>) observable -> observable);
        } else {
            return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).queryReplyList(getRequestBody(mGson.toJson(request))))
                    .flatMap((Function<Observable<PersonPageCardCommentResult>, ObservableSource<PersonPageCardCommentResult>>) observable -> observable);
        }
    }

    @Override
    public Observable<MyFansListResult> queryFansList(CirclePersonFansRequest request) {
        if ((request.getOtherUserId()) == 0) {
            CirclePersonMinePageRequest request1 = new CirclePersonMinePageRequest();
            request1.setPageNum(request.getPageNum());
            return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).queryFansList(getRequestBody(mGson.toJson(request1))))
                    .flatMap((Function<Observable<MyFansListResult>, ObservableSource<MyFansListResult>>) observable -> observable);
        } else {
            return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).queryFansList(getRequestBody(mGson.toJson(request))))
                    .flatMap((Function<Observable<MyFansListResult>, ObservableSource<MyFansListResult>>) observable -> observable);
        }
    }

    @Override
    public Observable<MyFansListResult> queryMyAttentionList(CirclePersonFansRequest request) {
        if ((request.getOtherUserId()) == 0) {
            CirclePersonMinePageRequest request1 = new CirclePersonMinePageRequest();
            request1.setPageNum(request.getPageNum());
            return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).queryMyAttentionList(getRequestBody(mGson.toJson(request1))))
                    .flatMap((Function<Observable<MyFansListResult>, ObservableSource<MyFansListResult>>) observable -> observable);
        } else {
            return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).queryMyAttentionList(getRequestBody(mGson.toJson(request))))
                    .flatMap((Function<Observable<MyFansListResult>, ObservableSource<MyFansListResult>>) observable -> observable);
        }
    }

    @Override
    public Observable<BaseStatusResult> requestAttentionOther(CircleAttentionOthersRequest request) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).requestAttentionOther(getRequestBody(mGson.toJson(request))))
                .flatMap((Function<Observable<BaseStatusResult>, ObservableSource<BaseStatusResult>>) observable -> observable);
    }
    @Override
    public Observable<BaseStatusResult> removeAttentionOther(CircleAttentionOthersRequest request) {
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService.class).removeAttentionOther(getRequestBody(mGson.toJson(request))))
                .flatMap((Function<Observable<BaseStatusResult>, ObservableSource<BaseStatusResult>>) observable -> observable);
    }
}