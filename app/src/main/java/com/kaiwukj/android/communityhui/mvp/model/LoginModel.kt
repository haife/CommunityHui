package com.kaiwukj.android.communityhui.mvp.model

import com.google.gson.Gson
import com.kaiwukj.android.communityhui.mvp.contract.LoginContract
import com.kaiwukj.android.communityhui.mvp.http.api.service.LoginService
import com.kaiwukj.android.communityhui.mvp.http.entity.request.LoginRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.LoginResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.LoginVerifyCodeResult
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
 * @time 2019/7/15
 * @desc
 */
@ActivityScope
class LoginModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), LoginContract.Model {

    @Inject
    lateinit var mGson: Gson;


    override fun requestVerifyCode(phone: String): Observable<LoginVerifyCodeResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(LoginService::class.java)
                .requestLoginVerifyCode(phone))
                .flatMap { it }


    }

    override fun requestLogin(request: LoginRequest): Observable<LoginResult> {
        return Observable.just(mRepositoryManager.obtainRetrofitService(LoginService::class.java)
                .requestLogin(getRequestBody(mGson.toJson(request))))
                .flatMap { it }
    }



}
