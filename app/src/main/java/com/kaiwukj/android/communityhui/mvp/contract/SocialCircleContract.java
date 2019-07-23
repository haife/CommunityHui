package com.kaiwukj.android.communityhui.mvp.contract;

import android.content.Context;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CircleHomeRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CommentOtherRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.PostCardRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardDetailResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHotResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult;
import com.kaiwukj.android.mcas.mvp.IModel;
import com.kaiwukj.android.mcas.mvp.IView;

import androidx.annotation.Nullable;
import io.reactivex.Observable;


/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/19
 * @desc
 */
public interface SocialCircleContract {

    interface View extends IView {
        Context getCtx();

        void finishRefresh();

        void finishLoadMore(@Nullable boolean noData);

        void onGetCardDetailResult(CircleCardDetailResult result);

        void onGetOtherHomePageData(SocialUserHomePageResult result);
    }

    interface Model extends IModel {

        Observable<CircleHomeResult> requestCircleHomeList(CircleHomeRequest request, boolean isRefresh);

        Observable<CircleCardResult> requestCircleCardList();

        Observable<CircleHotResult> requestCircleHotList();

        Observable<BaseStatusResult> postSocialCard(PostCardRequest request);


        Observable<CircleCardDetailResult> requestSocialCardDetail(int id);

        //发表评论或者回复他人
        Observable<BaseStatusResult> requestCommentOther(CommentOtherRequest request);

        Observable<SocialUserHomePageResult> requestSocialHomePage(SocialUserHomePageRequest request);
    }
}
