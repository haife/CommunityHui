package com.kaiwukj.android.communityhui.mvp.http.api.service;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-22
 */
public interface MineService {
    /**
     * 技工详情
     */
    @GET("/app/sc/uaddress/list")
    Observable<MyAddressResult> requestMyAddress();

    /**
     * 添加收藏
     */
    @POST("/app/sc/favorite/addfavorite")
    Observable<BaseStatusResult> addCollectionRequest(@Body RequestBody body);

    /**
     * 取消收藏
     */
    @GET("/app/sc/favorite/deletefavorite/{id}")
    Observable<BaseStatusResult> moveCollectionRequest(@Path("id") int id);

    /**
     * 用户信息
     */
    @GET("/app/sc/user/info")
    Observable<MineUserInfoResult> requestMineInfoData();

    /**
     * 用户信息
     */
    @POST("/app/sc/user/update")
    Observable<BaseStatusResult> updatetMineInfoData(@Body RequestBody body);
}
