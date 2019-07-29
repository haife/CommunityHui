package com.kaiwukj.android.communityhui.mvp.ui.activity


import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackActivity
import com.kaiwukj.android.communityhui.app.constant.AppointmentUrl
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.di.component.DaggerAppointmentComponent
import com.kaiwukj.android.communityhui.di.module.AppointmentModule
import com.kaiwukj.android.communityhui.mvp.contract.AppointmentContract
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.StaffInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffCommentResult
import com.kaiwukj.android.communityhui.mvp.presenter.AppointmentPresenter
import com.kaiwukj.android.communityhui.mvp.ui.fragment.AppointmentPersonInfoFragment
import com.kaiwukj.android.mcas.di.component.AppComponent
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 预定流程
 */
@Route(path = AppointmentUrl)
class AppointmentActivity : BaseSwipeBackActivity<AppointmentPresenter>(), AppointmentContract.View {


    @Autowired(name = ExtraCons.EXTRA_KEY_HOUSE_KEEP)
    @JvmField
    var mTargetStr: String? = null

    @Autowired(name = ExtraCons.EXTRA_KEY_STAFF_USER_ID)
    @JvmField
    var userId: String? = null

    @Autowired(name = ExtraCons.EXTRA_KEY_STAFF_SETVIE_TYPE_ID)
    @JvmField
    var mServiceTypeId: String? = null

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerAppointmentComponent
                .builder()
                .appComponent(appComponent)
                .appointmentModule(AppointmentModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        ARouter.getInstance().inject(this@AppointmentActivity)
        return R.layout.activity_appointment
    }


    override fun initData(savedInstanceState: Bundle?) {
        when (mTargetStr) {
            AppointmentPersonInfoFragment.APPOINTMENT_PERSON_INFO_FRAGMENT -> {
                userId?.let { loadRootFragment(R.id.fl_appointment_container, AppointmentPersonInfoFragment.newInstance(it.toInt(), mServiceTypeId?.toInt())) }
            }
        }
    }


    override fun onGetStaffDetailInfo(result: StaffInfoResult) {
    }

    override fun onGetStaffCommentInfo(result: StaffCommentResult) {
    }


    override fun post(runnable: Runnable?) {
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onGetMyAddressList(result: MyAddressResult) {
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return DefaultHorizontalAnimator()
    }

    override fun showMessage(message: String) {
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun killMyself() {
        onBackPressedSupport()
    }


}
