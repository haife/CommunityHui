package com.kaiwukj.android.communityhui.mvp.presenter

import android.app.Application
import com.kaiwukj.android.communityhui.mvp.contract.AppointmentContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import com.kaiwukj.android.mcas.http.imageloader.ImageLoader
import com.kaiwukj.android.mcas.integration.AppManager
import com.kaiwukj.android.mcas.mvp.BasePresenter
import com.kaiwukj.android.mcas.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
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
class AppointmentPresenter
@Inject
constructor(model: AppointmentContract.Model, rootView: AppointmentContract.View) :
        BasePresenter<AppointmentContract.Model, AppointmentContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    /**
     * 选择阿姨
     */
    fun requestSelectStaffDetail(request: Int) {
        mModel.requestSelectStaffDetail(request)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<StaffListResult>(mErrorHandler) {
                    override fun onNext(data: StaffListResult) {
                        if (data.code == Api.RequestSuccess) {
                        } else {

                        }
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
