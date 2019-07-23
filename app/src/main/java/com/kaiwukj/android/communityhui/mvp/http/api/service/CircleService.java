package com.kaiwukj.android.communityhui.mvp.http.api.service;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardDetailResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHotResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/23
 * @desc $desc
 */
public interface CircleService {
    /*
     * 圈子首页
     */
    @POST("/app/sc/unote/listNote")
    Observable<CircleHomeResult> requestCircleHomeData(@Body RequestBody requestBody);

    /*
     * 圈子首页主题帖
     */
    @GET("/app/sc/unote/listType")
    Observable<CircleCardResult> requestCircleCardData();

    /*
     * 圈子首页主题帖
     */
    @GET("/app/sc/unote/listRecommendNote")
    Observable<CircleHotResult> requestCircleHotData();

    /**
     * 发帖
     */
    @POST("/app/sc/unote/save")
    Observable<BaseStatusResult> postCardRequest(@Body RequestBody body);

    /**
     * 帖子详情
     */
    @POST("/app/sc/unote/save")
    Observable<CircleCardDetailResult> requestCardDetail(@Body RequestBody body);

    /**
     * 发表评论或者回复别人
     */
    @POST("/app/sc/unoteComment/save")
    Observable<BaseStatusResult> requestCommentMine(@Body RequestBody body);

    /**
     * 社区查看主页
     * 不传递id默认查看当前用户的帖子主页个人信息，
     * 传递id查看该用户id的帖子主页个人信息
     */
    @POST("/app/sc/uinfo/queryMyInfo")
    Observable<SocialUserHomePageResult> requestSocialHomePage(@Body RequestBody body);
}
