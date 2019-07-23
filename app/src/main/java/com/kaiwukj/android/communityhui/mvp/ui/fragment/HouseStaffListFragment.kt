package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment
import com.kaiwukj.android.communityhui.app.constant.AppointmentUrl
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.di.component.DaggerHouseKeepComponent
import com.kaiwukj.android.communityhui.di.module.HouseKeepModule
import com.kaiwukj.android.communityhui.mvp.contract.HouseKeepContract
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreListRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreStaffRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult
import com.kaiwukj.android.communityhui.mvp.presenter.HouseKeepPresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.SelectStaffListAdapter
import com.kaiwukj.android.communityhui.mvp.ui.fragment.AppointmentPersonInfoFragment.Companion.APPOINTMENT_PERSON_INFO_FRAGMENT
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.fragment_house_staff_list.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 家政人员服务列表
 */
class HouseStaffListFragment : BaseSupportFragment<HouseKeepPresenter>(), HouseKeepContract.View {
    private lateinit var mHouseAdapter: SelectStaffListAdapter
    //在选择阿姨列表中请求的数据
    private var request: StoreStaffRequest = StoreStaffRequest()
    //门店列表中查看全部技工的请求体
    private var mShopStaffRequest: StoreListRequest? = null
    private var staffList = ArrayList<StaffListResult>()
    private var page: Int = 1
    private var isLoadMore = false
    //用来判断是选择阿姨的列表页面还是查看门店下阿姨
    //1:选择阿姨 2:门店阿姨
    private var mRequestType: Int? = null

    companion object {
        const val EXTRA_KEY_STAFF_LIST_URL = "HOUSE_STAFF_LIST"

        fun newInstance(bean: StoreListRequest?, requestType: Int?): HouseStaffListFragment {
            val fragment = HouseStaffListFragment()
            fragment.mRequestType = requestType
            when (requestType) {
                1 -> {
                    bean?.let { it -> it.serviceTypeId?.let { fragment.request.serviceTypeId = it } }
                }
                2 -> {
                    fragment.mShopStaffRequest = bean
                }
            }
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHouseKeepComponent
                .builder()
                .appComponent(appComponent)
                .houseKeepModule(HouseKeepModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_house_staff_list, container, false);
    }


    override fun initData(savedInstanceState: Bundle?) {
        when (mRequestType) {
            1 ->   //如果是选择阿姨类型 请求此接口
                mPresenter?.requestSelectStaff(request)
            2 ->   //如果是查看某个门店下的所有阿姨
                mShopStaffRequest?.let { mPresenter?.requestShopsStaffList(it) }
        }

        initRecycle()
    }

    private fun initRecycle() {
        rv_staff_list_child.layoutManager = LinearLayoutManager(context)
        rv_staff_list_child.addItemDecoration(RecycleViewDivide(drawableId = null, divideHeight = 20))
        mHouseAdapter = SelectStaffListAdapter(R.layout.recycle_item_house_staff_list_layout, staffList, context)
        rv_staff_list_child.adapter = mHouseAdapter

        mHouseAdapter.setOnItemClickListener { adapter, view, position ->
            val userID = staffList[position].storeemployeeId
            val serviceTypeId = staffList[position].serviceTypeId


            ARouter.getInstance().build(AppointmentUrl)
                    .withString(ExtraCons.EXTRA_KEY_HOUSE_KEEP, APPOINTMENT_PERSON_INFO_FRAGMENT)
                    .withString(ExtraCons.EXTRA_KEY_STAFF_SETVIE_TYPE_ID, serviceTypeId.toString())
                    .withString(ExtraCons.EXTRA_KEY_STAFF_USER_ID, userID.toString()).navigation()
        }

        smart_refresh_staff_list.setOnLoadMoreListener {
            page++
            isLoadMore = true
            when (mRequestType) {
                1 ->   //如果是选择阿姨类型 请求此接口
                    mPresenter?.requestSelectStaff(request)
                2 ->   //如果是查看某个门店下的所有阿姨
                    mShopStaffRequest?.let { mPresenter?.requestShopsStaffList(it) }
            }
        }
    }

    /**
     * 排序
     * @param request StoreStaffRequest
     */
    fun sortStaffList(req: StoreStaffRequest) {
        request.score = req.score
        request.serviceHome = req.serviceHome
        request.workStartTime = req.workStartTime
        request.servicePrice = req.servicePrice
        page = 1
        mPresenter?.requestSelectStaff(request)
    }

    /**
     * 接口返回数据
     * @param result List<StaffListResult>
     */
    override fun onSelectStaffList(result: List<StaffListResult>) {

        if (isLoadMore) {
            staffList.clear()
            isLoadMore = false
            smart_refresh_staff_list?.finishLoadMore()
            if (result.isNotEmpty() && page > 1) {
                smart_refresh_staff_list?.finishLoadMoreWithNoMoreData()
            }
        }
        staffList.addAll(result)
        mHouseAdapter.notifyDataSetChanged()

    }

    override fun onGetServiceList(result: List<HomeServiceEntity>) {
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

    override fun post(runnable: Runnable?) {
    }
}
