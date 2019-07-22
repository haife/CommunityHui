package com.kaiwukj.android.communityhui.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.kaiwukj.android.communityhui.mvp.contract.HomeContract
import com.kaiwukj.android.communityhui.mvp.http.api.cache.CommonCache
import com.kaiwukj.android.communityhui.mvp.http.api.service.HomeService
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreListRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.mcas.di.scope.FragmentScope
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
 * @time 2019/7/15
 * @desc
 */
@FragmentScope
class HomeModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), HomeContract.Model {


    private val homeDynamicKey = "HomeHotService"
    private val homeStoreKey = "HomeStoreList"
    private val homeStaffKey = "HomeStaffList"
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application


    override fun requestServiceList(): Observable<HomeServiceEntity> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(HomeService::class.java)
                .requestHomeServiceList())
                .flatMap {
                    mRepositoryManager.obtainCacheService(CommonCache::class.java).getHomeServiceCache(it, DynamicKey(homeDynamicKey), EvictDynamicKey(true))
                            .map { list: Reply<HomeServiceEntity> ->
                                list.data
                            }
                }
    }


    override fun requestStoreRecommend(recommendFlg: StoreListRequest): Observable<StoreListResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(HomeService::class.java)
                .requestStoreRecommend(getRequestBody(mGson.toJson(recommendFlg))))
                .flatMap {
                    mRepositoryManager.obtainCacheService(CommonCache::class.java).getHomeStoreCache(it, DynamicKey(homeStoreKey), EvictDynamicKey(false))
                            .map { list: Reply<StoreListResult> ->
                                list.data
                            }
                }
    }


    override fun requestStaffRecommend(recommendFlg: StoreListRequest): Observable<StaffListResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(HomeService::class.java)
                .requestStaffRecommend(getRequestBody(mGson.toJson(recommendFlg))))
                .flatMap {
                    mRepositoryManager.obtainCacheService(CommonCache::class.java).getHomeStaffCache(it, DynamicKey(homeStaffKey), EvictDynamicKey(false))
                            .map { list: Reply<StaffListResult> ->
                                list.data
                            }
                }
    }

    private fun getRequestBody(postJson: String): RequestBody {
        return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), postJson)
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
