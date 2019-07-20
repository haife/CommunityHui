package com.kaiwukj.android.communityhui.mvp.http.api.service;

import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.LoginVerifyCodeResult;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/20
 * @desc 首页模块
 */
public interface HomeService {

    /*
     * 首页家政服务列表
     */
    @GET("/app/sc/storeService/listServiceType")
    Observable<HomeServiceEntity> requestHomeServiceList();

    /*
     * 门店推荐
     */
    @GET("POST /app/sc/storeService/listStore")
    Observable<LoginVerifyCodeResult> requestStoreRecommend();

    /*
     * 所有门店
     */
    @GET("/app/sc/storeService/listServiceType")
    Observable<LoginVerifyCodeResult> requestTotalStoreList();


    /*
     * 阿姨推荐
     */
    @GET("POST /app/sc/storeService/listStore")
    Observable<LoginVerifyCodeResult> requestStaffRecommend();

}
