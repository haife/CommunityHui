package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.R.string
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerHouseKeepComponent
import com.kaiwukj.android.communityhui.di.module.HouseKeepModule
import com.kaiwukj.android.communityhui.mvp.contract.HouseKeepContract
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult
import com.kaiwukj.android.communityhui.mvp.presenter.HouseKeepPresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HouseKeepServiceAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.fragment_house_keep_service.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 家政服务
 */
class HouseKeepFragment : BaseSwipeBackFragment<HouseKeepPresenter>(), HouseKeepContract.View {
    private var mEntity: ArrayList<HomeServiceEntity> = ArrayList()
    private var typeFaceMediumBold: Typeface? = null
    private var serviceAdapter: HouseKeepServiceAdapter? = null

    companion object {
        fun newInstance(): HouseKeepFragment {
            val fragment = HouseKeepFragment()
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_house_keep_service, container, false))
    }

    override fun initData(savedInstanceState: Bundle?) {
        initTopBar()
        initUi()
        mPresenter?.requestServiceList()
    }

    private fun initUi() {
        serviceAdapter = HouseKeepServiceAdapter(R.layout.recycle_item_house_keep_service, mEntity, context)
        rv_house_keep_type.layoutManager = LinearLayoutManager(context)
        rv_house_keep_type.addItemDecoration(RecycleViewDivide(divideHeight = 20, divideColor = Color.parseColor("#F9F9F9")))
        rv_house_keep_type.adapter = serviceAdapter
        serviceAdapter?.setOnItemClickListener { adapter, view, position ->
            start(HouseKeepListFragment.newInstance(mEntity[position].id.toString(), mEntity))
        }
    }


    private fun initTopBar() {
        qtb_house_keeping.addLeftBackImageButton().setOnClickListener { killMyself() }
        qtb_house_keeping.setTitle(getString(string.house_keeping_title_str))
    }

    override fun onGetServiceList(result: List<HomeServiceEntity>) {
        mEntity.addAll(result)
        serviceAdapter?.notifyDataSetChanged()
    }

    override fun onSelectStaffList(result: List<StaffListResult>) {
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
        activity?.finish()
    }

    override fun post(runnable: Runnable?) {
    }


}
