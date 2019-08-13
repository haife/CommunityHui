package com.kaiwukj.android.communityhui.mvp.http.api.service;

import com.kaiwukj.android.communityhui.mvp.http.entity.result.VersionUpdateResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/2
 * @desc 可以存放通用的一些 API
 */
public interface CommonService {
    /**
     * 版本更新
     */
    @POST("/sys/appVersion/query")
    Observable<VersionUpdateResult> requestVersionUpdate(@Body RequestBody request);
}
