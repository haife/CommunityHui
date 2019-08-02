package com.kaiwukj.android.communityhui.mvp.ui.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerStoreComponent
import com.kaiwukj.android.communityhui.di.module.StoreModule
import com.kaiwukj.android.communityhui.mvp.contract.StoreContract
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreDetailResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.communityhui.mvp.presenter.StorePresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.StoreListAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.utils.McaUtils
import kotlinx.android.synthetic.main.fragment_store.*
import javax.inject.Inject


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 门店列表
 */
class StoreListFragment : BaseSwipeBackFragment<StorePresenter>(), StoreContract.View {
    @Inject
    lateinit var mStoreListAdapter: StoreListAdapter

    @Inject
    lateinit var mLayoutManager: RecyclerView.LayoutManager

    @Inject
    lateinit var listData: ArrayList<StoreListResult>
    private var page = 1
    override fun post(runnable: Runnable?) {
    }

    companion object {
        fun newInstance(): StoreListFragment {
            return StoreListFragment()
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerStoreComponent
                .builder()
                .appComponent(appComponent)
                .storeModule(StoreModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        initTopBar()
        initRecycleView()
        mPresenter?.requestAllStoreRecommend(page)
        processRecycle()
    }

    private fun processRecycle() {
        mStoreListAdapter.setOnItemClickListener { adapter, view, position ->
            start(StoreSortListFragment.newInstance(listData[position].id))
        }
        smart_store_list.setOnRefreshListener {
            page = 1
            it.setEnableLoadMore(false)
            mPresenter?.requestAllStoreRecommend(page)
            smart_store_list.setEnableLoadMore(true)
        }
        smart_store_list.setOnLoadMoreListener {
            page++
            it.setEnableRefresh(false)
            mPresenter?.requestAllStoreRecommend(page)
            it.setEnableRefresh(true)
        }
    }

    private fun initRecycleView() {
        rv_store_list.addItemDecoration(RecycleViewDivide(drawableId = null, divideHeight = 20))
        McaUtils.configRecyclerView(rv_store_list, mLayoutManager)
        rv_store_list.adapter = mStoreListAdapter
    }

    override fun getContextView(): Context? = context

    override fun onGetStoreRecommend(list: StoreListResult) {
        //所有门店
        if (page == 1) {
            listData.clear()
            smart_store_list.finishRefresh()
            listData.addAll(list.result.list)
        } else {
            if (list.result.list.size > 0) {
                listData.addAll(list.result.list)
                smart_store_list.finishLoadMore()
            } else {
                smart_store_list.finishLoadMoreWithNoMoreData()
            }
        }
        mStoreListAdapter.notifyDataSetChanged()
    }

    private fun initTopBar() {
        qtb_store_list.addLeftBackImageButton().setOnClickListener { killMyself() }
        qtb_store_list.setTitle(getString(R.string.store_title))
    }

    override fun showLoading() {
        empty_view_store.setLoadingShowing(true)
        rv_store_list.visibility = View.GONE
    }

    override fun hideLoading() {
        empty_view_store.hide()
        rv_store_list.visibility = View.VISIBLE
    }

    override fun showMessage(message: String) {
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun killMyself() {
        activity?.onBackPressed()
    }

    override fun onRefreshFinish() {
    }

    override fun onLoadMoreFinish() {
    }

    override fun onGetStoreDetail(detailResult: StoreDetailResult) {
    }

}
