package com.kaiwukj.android.communityhui.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.kaiwukj.android.communityhui.mvp.contract.HouseKeepContract
import com.kaiwukj.android.communityhui.mvp.http.api.cache.CommonCache
import com.kaiwukj.android.communityhui.mvp.http.api.service.HomeService
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreListRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreStaffRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import com.kaiwukj.android.mcas.integration.IRepositoryManager
import com.kaiwukj.android.mcas.mvp.BaseModel
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictDynamicKey
import io.rx_cache2.Reply
import okhttp3.RequestBody
import javax.inject.Inject


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc
 */
@ActivityScope
class HouseKeepModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), HouseKeepContract.Model {


    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;
    private val homeDynamicKey = "HomeHotService"

    override fun requestServiceList(): Observable<HomeServiceEntity> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(HomeService::class.java)
                .requestHomeServiceList())
                .flatMap {
                    mRepositoryManager.obtainCacheService(CommonCache::class.java)
                            .getHomeServiceCache(it, DynamicKey(homeDynamicKey), EvictDynamicKey(true))
                            .map { list: Reply<HomeServiceEntity> ->
                                list.data
                            }
                }
    }


    /**
     * 选择阿姨
     * @param request StoreStaffRequest
     * @return Observable<StaffListResult>
     */
    override fun requestSelectStaff(request: StoreStaffRequest): Observable<StaffListResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(HomeService::class.java)
                .requestSelectStaff(getRequestBody(mGson.toJson(request))))
                .flatMap { it }


    }

    /**
     * 查看某个门店下技工列表
     * @param request StoreListRequest
     * @return Observable<StaffListResult>
     */
    override fun requestShopsStaffList(request: StoreListRequest): Observable<StaffListResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(HomeService::class.java)
                .requestSelectStaff(getRequestBody(mGson.toJson(request))))
                .flatMap { it }
    }


    private fun getRequestBody(postJson: String): RequestBody {
        return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), postJson)
    }


    override fun onDestroy() {
        super.onDestroy();
    }
}
