package com.kaiwukj.android.communityhui.mvp.contract

import androidx.fragment.app.FragmentActivity
import com.kaiwukj.android.mcas.mvp.IModel
import com.kaiwukj.android.mcas.mvp.IView


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
    }

    interface Model : IModel {
//        fun requestPhoneCode(request: LoginCodeRequest): Observable<BaseResponse<LoginResultBean>>
//        fun requestLogin(request: LoginRequest): Observable<BaseResponse<LoginResultBean>>

    }
}
