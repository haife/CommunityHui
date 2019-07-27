package com.kaiwukj.android.communityhui.mvp.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerAppointmentComponent
import com.kaiwukj.android.communityhui.di.module.AppointmentModule
import com.kaiwukj.android.communityhui.mvp.contract.AppointmentContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.StaffInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffCommentResult
import com.kaiwukj.android.communityhui.mvp.presenter.AppointmentPresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.AppointmentCommentAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms
import kotlinx.android.synthetic.main.fragment_appointment_person_information.*
import kotlinx.android.synthetic.main.include_person_information_header.*
import kotlinx.android.synthetic.main.include_person_others_info.*
import kotlinx.android.synthetic.main.include_person_qualification_info.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc
 */
class AppointmentPersonInfoFragment : BaseSwipeBackFragment<AppointmentPresenter>(), AppointmentContract.View {

    private var shopId: Int? = null
    private var userId: Int? = null
    private var mServiceTypeId: Int? = null
    private lateinit var mStoreListAdapter: AppointmentCommentAdapter
    private var commentList = ArrayList<StaffCommentResult>()

    companion object {
        const val APPOINTMENT_PERSON_INFO_FRAGMENT = "APPOINTMENT_PERSON_INFO_FRAGMENT"
        fun newInstance(userId: Int?, serviceTypeId: Int?): AppointmentPersonInfoFragment {
            val fragment = AppointmentPersonInfoFragment()
            fragment.userId = userId
            fragment.mServiceTypeId = serviceTypeId
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
        userId?.let { mPresenter?.requestSelectStaffDetail(it) }


        rv_appointment_user_comment.layoutManager = LinearLayoutManager(context!!)
        mStoreListAdapter = AppointmentCommentAdapter(R.layout.recycle_item_staff_comment_layout, commentList as MutableList<StaffCommentResult>?, context!!)
        rv_appointment_user_comment.adapter = mStoreListAdapter
        val footLoadView = LayoutInflater.from(context!!).inflate(R.layout.footer_comment_load_more_layout, null)
        mStoreListAdapter.addFooterView(footLoadView)

        //立即预约 需要传递哪种服务类型
        qbtn_appointment_right_now.setOnClickListener {
            start(AppointmentDemandFragment.newInstance(userId, mServiceTypeId, shopId))
        }

        //所属门店
        rl_person_info_store.setOnClickListener {
            start(StoreSortListFragment.newInstance(shopId))
        }
    }

    /**
     *
     * @param result StaffInfoResult
     */
    override fun onGetStaffDetailInfo(result: StaffInfoResult) {
        val result: StaffInfoResult = result.result
        shopId = result.hmstoreId
        GlideArms.with(context!!).load(Api.IMG_URL + result.avatar).circleCrop().centerCrop().into(riv_person_info_photo)
        tv_riv_person_info_name.text = result.realName
        tv_riv_person_info_message.text = String.format(getString(R.string.home_format_staff_message), result.worktime, result.age, result.nativePlace)
        tv_riv_person_info_gender.text = String.format(getString(R.string.home_format_staff_info_gender), result.avgScore)
        tv_riv_person_info_below_belong_shops.text = result.storeName
        tv_person_info_work_time.text = String.format(getString(R.string.home_format_staff_info_work_time), result.worktime)
        tv_person_info_shop_comment.text = String.format(getString(R.string.home_format_staff_info_work_time), result.evaluate)
        //资历列表
        if (result.empCommentList.isNotEmpty()) {
            commentList.addAll(result.empCommentList)
             //ll_person_qualification_info_container.setLabels()
        }
        //服务价格
        if (result.empTypeList.isNotEmpty()) {
            for (index in result.empTypeList) {
                val lp = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
                lp.setMargins(0, 15, 0, 15)
                var itemView = LayoutInflater.from(context).inflate(R.layout.include_person_infor_staff_service_price, null)
                val nameTv = itemView.findViewById<TextView>(R.id.tv_staff_service_price_name)
                val priceTv = itemView.findViewById<TextView>(R.id.tv_person_info_service_price)
                nameTv.text = index.serviceType ?: ""
                priceTv.text = String.format(getString(R.string.person_info_service_price_format), index.servicePrice, index.serviceUnit)
                itemView.layoutParams = lp
                ll_peron_other_info_service_price.addView(itemView)
            }
        }

        if (result.empTagList.isNotEmpty()) {
            ll_person_tags_info_container.setLabels(result.empTagList)
        }

    }

    private fun initTopBar() {
        qtb_appointment_person_info.setTitle(getString(com.kaiwukj.android.communityhui.R.string.appointment_staff_info_detail))
        qtb_appointment_person_info.addLeftBackImageButton().setOnClickListener { killMyself() }

    }

    /**
     * 获取到评论信息
     * @param result StaffCommentResult
     */
    override fun onGetStaffCommentInfo(commentResult: StaffCommentResult) {
        commentList.addAll(commentResult.result)
        mStoreListAdapter.notifyDataSetChanged()
    }


    override fun onGetMyAddressList(result: MyAddressResult) {
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
