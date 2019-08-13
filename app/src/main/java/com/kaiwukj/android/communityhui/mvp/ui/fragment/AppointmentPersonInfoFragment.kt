package com.kaiwukj.android.communityhui.mvp.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
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
import com.kaiwukj.android.mcas.utils.McaUtils
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
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
    private lateinit var mCommentAdapter: AppointmentCommentAdapter
    private var commentList = ArrayList<StaffCommentResult>()
    private var page = 1
    private lateinit var dialog: QMUITipDialog
    private var mResult: StaffInfoResult = StaffInfoResult()
    private val selectItemsStrings = arrayListOf<String>()
    private val selectItems = arrayListOf<StaffInfoResult.EmpTypeListBean>()

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
        mCommentAdapter = AppointmentCommentAdapter(R.layout.recycle_item_staff_comment_layout, commentList, context!!)
        rv_appointment_user_comment.adapter = mCommentAdapter

        //立即预约 需要传递哪种服务类型
        qbtn_appointment_right_now.setOnClickListener {
            if (mServiceTypeId == null || mServiceTypeId == 0) {
                MaterialDialog(context!!).title(R.string.choice_service_type_title).show {
                    listItems(res = null, items = selectItemsStrings) { dialog, index, text ->
                        mServiceTypeId = selectItems[index].serviceTypeId
                        start(AppointmentDemandFragment.newInstance(userId, mServiceTypeId, shopId))
                    }
                }
                return@setOnClickListener
            }


            start(AppointmentDemandFragment.newInstance(userId, mServiceTypeId, shopId))
        }

        //所属门店
        rl_person_info_store.setOnClickListener {
            start(StoreSortListFragment.newInstance(shopId))
        }
        //获取更多评论
        smart_refresh_appoint_user_info.setOnLoadMoreListener {
            page++
            if (page == 2) {
                commentList.clear()
            }
            mPresenter?.requestUserComment(userId!!, page)
        }
    }

    /**
     *
     * @param result StaffInfoResult
     */
    override fun onGetStaffDetailInfo(result: StaffInfoResult) {
        mResult = result.result
        initTobBarClick()
        initLayoutData()
    }

    private fun initLayoutData() {
        shopId = mResult.hmstoreId
        GlideArms.with(context!!).load(Api.IMG_URL + mResult.avatar).circleCrop().centerCrop().into(riv_person_info_photo)
        tv_riv_person_info_name.text = mResult.realName
        tv_riv_person_info_message.text = String.format(getString(R.string.home_format_staff_message), mResult.worktime, mResult.age, mResult.nativePlace)
        tv_riv_person_info_gender.text = String.format(getString(R.string.home_format_staff_info_gender), mResult.avgScore)
        tv_riv_person_info_below_belong_shops.text = mResult.storeName
        tv_person_info_work_time.text = String.format(getString(R.string.home_format_staff_info_work_time), mResult.worktime)
        tv_person_info_shop_comment.text = String.format(getString(R.string.stores_comment), mResult.evaluate)
        if (mResult.imgList.isNotEmpty()) {
            tv_person_qualification.text = getString(R.string.appoint_has_identify)
        } else {
            tv_person_qualification.text = getString(R.string.appoint_no_identify)
        }
        //评论列表
        if (mResult.empCommentList.isNotEmpty()) {
            commentList.addAll(mResult.empCommentList)
            mCommentAdapter.notifyDataSetChanged()
        }
        //服务价格
        if (mResult.empTypeList.isNotEmpty()) {
            for (index in mResult.empTypeList) {
                val lp = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
                lp.setMargins(0, 15, 0, 15)
                var itemView = LayoutInflater.from(context).inflate(R.layout.include_person_infor_staff_service_price, null)
                val nameTv = itemView.findViewById<TextView>(R.id.tv_staff_service_price_name)
                val priceTv = itemView.findViewById<TextView>(R.id.tv_person_info_service_price)
                nameTv.text = index.serviceType ?: ""
                priceTv.text = String.format(getString(R.string.person_info_service_price_format), index.servicePrice, index.serviceUnit)
                itemView.layoutParams = lp
                ll_peron_other_info_service_price.addView(itemView)
                //赋值技工服务类型数组，如果serviceID传递进来为0则需要选择类型
                selectItemsStrings.add(nameTv.text.toString())
                selectItems.add(index)
            }
        }
        if (mResult.empTagList.isNotEmpty()) {
            ll_person_tags_info_container.setLabels(mResult.empTagList)
        }

        rl_appointment_right_now.visibility = View.VISIBLE
    }

    private fun initTobBarClick() {
        qtb_appointment_person_info.removeAllRightViews()
        when (mResult.favoriteFlag) {
            1 -> {
                qtb_appointment_person_info.addRightTextButton(getString(R.string.store_collect_had_desc), R.id.qmui_top_right_btn).setOnClickListener {
                    //收藏服务人员
                    userId?.let {
                        mPresenter?.requestMoveCollection(it)
                    }
                }
            }
            0 -> {
                qtb_appointment_person_info.addRightTextButton(getString(R.string.store_collect_desc), R.id.qmui_top_right_btn).setOnClickListener {
                    //收藏服务人员
                    userId?.let {
                        mPresenter?.requestAddCollection(it)
                    }
                }
            }
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
    override fun onGetStaffCommentInfo(commentResult: ArrayList<StaffCommentResult>) {
        if (page == 1 && commentResult.isEmpty()) {
            val noCommentView = LayoutInflater.from(context).inflate(R.layout.footer_comment_no_layout, null)
            noCommentView.minimumHeight = McaUtils.dip2px(context!!, 80f)
            mCommentAdapter.setFooterView(noCommentView)
            return
        }

        if (commentResult.isNotEmpty()) {
            smart_refresh_appoint_user_info.finishLoadMore()
        } else {
            smart_refresh_appoint_user_info.finishLoadMore()
            smart_refresh_appoint_user_info.finishLoadMoreWithNoMoreData()
            return
        }

        commentList.addAll(commentResult)
        mCommentAdapter.notifyDataSetChanged()
    }

    override fun onGetMyAddressList(result: MyAddressResult) {
    }

    override fun showLoading() {
        smart_refresh_appoint_user_info.visibility = View.GONE
        empty_view_staff_info.setLoadingShowing(true)
    }

    override fun hideLoading() {
        smart_refresh_appoint_user_info.visibility = View.VISIBLE
        empty_view_staff_info.hide()
    }

    override fun showMessage(message: String) {
        dialog = QMUITipDialog.Builder(context).setTipWord(message).create()
        dialog.setTitle(message)
        dialog.show()
        Handler().postDelayed({ dialog.dismiss() }, 800)
        if (message == getString(R.string.store_collect_had_desc)) {
            mResult.favoriteFlag = 1
            initTobBarClick()

        } else if (message == getString(R.string.social_circle_cancel_attention_hint)) {
            mResult.favoriteFlag = 0
            initTobBarClick()
        }
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun post(runnable: Runnable?) {
    }

    override fun killMyself() {
        activity?.onBackPressed()
    }
}
