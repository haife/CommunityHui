package com.kaiwukj.android.communityhui.di.module


import com.kaiwukj.android.communityhui.mvp.contract.LoginContract
import com.kaiwukj.android.communityhui.mvp.model.LoginModel
import com.kaiwukj.android.mcas.di.scope.ActivityScope
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides




/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc
 */
@Module
class LoginModule(private val view: LoginContract.View) {
    @ActivityScope
    @Provides
    fun provideLoginView(): LoginContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideLoginModel(model: LoginModel): LoginContract.Model {
        return model
    }

    @ActivityScope
    @Provides
    fun provideRxPermission(): RxPermissions {
        return RxPermissions(view.getActivity())
    }
}
