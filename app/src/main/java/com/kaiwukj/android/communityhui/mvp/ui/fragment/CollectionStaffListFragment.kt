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
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.HouseKeepUrl
import com.kaiwukj.android.communityhui.di.component.DaggerHouseKeepComponent
import com.kaiwukj.android.communityhui.di.module.HouseKeepModule
import com.kaiwukj.android.communityhui.mvp.contract.HouseKeepContract
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult
import com.kaiwukj.android.communityhui.mvp.presenter.HouseKeepPresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HouseKeepListAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.fragment_house_staff_list.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 收藏家政人员服务列表
 */
class CollectionStaffListFragment : BaseSupportFragment<HouseKeepPresenter>(), HouseKeepContract.View {


    private lateinit var mHouseAdapter: HouseKeepListAdapter

    companion object {
        const val EXTRA_KEY_STAFF_LIST_URL = "HOUSE_STAFF_LIST"         

        fun newInstance(int_type: Int): CollectionStaffListFragment {
            val fragment = CollectionStaffListFragment()
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
        val list2: MutableList<HRecommendMultiItemEntity> = mutableListOf()
        for (i in 1..10) {
            list2.add(HRecommendMultiItemEntity(""))
        }
        rv_staff_list_child.layoutManager = LinearLayoutManager(context)
        rv_staff_list_child.addItemDecoration(RecycleViewDivide(drawableId = null, divideHeight = 20))
        mHouseAdapter = HouseKeepListAdapter(list2, R.layout.recycle_item_collection_store_list, context!!)
        rv_staff_list_child.adapter = mHouseAdapter

        mHouseAdapter.setOnItemClickListener { adapter, view, position ->
            ARouter.getInstance().build(HouseKeepUrl).withString(ExtraCons.EXTRA_KEY_HOUSE_KEEP, EXTRA_KEY_STAFF_LIST_URL).navigation()
        }
    }

    override fun onSelectStaffList(result: List<StaffListResult>) {
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
