package com.kaiwukj.android.communityhui.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter


import com.kaiwukj.android.communityhui.di.component.DaggerLoginComponent
import com.kaiwukj.android.communityhui.di.module.LoginModule
import com.kaiwukj.android.communityhui.mvp.contract.LoginContract
import com.kaiwukj.android.communityhui.mvp.presenter.LoginPresenter

import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.constant.MainRouterUrl
import com.kaiwukj.android.mcas.base.BaseActivity
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.activity_login.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc  Login Screen
 */
class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(LoginModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }


    override fun initData(savedInstanceState: Bundle?) {
        qbtn_login.setOnClickListener {
            ARouter.getInstance().build(MainRouterUrl).navigation(this)
        }
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
    }

    override fun launchActivity(intent: Intent) {

    }

    override fun killMyself() {
        finish()
    }
}
