package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaiwukj.android.communityhui.R.layout
import com.kaiwukj.android.communityhui.R.string
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
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
class HouseKeepFragment : BaseSwipeBackFragment<HouseKeepPresenter>(), HouseKeepContract.View {


    companion object {
        const val MOON_WOMAN_INDEX = 0
        const val CARER_INDEX = 1
        const val RAISE_INDEX = 2
        const val PROLACTIN_DIVISION_INDEX = 3

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
        return attachToSwipeBack(inflater.inflate(layout.fragment_house_keep_service, container, false))
    }

    override fun initData(savedInstanceState: Bundle?) {
        initTopBar()
        initClick()
    }

    private fun initClick() {
        rl_house_keeping_moon_woman.setOnClickListener {
            start(HouseKeepListFragment.newInstance(MOON_WOMAN_INDEX))
        }
        rl_house_keeping_carer.setOnClickListener {
            start(HouseKeepListFragment.newInstance(CARER_INDEX))
        }
        rl_house_keeping_raise.setOnClickListener {
            start(HouseKeepListFragment.newInstance(RAISE_INDEX))
        }
        rl_house_keeping_prolactin_division.setOnClickListener {
            start(HouseKeepListFragment.newInstance(RAISE_INDEX))
        }
    }

    private fun initTopBar() {
        qtb_house_keeping.addLeftBackImageButton().setOnClickListener { killMyself() }
        qtb_house_keeping.setTitle(getString(string.house_keeping_title_str))
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
        activity?.finish()
    }


    override fun post(runnable: Runnable?) {
    }


}
