package com.kaiwukj.android.communityhui.mvp.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment
import com.kaiwukj.android.communityhui.app.constant.MainRouterUrl
import com.kaiwukj.android.communityhui.di.component.DaggerAppointmentComponent
import com.kaiwukj.android.communityhui.di.module.AppointmentModule
import com.kaiwukj.android.communityhui.mvp.contract.AppointmentContract
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.StaffInfoResult
import com.kaiwukj.android.communityhui.mvp.presenter.AppointmentPresenter
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.fragment_appointment_success.*

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 预约结果
 */
class AppointmentResultFragment : BaseSupportFragment<AppointmentPresenter>(), AppointmentContract.View {
    override fun onGetStaffDetailInfo(result: StaffInfoResult) {
    }

    override fun post(runnable: Runnable?) {
    }

    companion object {
        fun newInstance(): AppointmentResultFragment {
            val fragment = AppointmentResultFragment()
            return fragment
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerAppointmentComponent
                .builder()
                .appComponent(appComponent)
                .appointmentModule(AppointmentModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_appointment_success, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        qbtn_appoint_back_home.setOnClickListener {
            ARouter.getInstance().build(MainRouterUrl).navigation(context)
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

    }
}
