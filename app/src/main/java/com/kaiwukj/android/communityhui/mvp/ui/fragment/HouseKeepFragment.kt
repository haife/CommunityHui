package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment
import com.kaiwukj.android.communityhui.di.component.DaggerHouseKeepComponent
import com.kaiwukj.android.communityhui.di.module.HouseKeepModule
import com.kaiwukj.android.communityhui.mvp.contract.HouseKeepContract
import com.kaiwukj.android.communityhui.mvp.presenter.HouseKeepPresenter
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.fragment_house_keep_service.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 家政服务
 */
class HouseKeepFragment : BaseSupportFragment<HouseKeepPresenter>(), HouseKeepContract.View {
    override fun post(runnable: Runnable?) {
    }

    companion object {
        fun newInstance(): HouseKeepFragment {
            val fragment = HouseKeepFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHouseKeepComponent
                .builder()
                .appComponent(appComponent)
                .houseKeepModule(HouseKeepModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_house_keep_service, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        qtb_house_keeping.setTitle(getString(R.string.house_keeping_title_str))
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

    }
}
