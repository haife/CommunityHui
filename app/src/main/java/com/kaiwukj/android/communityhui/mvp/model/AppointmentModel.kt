package com.kaiwukj.android.communityhui.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.kaiwukj.android.communityhui.mvp.contract.AppointmentContract
import com.kaiwukj.android.communityhui.mvp.http.api.service.HomeService
import com.kaiwukj.android.communityhui.mvp.http.api.service.MineService
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.StaffInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.request.AppointmentDemandRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CollectionRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StaffCommentRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffCommentResult
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import com.kaiwukj.android.mcas.integration.IRepositoryManager
import com.kaiwukj.android.mcas.mvp.BaseModel
import io.reactivex.Observable
import javax.inject.Inject


@ActivityScope
class AppointmentModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), AppointmentContract.Model {

    @Inject
    lateinit var mGson: Gson;
    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }

    /**
     * 技工详情
     * @param userId Int
     * @return Observable<StaffInfoResult>
     */
    override fun requestSelectStaffDetail(userId: Int): Observable<StaffInfoResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(HomeService::class.java)
                .requestSelectStaffDetail(userId.toString()))
                .flatMap { it }
    }

    /**
     * 用户对技工的评论
     * @param userId Int
     * @return Observable<StaffCommentResult>
     */
    override fun requestUserComment(userId: Int, page: Int): Observable<StaffCommentResult> {
        var req = StaffCommentRequest()
        req.storeemployeeId = userId
        req.pageNum = page
        return Observable.just(mRepositoryManager.obtainRetrofitService(HomeService::class.java)
                .requestSelectStaffComment(getRequestBody(mGson.toJson(req))))
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
     * 提交订单
     * @param request AppointmentDemandRequest
     * @return Observable<BaseStatusResult>
     */
    override fun requestAppointmentDate(request: AppointmentDemandRequest): Observable<BaseStatusResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(HomeService::class.java)
                .requestAppointmentOrder(getRequestBody(mGson.toJson(request))))
                .flatMap { it }
    }

    /**
     * 收藏阿姨
     * @param request CollectionRequest
     * @return Observable<BaseStatusResult>
     */
    override fun requestAddCollection(request: CollectionRequest): Observable<BaseStatusResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MineService::class.java)
                .addCollectionRequest(getRequestBody(mGson.toJson(request))))
                .flatMap { it }
    }

    /**
     * 取消收藏
     * @param id Int
     * @return Observable<BaseStatusResult>
     */
    override fun requestMoveCollection(request: CollectionRequest): Observable<BaseStatusResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(MineService::class.java)
                .moveCollectionRequest(getRequestBody(mGson.toJson(request))))
                .flatMap { it }
    }



}
