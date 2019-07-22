package com.kaiwukj.android.communityhui.mvp.http.api.service;

import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
    @GET("/app/sc/uaddress/getAddressList")
    Observable<MyAddressResult> requestMyAddress(@Path("userId") int userId);


}
