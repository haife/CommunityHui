package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment
import com.kaiwukj.android.communityhui.app.constant.AppointmentUrl
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.di.component.DaggerEditMineInfoComponent
import com.kaiwukj.android.communityhui.di.module.EditMineInfoModule
import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.http.entity.request.MineCollectionRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineCollectionResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.presenter.EditMineInfoPresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.CollectionListAdapter
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
class CollectionStaffListFragment : BaseSupportFragment<EditMineInfoPresenter>(), EditMineInfoContract.View {

    private lateinit var mCollectionAdapter: CollectionListAdapter
    lateinit var request: MineCollectionRequest
    var collectionList = ArrayList<MineCollectionResult>()

    companion object {
        const val EXTRA_KEY_STAFF_LIST_URL = "HOUSE_STAFF_LIST"

        fun newInstance(request: MineCollectionRequest): CollectionStaffListFragment {
            val fragment = CollectionStaffListFragment()
            fragment.request = request
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerEditMineInfoComponent
                .builder()
                .appComponent(appComponent)
                .editMineInfoModule(EditMineInfoModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_house_staff_list, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.requestMyCollection(request)
        rv_staff_list_child.layoutManager = LinearLayoutManager(context)

        mCollectionAdapter = CollectionListAdapter(request.typeId, collectionList, R.layout.recycle_item_collection_staff_list_layout, context!!)
        rv_staff_list_child.adapter = mCollectionAdapter

        mCollectionAdapter.setOnItemClickListener { adapter, view, position ->
            val userID = collectionList[position].favoriteId
            ARouter.getInstance().build(AppointmentUrl)
                    .withString(ExtraCons.EXTRA_KEY_HOUSE_KEEP, AppointmentPersonInfoFragment.APPOINTMENT_PERSON_INFO_FRAGMENT)
                    .withString(ExtraCons.EXTRA_KEY_STAFF_USER_ID, userID.toString()).navigation()
        }
    }

    override fun onGetMyCollectionData(list: List<MineCollectionResult>) {
        collectionList.addAll(list)
        mCollectionAdapter.notifyDataSetChanged()
    }


    override fun onGetMyAddressList(result: MyAddressResult) {
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
