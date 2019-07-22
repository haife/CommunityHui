package com.kaiwukj.android.communityhui.mvp.ui.activity


import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportActivity
import com.kaiwukj.android.communityhui.app.constant.AppointmentUrl
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.di.component.DaggerAppointmentComponent
import com.kaiwukj.android.communityhui.di.module.AppointmentModule
import com.kaiwukj.android.communityhui.mvp.contract.AppointmentContract
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.StaffInfoResult
import com.kaiwukj.android.communityhui.mvp.presenter.AppointmentPresenter
import com.kaiwukj.android.communityhui.mvp.ui.fragment.AppointmentPersonInfoFragment
import com.kaiwukj.android.mcas.di.component.AppComponent

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 预定流程
 */
@Route(path = AppointmentUrl)
class AppointmentActivity : BaseSupportActivity<AppointmentPresenter>(), AppointmentContract.View {
    @Autowired(name = ExtraCons.EXTRA_KEY_HOUSE_KEEP)
    @JvmField
    var mTargetStr: String? = null

    @Autowired(name = ExtraCons.EXTRA_KEY_STAFF_USER_ID)
    @JvmField
    var userId: Int? = null

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerAppointmentComponent
                .builder()
                .appComponent(appComponent)
                .appointmentModule(AppointmentModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_appointment
    }


    override fun initData(savedInstanceState: Bundle?) {
        when (mTargetStr) {
            AppointmentPersonInfoFragment.APPOINTMENT_PERSON_INFO_FRAGMENT -> {
                loadRootFragment(R.id.fl_appointment_container, AppointmentPersonInfoFragment.newInstance(userId!!))
            }
        }
    }

    override fun onGetStaffDetailInfo(result: StaffInfoResult) {
    }

    override fun post(runnable: Runnable?) {
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
