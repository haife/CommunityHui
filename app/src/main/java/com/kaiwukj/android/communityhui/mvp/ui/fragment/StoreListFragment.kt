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
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.communityhui.mvp.presenter.StorePresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.StoreListAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.utils.McaUtils
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
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


    override fun post(runnable: Runnable?) {
    }

    companion object {
        fun newInstance(): StoreListFragment {
            val fragment = StoreListFragment()
            return fragment
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
        mPresenter?.requestAllStoreRecommend()
        mStoreListAdapter.setOnItemClickListener { adapter, view, position ->
            start(StoreSortListFragment.newInstance())
        }

        smart_store_list.setOnRefreshListener(OnRefreshListener {  })

    }

    private fun initRecycleView() {
        rv_store_list.addItemDecoration(RecycleViewDivide(drawableId = null, divideHeight = 20))
        McaUtils.configRecyclerView(rv_store_list, mLayoutManager)
        rv_store_list.adapter = mStoreListAdapter
    }

    override fun getContextView(): Context? = context

    override fun onGetStoreRecommend(list: StoreListResult) {
    }

    private fun initTopBar() {
        qtb_store_list.addLeftBackImageButton().setOnClickListener { killMyself() }
        qtb_store_list.setTitle(getString(R.string.store_title))
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
