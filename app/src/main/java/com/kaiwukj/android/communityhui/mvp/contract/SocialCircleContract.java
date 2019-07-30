package com.kaiwukj.android.communityhui.mvp.contract;

import android.content.Context;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseQITokenResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CircleAttentionOthersRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CircleHomeRequest;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CirclePersonFansRequest;
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

        Observable<CircleHomeResult> requestCircleHomeList(CircleHomeRequest request);

        Observable<CircleCardResult> requestCircleCardList();

        Observable<CircleHotResult> requestCircleHotList();

        Observable<BaseStatusResult> postSocialCard(PostCardRequest request);

        Observable<CircleCardDetailResult> requestSocialCardDetail(int id);

        Observable<CircleCardCommentResult> requestCommentList(int id, int page);

        //发表评论或者回复他人
        Observable<BaseStatusResult> requestCommentOther(CommentOtherRequest request);

        Observable<SocialUserHomePageResult> requestSocialHomePage(SocialUserHomePageRequest request);

        //获取七牛云Token
        Observable<BaseQITokenResult> requestQIToken();

        //获取Ta发布的帖子
        Observable<CircleHomeResult> queryCircleMyNoteList(CirclePersonPageRequest request); //获取Ta发布的帖子

        Observable<PersonPageCardCommentResult> queryReplyList(CirclePersonPageRequest request);

        //获取TA的粉丝
        Observable<MyFansListResult> queryFansList(CirclePersonFansRequest request);

        //获取Ta的关注
        Observable<MyFansListResult> queryMyAttentionList(CirclePersonFansRequest request);

        //关注别人
        Observable<BaseStatusResult> requestAttentionOther(CircleAttentionOthersRequest request);
    }
}
