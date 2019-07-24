package com.kaiwukj.android.communityhui.mvp.http.api.service;

import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.request.MineCollectionResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.OrderListResult;

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
     * 我的地址
     */
    @GET("/app/sc/uaddress/list")
    Observable<MyAddressResult> requestMyAddress();

    /**
     * 修改我的地址
     */
    @POST("/app/sc/uaddress/update")
    Observable<BaseStatusResult> upDateMyAddress(@Body RequestBody body);

    /**
     * 新增
     */
    @POST("/app/sc/uaddress/save")
    Observable<BaseStatusResult> addMineAddress(@Body RequestBody body);


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
     * 更新用户信息
     */
    @POST("/app/sc/user/update")
    Observable<BaseStatusResult> updateMineInfoData(@Body RequestBody body);

    /**
     * 订单列表
     */
    @POST("/app/sc/userOrder/myOrder")
    Observable<OrderListResult> getMineOrderData(@Body RequestBody body);

    /**
     * 收藏
     */
    @POST("/app/sc/favorite/favourByTypePage")
    Observable<MineCollectionResult> getMineCollectionData(@Body RequestBody body);
}
