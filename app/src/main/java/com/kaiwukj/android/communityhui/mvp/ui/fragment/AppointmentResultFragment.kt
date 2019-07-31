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
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffCommentResult
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
    private var status: Int = 0
    override fun onGetMyAddressList(result: MyAddressResult) {
    }


    companion object {
        fun newInstance(status: Int): AppointmentResultFragment {
            val fragment = AppointmentResultFragment()
            fragment.status = status
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
        return if (status == 1) {
            inflater.inflate(R.layout.fragment_appointment_success, container, false)
        } else {
            inflater.inflate(R.layout.fragment_appointment_failur, container, false)
        }

    }

    override fun initData(savedInstanceState: Bundle?) {

        qbtn_appoint_back_home.setOnClickListener {
            ARouter.getInstance().build(MainRouterUrl).navigation(context)
        }
    }

    override fun onGetStaffCommentInfo(result: ArrayList<StaffCommentResult>) {
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

    }
}
