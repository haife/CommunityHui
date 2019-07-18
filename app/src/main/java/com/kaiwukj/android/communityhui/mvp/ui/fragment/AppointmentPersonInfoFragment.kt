package com.kaiwukj.android.communityhui.mvp.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerAppointmentComponent
import com.kaiwukj.android.communityhui.di.module.AppointmentModule
import com.kaiwukj.android.communityhui.mvp.contract.AppointmentContract
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.communityhui.mvp.presenter.AppointmentPresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.StoreListAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.fragment_appointment_person_information.*

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc
 */
class AppointmentPersonInfoFragment : BaseSwipeBackFragment<AppointmentPresenter>(), AppointmentContract.View {

    lateinit var mStoreListAdapter: StoreListAdapter


    companion object {
        fun newInstance(): AppointmentPersonInfoFragment {
            val fragment = AppointmentPersonInfoFragment()
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_appointment_person_information, container, false))
    }

    override fun initData(savedInstanceState: Bundle?) {
        initTopBar()
        val list = arrayListOf<StoreListResult>()
        for (i in 0..4) {
            list.add(StoreListResult())
        }
        rv_appointment_user_comment.layoutManager = LinearLayoutManager(context!!)
        mStoreListAdapter = StoreListAdapter(list, R.layout.recycle_item_user_comment_layout, context!!)
        rv_appointment_user_comment.adapter = mStoreListAdapter
        val footLoadView = LayoutInflater.from(context!!).inflate(R.layout.footer_comment_load_more_layout, null)
        mStoreListAdapter.addFooterView(footLoadView)

        //立即预约 需要传递哪种服务类型
        qbtn_appointment_right_now.setOnClickListener {
            start(AppointmentDemandFragment.newInstance())
        }
    }


    private fun initTopBar() {
        qtb_appointment_person_info.setTitle(getString(R.string.appointment_staff_info_detail))
        qtb_appointment_person_info.addLeftBackImageButton().setOnClickListener { killMyself() }

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun post(runnable: Runnable?) {
    }

    override fun killMyself() {
        activity?.onBackPressed()
    }
}
