package com.kaiwukj.android.communityhui.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.kaiwukj.android.communityhui.mvp.contract.StoreContract
import com.kaiwukj.android.communityhui.mvp.http.api.cache.CommonCache
import com.kaiwukj.android.communityhui.mvp.http.api.service.HomeService
import com.kaiwukj.android.communityhui.mvp.http.api.service.MineService
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseRootResult
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CollectionRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreListRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreDetailResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import com.kaiwukj.android.mcas.integration.IRepositoryManager
import com.kaiwukj.android.mcas.mvp.BaseModel
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictDynamicKey
import io.rx_cache2.Reply
import okhttp3.RequestBody
import javax.inject.Inject


@ActivityScope
class StoreModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), StoreContract.Model {


    private val allStoreList = "AllStoreList"
    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;


    override fun requestAllStoreRecommend(recommendFlg: StoreListRequest): Observable<StoreListResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(HomeService::class.java)
                .requestStoreRecommend(getRequestBody(mGson.toJson(recommendFlg))))
                .flatMap {
                    mRepositoryManager.obtainCacheService(CommonCache::class.java).getHomeStoreCache(it, DynamicKey(allStoreList), EvictDynamicKey(false))
                            .map { list: Reply<StoreListResult> ->
                                list.data
                            }
                }
    }

    /**
     * 获取门店详情
     * @param id Int
     * @return Observable<BaseRootResult<StoreDetailResult>>
     */
    override fun requestStoreDetail(id: Int): Observable<BaseRootResult<StoreDetailResult>> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(HomeService::class.java)
                .requestStoreDetail(id))
                .flatMap {
                    it
                }
    }

    /**
     * 取消收藏
     * @param id Int
     * @return Observable<BaseStatusResult>
     */
    override fun requestMoveCollection(id: Int): Observable<BaseStatusResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MineService::class.java)
                .moveCollectionRequest(id))
                .flatMap { it }
    }

    /**
     * 添加收藏
     * @param request CollectionRequest
     * @return Observable<BaseStatusResult>
     */
    override fun requestAddCollection(request: CollectionRequest): Observable<BaseStatusResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MineService::class.java)
                .addCollectionRequest(getRequestBody(mGson.toJson(request))))
                .flatMap { it }
    }


    private fun getRequestBody(postJson: String): RequestBody {
        return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), postJson)
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
