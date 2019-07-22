package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    private var request: StoreStaffRequest = StoreStaffRequest()
    private var staffList = ArrayList<StaffListResult>()
    private var page: Int = 1
    private var isLoadMore = false

    companion object {
        const val EXTRA_KEY_STAFF_LIST_URL = "HOUSE_STAFF_LIST"

        fun newInstance(int_type: String): HouseStaffListFragment {
            val fragment = HouseStaffListFragment()
            fragment.request.serviceTypeId = int_type.toInt()
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
        mPresenter?.requestSelectStaff(request)
        initRecycle()
    }

    private fun initRecycle() {
        rv_staff_list_child.layoutManager = LinearLayoutManager(context)
        rv_staff_list_child.addItemDecoration(RecycleViewDivide(drawableId = null, divideHeight = 20))
        mHouseAdapter = SelectStaffListAdapter(R.layout.recycle_item_house_staff_list_layout, staffList, context)
        rv_staff_list_child.adapter = mHouseAdapter

        mHouseAdapter.setOnItemClickListener { adapter, view, position ->
            Log.e("USER_ID", staffList[position].storeemployeeId.toString())
            val userID = staffList[position].storeemployeeId
            ARouter.getInstance().build(AppointmentUrl)
                    .withString(ExtraCons.EXTRA_KEY_HOUSE_KEEP, APPOINTMENT_PERSON_INFO_FRAGMENT)
                    .withInt(ExtraCons.EXTRA_KEY_STAFF_USER_ID, userID).navigation()
        }

        smart_refresh_staff_list.setOnLoadMoreListener {
            page++
            isLoadMore = true
            mPresenter?.requestSelectStaff(request)
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
        staffList.clear()
        mPresenter?.requestSelectStaff(request)
    }

    /**
     * 接口返回数据
     * @param result List<StaffListResult>
     */
    override fun onSelectStaffList(result: List<StaffListResult>) {
        staffList.addAll(result)
        if (isLoadMore)
            isLoadMore = !isLoadMore
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
