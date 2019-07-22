package com.kaiwukj.android.communityhui.mvp.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerAppointmentComponent
import com.kaiwukj.android.communityhui.di.module.AppointmentModule
import com.kaiwukj.android.communityhui.mvp.contract.AppointmentContract
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.StaffInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.request.AppointmentDemandRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffCommentResult
import com.kaiwukj.android.communityhui.mvp.presenter.AppointmentPresenter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import kotlinx.android.synthetic.main.fragment_appointment_demand.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc
 */
class AppointmentDemandFragment : BaseSwipeBackFragment<AppointmentPresenter>(), AppointmentContract.View {
    private var hintDialog: QMUITipDialog? = null
    private var mServiceTypeId: Int? = null
    private var mAddressId: Int? = null
    private var mUserId: Int? = null
    private var mShopID: Int? = null
    private var request: AppointmentDemandRequest = AppointmentDemandRequest()
    override fun post(runnable: Runnable?) {
    }

    companion object {
        fun newInstance(userId: Int?, serviceTypeId: Int?, shopID: Int?): AppointmentDemandFragment {
            val fragment = AppointmentDemandFragment()
            fragment.mServiceTypeId = serviceTypeId
            fragment.mUserId = userId
            fragment.mShopID = shopID
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_appointment_demand, container, false))
    }

    override fun initData(savedInstanceState: Bundle?) {
        mShopID?.let { request.hmstoreId = it }
        mUserId?.let {
            request.storeemployeeId = it
            mPresenter?.requestMyAddress(it)
        }
        mServiceTypeId?.let {
            request.serviceTypeId = it
        }
        mAddressId?.let {
            request.addressId = it
        }
        initTopBar()
        initPickDate()

        //提交订单
        qbtn_submit_order.setOnClickListener lable@{
            if (mAddressId == null) {
                showHint(getString(R.string.appointment_demand_address_hint))
                Handler().postDelayed({
                    hideLoading()
                }, 800)
                return@lable
            }
            if (et_appoint_demand_service_days.text.toString().isEmpty()) {
                showHint(getString(R.string.appointment_demand_days_hint))
                Handler().postDelayed({
                    hideLoading()
                }, 800)
                return@lable
            } else {
                request.serviceLength = et_appoint_demand_service_days.text.toString().toInt()
            }

            if (request.planBeginTime.toString().isEmpty()) {
                showHint(getString(R.string.appointment_demand_days_hint))
                Handler().postDelayed({
                    hideLoading()
                }, 800)
                return@lable
            }
            if (et_appoint_demand_other_content.text.isNotEmpty()) {
                request.description = et_appoint_demand_service_days.text.toString()
            }
            showLoading()
            Handler().postDelayed({
                mPresenter?.requestAppointmentDate(request)
            }, 1000)

        }


    }

    /**
     * 时间选择器初始化
     */
    private fun initPickDate() {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR))
        calendar.roll(Calendar.DAY_OF_YEAR, -1)
        tv_tv_appoint_demand_service_times.setOnClickListener {
            //时间选择器
            TimePickerBuilder(context, OnTimeSelectListener
            { date, v ->
                tv_tv_appoint_demand_service_times.text = formatter.format(date)
                request.planBeginTime = date
            }).setCancelColor(ContextCompat.getColor(context!!, R.color.common_text_slight_color))
                    .isCyclic(false)
                    .setRangDate(Calendar.getInstance(), calendar)//起始终止年月日设定
                    .setSubmitColor(ContextCompat.getColor(context!!, R.color.app_color_theme))
                    .setLineSpacingMultiplier(1.8f)
                    .build().show()
        }
    }

    private fun initTopBar() {
        qtb_appointment_demand.setTitle(getString(com.kaiwukj.android.communityhui.R.string.appointment_demand_input))
        qtb_appointment_demand.addLeftBackImageButton().setOnClickListener { killMyself() }
    }

    /**
     * 获取到门店地址
     * @param result MyAddressResult
     */
    override fun onGetMyAddressList(result: MyAddressResult) {
        //判断有误地址
        tv_add_address_hint.visibility = if (result.result.isNotEmpty()) View.GONE else View.VISIBLE
        rl_appointment_address_container.visibility = if (result.result.isNotEmpty()) View.VISIBLE else View.GONE
        if (result.result.isNotEmpty()) {
            mAddressId = result.result[0].communityId
            tv_appoint_demand_address.text = result.result[0].address
            tv_address_user_info.text = String.format(getString(R.string.mine_address_info), result.result[0].name, result.result[0].phone)
        }
    }

    override fun onGetStaffDetailInfo(result: StaffInfoResult) {
    }

    override fun onGetStaffCommentInfo(result: StaffCommentResult) {
    }

    override fun showLoading() {
        hintDialog = QMUITipDialog.Builder(context).setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING).setTipWord(getString(R.string.setting_submitting)).create()
        hintDialog?.show()
    }

    override fun hideLoading() {
        hintDialog?.dismiss()
    }

    private fun showHint(hint: String) {
        hintDialog = QMUITipDialog.Builder(context).setTipWord(hint).create()
        hintDialog?.show()
    }

    override fun showMessage(message: String) {
        start(AppointmentResultFragment.newInstance())
        hideLoading()
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun killMyself() {
        activity?.onBackPressed()
    }
}
