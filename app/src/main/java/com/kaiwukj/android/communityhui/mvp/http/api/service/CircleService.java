package com.kaiwukj.android.communityhui.mvp.http.api.service;

import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleCardResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHomeResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.CircleHotResult;

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
}
