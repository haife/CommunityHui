package com.kaiwukj.android.communityhui.mvp.presenter

import android.app.Application
import com.kaiwukj.android.communityhui.app.constant.SPParam
import com.kaiwukj.android.communityhui.mvp.contract.LoginContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.request.LoginRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.LoginResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.LoginVerifyCodeResult
import com.kaiwukj.android.communityhui.utils.SPUtils
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
 * @time 2019/7/15
 * @desc
 */
@ActivityScope
class LoginPresenter
@Inject
constructor(model: LoginContract.Model, rootView: LoginContract.View) :
        BasePresenter<LoginContract.Model, LoginContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mAppManager: AppManager

    /**
     * 获取手机验证码
     * @param phoneNumber String
     */
    fun requestVerifyCode(phoneNumber: String) {
        mModel.requestVerifyCode(phoneNumber)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<LoginVerifyCodeResult>(mErrorHandler) {
                    override fun onNext(t: LoginVerifyCodeResult) {
                        if (t.code == Api.RequestSuccess) {
                            mRootView.sendVerifyCodeComplete()
                        } else {
                            mRootView.showMessage(t.desc)
                        }
                    }
                })
    }

    /**
     * 登录
     * @param loginRequest LoginRequest
     */
    fun requestLogin(loginRequest: LoginRequest) {
        mModel.requestLogin(loginRequest)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ErrorHandleSubscriber<LoginResult>(mErrorHandler) {
                    override fun onNext(t: LoginResult) {
                        if (t.code == Api.RequestSuccess) {
                            SPUtils.getInstance().put(SPParam.SP_LOGIN_PHONE, loginRequest.phoneNo)
                            SPUtils.getInstance().put(SPParam.SP_LOGIN_TOKEN, t.result)
                            mRootView.loginSuccess()

                        } else {
                            mRootView.showMessage(t.desc)
                        }

                    }

                })
    }


    override fun onDestroy() {
        super.onDestroy();
    }
}
