package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.graphics.Typeface
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
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
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

    private var mEntity: List<HomeServiceEntity>? = null
    private val typeFaceMediumBold = Typeface.createFromAsset(context?.assets, "PingFangSC-Medium-Bold.ttf")

    companion object {
        const val MOON_WOMAN_INDEX = 0
        const val CARER_INDEX = 1
        const val RAISE_INDEX = 2
        const val PROLACTIN_DIVISION_INDEX = 3

        fun newInstance(entity: List<HomeServiceEntity>?): HouseKeepFragment {
            val fragment = HouseKeepFragment()
            fragment.mEntity = entity
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
        initUi()
    }

    private fun initUi() {
        if (!mEntity.isNullOrEmpty()) {
            tv_house_keeping_moon_woman.text = mEntity!![0].dicValue
            tv_house_keeping_carer.text = mEntity!![1].dicValue
            tv_house_keeping_rearing.text = mEntity!![2].dicValue
            tv_house_keeping_prolactin_division.text = mEntity!![3].dicValue
        }


        tv_house_keeping_moon_woman.typeface = typeFaceMediumBold
        tv_house_keeping_carer.typeface = typeFaceMediumBold
        tv_house_keeping_rearing.typeface = typeFaceMediumBold
        tv_house_keeping_prolactin_division.typeface = typeFaceMediumBold

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
