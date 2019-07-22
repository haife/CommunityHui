package com.kaiwukj.android.communityhui.mvp.http.api.service;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.StaffInfoResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffCommentResult;
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
    Observable<StaffInfoResult> requestSelectStaffDetail(@Path("storeemployeeId") String id);

    /**
     * 对技工的评价
     */
    @POST("/app/sc/storeEmployee/empComment")
    Observable<StaffCommentResult> requestSelectStaffComment(@Body RequestBody requestBody);

    /*
     * 提交预约订单
     */
    @POST("/app/sc/storeEmployee/list")
    Observable<BaseStatusResult> requestAppointmentOrder(@Body RequestBody requestBody);
}
