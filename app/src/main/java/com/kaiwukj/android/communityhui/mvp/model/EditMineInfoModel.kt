package com.kaiwukj.android.communityhui.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.http.api.service.CircleService
import com.kaiwukj.android.communityhui.mvp.http.api.service.MineService
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseQITokenResult
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult
import com.kaiwukj.android.communityhui.mvp.http.entity.request.MineCollectionRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineCollectionResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
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
 * @time 2019/7/17
 * @desc
 */
@ActivityScope
class EditMineInfoModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), EditMineInfoContract.Model {


    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application


    override fun updateMineInfoData(request: MineUserInfoResult): Observable<BaseStatusResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MineService::class.java)
                .updateMineInfoData(getRequestBody(mGson.toJson(request))))
                .flatMap { it }
    }

    override fun requestMyCollection(request: MineCollectionRequest): Observable<MineCollectionResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MineService::class.java)
                .getMineCollectionData(getRequestBody(mGson.toJson(request))))
                .flatMap { it }
    }


    /**
     * 我的地址
     * @param userId Int
     * @return Observable<MyAddressResult>
     */
    override fun requestMyAddress(): Observable<MyAddressResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MineService::class.java)
                .requestMyAddress())
                .flatMap { it }
    }


    /**
     * 编辑地址
     * @param userId Int
     * @return Observable<MyAddressResult>
     */
    override fun upDateMyAddress(request: MyAddressResult): Observable<BaseStatusResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MineService::class.java)
                .upDateMyAddress(getRequestBody(mGson.toJson(request))))
                .flatMap { it }
    }

    /**
     * 新增地址
     * @param userId Int
     * @return Observable<MyAddressResult>
     */
    override fun addMyAddress(request: MyAddressResult): Observable<BaseStatusResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MineService::class.java)
                .addMineAddress(getRequestBody(mGson.toJson(request))))
                .flatMap { it }
    }

    override fun deleteMyAddress(addressId: Int): Observable<BaseStatusResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MineService::class.java)
                .deleteMineAddress(addressId))
                .flatMap { it }
    }


    override fun requestQIToken(): Observable<BaseQITokenResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(CircleService::class.java)
                .requestQiToken(getRequestBody(mGson.toJson(Any()))))
                .flatMap { it }
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
