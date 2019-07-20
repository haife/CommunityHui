package com.kaiwukj.android.communityhui.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.kaiwukj.android.communityhui.mvp.contract.HomeContract
import com.kaiwukj.android.communityhui.mvp.http.api.cache.CommonCache
import com.kaiwukj.android.communityhui.mvp.http.api.service.HomeService
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.mcas.di.scope.FragmentScope
import com.kaiwukj.android.mcas.integration.IRepositoryManager
import com.kaiwukj.android.mcas.mvp.BaseModel
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictDynamicKey
import io.rx_cache2.Reply
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

    val homeDynamicKey = "HomeHotService"
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

//    override fun requestStoreRecommend(phone: String): Observable<LoginVerifyCodeResult> {
//        return
//    }
//
//    override fun requestStaffRecommend(phone: String): Observable<LoginVerifyCodeResult> {
//    }


    override fun onDestroy() {
        super.onDestroy();
    }
}
