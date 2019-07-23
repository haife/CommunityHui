package com.kaiwukj.android.communityhui.mvp.presenter

import android.app.Application
import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.base.BaseStatusResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import com.kaiwukj.android.mcas.http.imageloader.ImageLoader
import com.kaiwukj.android.mcas.integration.AppManager
import com.kaiwukj.android.mcas.mvp.BasePresenter
import com.kaiwukj.android.mcas.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import me.jessyan.rxerrorhandler.handler.RetryWithDelay
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
class MinePresenter
@Inject
constructor(model: MineContract.Model, rootView: MineContract.View) :
        BasePresenter<MineContract.Model, MineContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    /**
     * 获取手机验证码
     * @param phoneNumber String
     */
    fun getMineInfoData() {
        mModel.requestMineInfoData()
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<MineUserInfoResult>(mErrorHandler) {
                    override fun onNext(t: MineUserInfoResult) {
                        if (t.code == Api.RequestSuccess) {
                            mRootView.onGetMineInfo(result = t.result)
                            requestSocialHomePage(t.result.id.toString())
                        } else {
                            mRootView.showMessage(t.desc)
                        }
                    }
                })
    }

    /**
     * 帖子 回复信息等
     * @param userId String
     */
    fun requestSocialHomePage(userId: String) {
        val request = SocialUserHomePageRequest()
        request.id = userId
        mModel.requestSocialHomePage(request)
                .compose(RxLifecycleUtils.bindToLifecycle<SocialUserHomePageResult>(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(RetryWithDelay(3, 2))
                .subscribe(object : ErrorHandleSubscriber<SocialUserHomePageResult>(mErrorHandler) {
                    override fun onNext(result: SocialUserHomePageResult) {
                        if (result.code == Api.RequestSuccess) {
                            mRootView.onGetOtherHomePageData(result.result)
                        }
                    }
                })
    }


    /**
     * 帖子 回复信息等
     * @param userId String
     */
    fun updateMineInfoData(userInfo: MineUserInfoResult) {
        mModel.updateMineInfoData(userInfo)
                .compose(RxLifecycleUtils.bindToLifecycle<BaseStatusResult>(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(RetryWithDelay(3, 2))
                .subscribe(object : ErrorHandleSubscriber<BaseStatusResult>(mErrorHandler) {
                    override fun onNext(result: BaseStatusResult) {
                        if (result.code == Api.RequestSuccess) {

                        }
                    }
                })
    }


    override fun onDestroy() {
        super.onDestroy();
    }
}
