package com.kaiwukj.android.communityhui.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route

import com.kaiwukj.android.communityhui.di.module.MainModule
import com.kaiwukj.android.communityhui.mvp.contract.MainContract
import com.kaiwukj.android.communityhui.mvp.presenter.MainPresenter

import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportActivity
import com.kaiwukj.android.communityhui.app.constant.MainRouterUrl
import com.kaiwukj.android.communityhui.di.component.DaggerMainComponent
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc Main Screen
 */
@Route(path = MainRouterUrl)
class MainActivity : BaseSupportActivity<MainPresenter>(), MainContract.View {

    override fun post(runnable: Runnable?) {
    }


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(MainModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }


    override fun initData(savedInstanceState: Bundle?) {
        bnve_main_bottom_navigation.enableAnimation(false)
        bnve_main_bottom_navigation.enableShiftingMode(false)
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
