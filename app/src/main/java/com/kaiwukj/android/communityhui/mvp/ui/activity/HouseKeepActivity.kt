package com.kaiwukj.android.communityhui.mvp.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackActivity
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.HouseKeepUrl
import com.kaiwukj.android.communityhui.di.component.DaggerHouseKeepComponent
import com.kaiwukj.android.communityhui.di.module.HouseKeepModule
import com.kaiwukj.android.communityhui.mvp.contract.HouseKeepContract
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.presenter.HouseKeepPresenter
import com.kaiwukj.android.communityhui.mvp.ui.fragment.AppointmentPersonInfoFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.HomeFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.HouseKeepFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.HouseStaffListFragment
import com.kaiwukj.android.mcas.di.component.AppComponent
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 家政模块
 */

@Route(path = HouseKeepUrl)
class HouseKeepActivity : BaseSwipeBackActivity<HouseKeepPresenter>(), HouseKeepContract.View {

    @Autowired(name = ExtraCons.EXTRA_KEY_HOUSE_KEEP)
    @JvmField
    var mTargetStr: String? = null
    @Autowired(name = ExtraCons.EXTRA_KEY_HOUSE_KEEP_ENTITY)
    @JvmField
    var mServiceEntity: List<HomeServiceEntity>? = null


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerHouseKeepComponent
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
        when (mTargetStr) {
            //从首页跳转而来
            HomeFragment.EXTRA_KEY_HOME_FRAGMENT_URL -> loadRootFragment(R.id.fl_house_keeping_container, HouseKeepFragment.newInstance(mServiceEntity))
            //从家政列表跳转而来
            HouseStaffListFragment.EXTRA_KEY_STAFF_LIST_URL -> {
                loadRootFragment(R.id.fl_house_keeping_container, AppointmentPersonInfoFragment.newInstance())
            }
        }

    }


    override fun showLoading() {

    }

    override fun post(runnable: Runnable?) {
    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return DefaultHorizontalAnimator()
    }

}
