package com.kaiwukj.android.communityhui.mvp.ui.activity


import android.content.Intent
import android.os.Bundle
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportActivity
import com.kaiwukj.android.communityhui.di.component.DaggerHouseKeepComponent
import com.kaiwukj.android.communityhui.di.module.HouseKeepModule
import com.kaiwukj.android.communityhui.mvp.contract.HouseKeepContract
import com.kaiwukj.android.communityhui.mvp.presenter.HouseKeepPresenter
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.activity_house_keep.*

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 家政模块
 */
class HouseKeepActivity : BaseSupportActivity<HouseKeepPresenter>(), HouseKeepContract.View {
    override fun post(runnable: Runnable?) {
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerHouseKeepComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .houseKeepModule(HouseKeepModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_house_keep
    }


    override fun initData(savedInstanceState: Bundle?) {


    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
    }



    override fun killMyself() {
        finish()
    }
}
