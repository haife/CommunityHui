package com.kaiwukj.android.communityhui.mvp.contract

import androidx.fragment.app.FragmentActivity
import com.kaiwukj.android.communityhui.mvp.http.entity.request.LoginRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.LoginResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.LoginVerifyCodeResult
import com.kaiwukj.android.mcas.mvp.IModel
import com.kaiwukj.android.mcas.mvp.IView
import io.reactivex.Observable


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc
 */
interface LoginContract {

    interface View : IView {
        fun getActivity(): FragmentActivity
        fun clickListener()
        fun loginSuccess()
        fun sendVerifyCodeComplete()
    }

    interface Model : IModel {
        fun requestLogin(request: LoginRequest): Observable<LoginResult>
        fun requestVerifyCode(phone: String): Observable<LoginVerifyCodeResult>
    }
}
