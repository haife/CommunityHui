package com.kaiwukj.android.communityhui.mvp.presenter

import android.app.Application
import com.kaiwukj.android.communityhui.mvp.contract.StoreContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseRootResult
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CollectionRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreListRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreDetailResult
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
    fun requestAllStoreRecommend(page: Int) {
        mModel.requestAllStoreRecommend(StoreListRequest(page = page))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    if (page != 1)
                        mRootView.showLoading()
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    if (page != 1)
                        mRootView.hideLoading()
                }
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(object : ErrorHandleSubscriber<StoreListResult>(mErrorHandler) {
                    override fun onNext(data: StoreListResult) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.onGetStoreRecommend(data)
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

    /**
     * 门店详情
     * @param request StoreListRequest
     */
    fun requestStoreDetail(shopId: Int) {
        mModel.requestStoreDetail(shopId)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<BaseRootResult<StoreDetailResult>>(mErrorHandler) {
                    override fun onNext(data: BaseRootResult<StoreDetailResult>) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.onGetStoreDetail(data.result)
                        } else {

                        }
                    }
                })
    }


    /**
     * 添加收藏
     * @param request StoreListRequest
     */
    fun requestAddCollection(request: CollectionRequest) {
        mModel.requestAddCollection(request)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<BaseStatusResult>(mErrorHandler) {
                    override fun onNext(data: BaseStatusResult) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.showMessage(data.desc)
                        } else {
                        }
                    }
                })
    }

    /**
     * 添加收藏
     * @param request StoreListRequest
     */
    fun requestMoveCollection(id: Int) {
        mModel.requestMoveCollection(id)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<BaseStatusResult>(mErrorHandler) {
                    override fun onNext(data: BaseStatusResult) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.showMessage(data.desc)
                        } else {
                        }
                    }
                })
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
