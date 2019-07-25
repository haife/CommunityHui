package com.kaiwukj.android.communityhui.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.http.api.service.CircleService
import com.kaiwukj.android.communityhui.mvp.http.api.service.MineService
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseRootResult
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult
import com.kaiwukj.android.communityhui.mvp.http.entity.request.OrderListRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.OrderListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import com.kaiwukj.android.mcas.integration.IRepositoryManager
import com.kaiwukj.android.mcas.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject


@ActivityScope
class MineModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), MineContract.Model {


    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }

    override fun requestMineInfoData(): Observable<BaseRootResult<MineUserInfoResult>> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MineService::class.java)
                .requestMineInfoData())
                .flatMap { it }
    }

    override fun requestSocialHomePage(request: SocialUserHomePageRequest): Observable<SocialUserHomePageResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService::class.java)
                .requestSocialHomePage(getRequestBody(mGson.toJson(request))))
                .flatMap { it }
    }



    override fun requestMineOrderData(request: OrderListRequest): Observable<OrderListResult> {
        return if (request.statusFlag == "0") {
            request.statusFlag = null
            Observable.just(mRepositoryManager.obtainRetrofitService(MineService::class.java)
                    .getMineOrderData(getRequestBody(mGson.toJson(request))))
                    .flatMap { it }
        } else {
            Observable.just(mRepositoryManager.obtainRetrofitService(MineService::class.java)
                    .getMineOrderData(getRequestBody(mGson.toJson(request))))
                    .flatMap { it }
        }

    }

}
