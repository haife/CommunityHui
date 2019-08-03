package com.kaiwukj.android.communityhui.mvp.presenter

import android.app.Application
import com.kaiwukj.android.communityhui.mvp.contract.AppointmentContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.StaffInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.request.AppointmentDemandRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CollectionRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffCommentResult
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
    fun requestSelectStaffDetail(userId: Int) {
        mModel.requestSelectStaffDetail(userId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    mRootView.showLoading()
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { mRootView.hideLoading() }
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<StaffInfoResult>(mErrorHandler) {
                    override fun onNext(data: StaffInfoResult) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.onGetStaffDetailInfo(data)
                            requestUserComment(userId, page = 1)
                        } else {

                        }
                    }
                })
    }

    /**
     * 阿姨评论
     */
    fun requestUserComment(userId: Int, page: Int) {
        mModel.requestUserComment(userId, page)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ErrorHandleSubscriber<StaffCommentResult>(mErrorHandler) {
                    override fun onNext(data: StaffCommentResult) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.onGetStaffCommentInfo(data.result as ArrayList<StaffCommentResult>)
                        }
                    }

                    override fun onComplete() {
                    }
                })
    }

    /**
     * 获取地址
     */
    fun requestMyAddress() {
        mModel.requestMyAddress()
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ErrorHandleSubscriber<MyAddressResult>(mErrorHandler) {
                    override fun onNext(data: MyAddressResult) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.onGetMyAddressList(data)
                        } else {

                        }
                    }
                })
    }

    /**
     * 提交订单
     */
    fun requestAppointmentDate(request: AppointmentDemandRequest) {
        mModel.requestAppointmentDate(request)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ErrorHandleSubscriber<BaseStatusResult>(mErrorHandler) {
                    override fun onNext(data: BaseStatusResult) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.showMessage("1")
                        } else {
                            mRootView.showMessage("0")
                        }
                    }
                })
    }


    /**
     * 添加收藏阿姨
     * @param request StoreListRequest
     */
    fun requestAddCollection(userId: Int) {
        val request = CollectionRequest()
        request.type = 1
        request.favoriteId = userId
        mModel.requestAddCollection(request)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<BaseStatusResult>(mErrorHandler) {
                    override fun onNext(data: BaseStatusResult) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.showMessage("已收藏")
                        }
                    }
                })
    }

    /**
     * 删除收藏技工
     * @param request StoreListRequest
     */
    fun requestMoveCollection(userId: Int) {
        val request = CollectionRequest()
        request.type = 1
        request.favoriteId = userId
        mModel.requestMoveCollection(request)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<BaseStatusResult>(mErrorHandler) {
                    override fun onNext(data: BaseStatusResult) {
                        if (data.code == Api.RequestSuccess) {
                            mRootView.showMessage("已取消")
                        }
                    }
                })
    }


    override fun onDestroy() {
        super.onDestroy();
    }
}
