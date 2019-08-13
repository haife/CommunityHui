package com.kaiwukj.android.communityhui.mvp.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.StoreListURL
import com.kaiwukj.android.communityhui.di.component.DaggerEditMineInfoComponent
import com.kaiwukj.android.communityhui.di.module.EditMineInfoModule
import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.http.entity.request.MineCollectionRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineCollectionResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.presenter.EditMineInfoPresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.CollectionListAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.fragment_collection_store.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 门店列表
 */
class CollectionStoreListFragment : BaseSwipeBackFragment<EditMineInfoPresenter>(), EditMineInfoContract.View {


    private lateinit var mCollectionAdapter: CollectionListAdapter
    lateinit var request: MineCollectionRequest
    private var pageNum: Int = 1
    var collectionList = ArrayList<MineCollectionResult>()

    companion object {
        fun newInstance(requestStore: MineCollectionRequest): CollectionStoreListFragment {
            val fragment = CollectionStoreListFragment()
            fragment.request = requestStore
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
        return inflater.inflate(R.layout.fragment_collection_store, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.requestMyCollection(request)
        rv_collection_store_list.layoutManager = LinearLayoutManager(context)
        mCollectionAdapter = CollectionListAdapter(request.typeId, collectionList, R.layout.recycle_item_collection_store_list, context!!)
        rv_collection_store_list.adapter = mCollectionAdapter

        val emptyView = LayoutInflater.from(context).inflate(R.layout.empty_view_common_container, null)
        val emptyIV = emptyView.findViewById<ImageView>(R.id.iv_empty_view_type)
        emptyIV.background = (ContextCompat.getDrawable(context!!, R.mipmap.icon_empty_view_collection))
        mCollectionAdapter.emptyView = emptyView
        smart_collection_store_list.setOnLoadMoreListener {
            pageNum++
            request.pageNum = pageNum
            mPresenter?.requestMyCollection(request)
        }

        mCollectionAdapter.setOnItemClickListener { adapter, view, position ->

            ARouter.getInstance().build(StoreListURL).withString(ExtraCons.EXTRA_KEY_STORE, StoreSortListFragment.FRAGMENT_KEY_STORE_SORT_LIST)
                    .withString(ExtraCons.EXTRA_KEY_STORE_SHOP_ID, collectionList[position].favoriteId.toString()).navigation()
        }
    }


    override fun onGetMyAddressList(result: MyAddressResult) {
    }

    override fun onGetMyCollectionData(list: List<MineCollectionResult>) {

        if (pageNum > 1 && list.isNullOrEmpty()) {
            smart_collection_store_list.finishLoadMore()
            smart_collection_store_list.finishLoadMoreWithNoMoreData()
        } else {
            smart_collection_store_list.finishLoadMore()
            collectionList.addAll(list)
            mCollectionAdapter.notifyDataSetChanged()
        }

    }

    override fun post(runnable: Runnable?) {
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
        activity?.onBackPressed()
    }
}
