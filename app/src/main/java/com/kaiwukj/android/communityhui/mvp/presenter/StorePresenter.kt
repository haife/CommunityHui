package com.kaiwukj.android.communityhui.mvp.presenter

import android.app.Application
import com.kaiwukj.android.communityhui.mvp.contract.StoreContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreListRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.communityhui.mvp.ui.adapter.StoreListAdapter
import com.kaiwukj.android.mcas.di.scope.ActivityScope
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
 * @desc 门店模块
 */
@ActivityScope
class StorePresenter
@Inject
constructor(model: StoreContract.Model, rootView: StoreContract.View) :
        BasePresenter<StoreContract.Model, StoreContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mAppManager: AppManager
    @Inject
    lateinit var listData: ArrayList<StoreListResult>
    @Inject
    lateinit var mStoreListAdapter: StoreListAdapter

    /**
     * 推荐门店
     * @param request StoreListRequest
     * 是否推荐 0默认，1推荐 不传为默认所有
     */
    fun requestAllStoreRecommend() {
        mModel.requestAllStoreRecommend(StoreListRequest(null))
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<StoreListResult>(mErrorHandler) {
                    override fun onNext(data: StoreListResult) {
                        if (data.code == Api.RequestSuccess) {
                            //所有门店
                            listData.addAll(data.result.list)
                            mStoreListAdapter.notifyDataSetChanged()
                        } else {

                        }
                    }
                })
    }


    /**
     * 门店列表
     * @param request StoreListRequest
     * 是否推荐 0默认，1推荐 不传为默认所有
     * 传递门店id则查询该门店的阿姨
     * 传递ServiceType则查询该门店的阿姨
     */
    fun requestStoreStaffList(request: StoreListRequest) {
        mModel.requestAllStoreRecommend(request)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<StoreListResult>(mErrorHandler) {
                    override fun onNext(data: StoreListResult) {
                        if (data.code == Api.RequestSuccess) {
                           mRootView.onGetStoreRecommend(data)
                        } else {

                        }
                    }
                })
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
