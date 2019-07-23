package com.kaiwukj.android.communityhui.mvp.presenter

import android.app.Application
import com.kaiwukj.android.communityhui.mvp.contract.MainContract
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import com.kaiwukj.android.mcas.http.imageloader.ImageLoader
import com.kaiwukj.android.mcas.integration.AppManager
import com.kaiwukj.android.mcas.mvp.BasePresenter
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc Main Screen
 */
@ActivityScope
class MainPresenter
@Inject
constructor(model: MainContract.Model, rootView: MainContract.View) :
        BasePresenter<MainContract.Model, MainContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    /**
     * 首页服务列表
     */
    fun requestServiceList() {
//        mModel.getMineInfoData()
//                .subscribeOn(Schedulers.io())
//                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<MineUserInfoResult>(mErrorHandler) {
//                    override fun onNext(data: MineUserInfoResult) {
//                        if (data.code == Api.RequestSuccess) {
//
//                        } else {
//
//                        }
//                    }
//                })
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
