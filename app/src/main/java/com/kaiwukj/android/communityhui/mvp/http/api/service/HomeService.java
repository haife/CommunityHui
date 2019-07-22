package com.kaiwukj.android.communityhui.mvp.http.api.service;

import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.LoginVerifyCodeResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    @POST("/app/sc/storeService/listStore")
    Observable<StoreListResult> requestStoreRecommend(@Body RequestBody requestBody);

    /*
     * 所有门店
     */
    @POST("/app/sc/storeService/listServiceType")
    Observable<LoginVerifyCodeResult> requestTotalStoreList(@Body RequestBody requestBody);


    /*
     * 阿姨推荐
     */
    @POST("/app/sc/storeService/listEmp")
    Observable<StaffListResult> requestStaffRecommend(@Body RequestBody requestBody);

    /*
     * 选择阿姨
     */
    @POST("/app/sc/storeEmployee/list")
    Observable<StaffListResult> requestSelectStaff(@Body RequestBody requestBody);

    /**
     * 技工详情
     */
    @GET("/app/sc/storeEmployee/info/{storeemployeeId}")
    Observable<StaffListResult> requestSelectStaffDetail(@Path("storeemployeeId") String id);
}
