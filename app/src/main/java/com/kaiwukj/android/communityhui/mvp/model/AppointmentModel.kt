package com.kaiwukj.android.communityhui.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.kaiwukj.android.communityhui.mvp.contract.AppointmentContract
import com.kaiwukj.android.communityhui.mvp.http.api.service.HomeService
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult
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


    override fun requestSelectStaffDetail(userId: Int): Observable<StaffListResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(HomeService::class.java)
                .requestSelectStaffDetail(userId.toString()))
                .flatMap { it }
    }


}
