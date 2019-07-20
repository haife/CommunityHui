package com.kaiwukj.android.communityhui.mvp.http.api.service;

import com.kaiwukj.android.communityhui.mvp.http.entity.result.LoginResult;
import com.kaiwukj.android.communityhui.mvp.http.entity.result.LoginVerifyCodeResult;

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
 * @desc
 */
public interface LoginService {
    /*
     * 获取验证码
     */
    @GET("/app/sc/login/{phoneNo}")
    Observable<LoginVerifyCodeResult> requestLoginVerifyCode(@Path("phoneNo") String phoneNo);

    /*
     * 登录
     */
    @POST("/app/sc/login")
    Observable<LoginResult> requestLogin(@Body RequestBody requestBody);


}
