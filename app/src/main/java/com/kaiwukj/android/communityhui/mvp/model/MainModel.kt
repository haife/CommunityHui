package com.kaiwukj.android.communityhui.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.kaiwukj.android.communityhui.mvp.contract.MainContract
import com.kaiwukj.android.communityhui.mvp.http.api.service.CommonService
import com.kaiwukj.android.communityhui.mvp.http.entity.request.VersionUpdateRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.VersionUpdateResult
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import com.kaiwukj.android.mcas.integration.IRepositoryManager
import com.kaiwukj.android.mcas.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc
 */
@ActivityScope
class MainModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), MainContract.Model {


    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;


    override fun requestVersionUpdate(request: VersionUpdateRequest): Observable<VersionUpdateResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(CommonService::class.java)
                .requestVersionUpdate(getRequestBody(mGson.toJson(request))))
                .flatMap {
                    it
                }
    }


    override fun onDestroy() {
        super.onDestroy();
    }


}
