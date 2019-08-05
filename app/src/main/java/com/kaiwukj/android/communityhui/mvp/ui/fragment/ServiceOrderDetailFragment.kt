package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerMineComponent
import com.kaiwukj.android.communityhui.di.module.MineModule
import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.OrderListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult
import com.kaiwukj.android.communityhui.mvp.presenter.MinePresenter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms
import com.kaiwukj.android.mcas.utils.McaUtils
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import kotlinx.android.synthetic.main.fragment_service_order_detail.*
import kotlinx.android.synthetic.main.include_order_detail_header.*
import kotlinx.android.synthetic.main.include_order_detail_status.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc 服务订单详情
 */
class ServiceOrderDetailFragment : BaseSwipeBackFragment<MinePresenter>(), MineContract.View {
    lateinit var orderData: OrderListResult
    var mServiceTypeId: Int = 0

    companion object {
        const val SERVICE_ORDER_DETAIL_FRAGMENT = "SERVICE_ORDER_DETAIL_FRAGMENT"

        fun newInstance(mOrderDetailBean: OrderListResult?): ServiceOrderDetailFragment {
            val fragment = ServiceOrderDetailFragment()
            if (mOrderDetailBean != null) {
                fragment.orderData = mOrderDetailBean
            }
            return fragment
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerMineComponent
                .builder()
                .appComponent(appComponent)
                .mineModule(MineModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_service_order_detail, container, false))
    }

    override fun initData(savedInstanceState: Bundle?) {
        initClick()
        initLayout()
    }

    private fun initLayout() {
        mServiceTypeId = orderData.statusFlag
        tv_custom_order_number.setRightStr(orderData.orderNo)
        tv_custom_order_subordinate_the_stores.setRightStr(orderData.storeName)
        tv_order_detail_user_name.text = orderData.realName
        tv_order_the_stores_phone.text = orderData.phoneNo
        tv_order_detail_user_grade.text = String.format(getString(R.string.home_format_order_grade), orderData.avgScore)
        tv_order_detail_user_tags.text = String.format(getString(R.string.home_format_order_detail_message), orderData.worktime, 35, orderData.nativePlace)
        context?.let { GlideArms.with(it).load(Api.IMG_URL + orderData.avatar).circleCrop().into(iv_order_detail_head) }
        if (McaUtils.isEmpty(orderData.interviewTime)) {
            tv_custom_order_interview_time.setRightStr(getString(R.string.interview_time_wait_sure))
        } else {
            tv_custom_order_interview_time.setRightStr(orderData.interviewTime)
        }

        tv_custom_order_service_type.setRightStr(orderData.serviceTypeName)
        tv_custom_order_service_days.setRightStr(orderData.serviceLength.toString() + "/" + orderData.serviceTypeUnit)
        tv_custom_order_service_days.setRightStr("${orderData.serviceLength} / ${orderData.serviceTypeUnit}")
        tv_custom_order_service_order_time.setRightStr(orderData.createTime)
        tv_order_detail_remark.text = orderData.description
        tv_custom_order_detail_store_address.setRightStr(orderData.serviceAddress)
        when (mServiceTypeId) {
            1 -> {
                iv_order_detail_status_appoint.isChecked = true
                qbtn_order_detail_cancel_order.visibility = View.VISIBLE
            }
            2 -> {
                //面试中
                qbtn_order_detail_cancel_order.visibility = View.VISIBLE
                iv_order_detail_status_appoint.isChecked = true
                cb_order_detail_status_interview.isChecked = true
            }
            3 -> {
                //签约中
                setDrawableTopStatus(R.drawable.selector_order_detail_appoint,cb_order_detail_status_interview)
                setDrawableTopStatus(R.drawable.selector_order_detail_next_step_checked,cb_order_detail_status_pay)
                cb_order_detail_status_pay.isChecked = true
            }
            4 -> {
                //服务中
                iv_order_detail_status_serving.isChecked = true
                setDrawableTopStatus(R.drawable.selector_order_detail_appoint,cb_order_detail_status_interview)
                setDrawableTopStatus(R.drawable.selector_order_detail_appoint,cb_order_detail_status_pay)
                setDrawableTopStatus(R.drawable.selector_order_detail_next_step_checked,iv_order_detail_status_serving)
            }
            5 -> {
                //服务完成
                qbtn_order_detail_bottom.visibility = View.VISIBLE
                setDrawableTopStatus(R.drawable.selector_order_detail_appoint,cb_order_detail_status_interview)
                setDrawableTopStatus(R.drawable.selector_order_detail_appoint,cb_order_detail_status_pay)
                setDrawableTopStatus(R.drawable.selector_order_detail_next_step_checked,iv_order_detail_status_serving)
            }
            0 -> {
                //已取消
            }
        }
    }


    private fun initClick() {
        //评价订单
        qbtn_order_detail_bottom.setOnClickListener {
            start(EvaluateServiceFragment.newInstance(orderData))
        }
        //取消
        qbtn_order_detail_cancel_order.setOnClickListener {
            mPresenter?.requestCancelMineOrderData(orderData.orderId)
        }
    }

    fun setDrawableTopStatus(drawableRes: Int, checkId: CheckBox) {
        val drawable = ContextCompat.getDrawable(context!!, drawableRes)
        drawable?.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        checkId.setCompoundDrawables(null, drawable, null, null);
    }

    override fun onGetMineInfo(result: MineUserInfoResult) {

    }

    override fun onGetOtherHomePageData(result: SocialUserHomePageResult) {
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        //订单取消成功
        qbtn_order_detail_cancel_order.visibility = View.GONE
        val dialog: QMUITipDialog = QMUITipDialog.Builder(context).setTipWord(getString(R.string.order_cancel_success)).create()
        dialog.setTitle(message)
        dialog.show()
        Handler().postDelayed({ dialog.dismiss() }, 800)
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun killMyself() {

    }

    override fun onGetOrderList(result: OrderListResult) {
    }

    override fun post(runnable: Runnable?) {
    }
}
