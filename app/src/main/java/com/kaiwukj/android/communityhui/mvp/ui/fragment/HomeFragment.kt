package com.kaiwukj.android.communityhui.mvp.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment
import com.kaiwukj.android.communityhui.di.component.DaggerHomeComponent
import com.kaiwukj.android.communityhui.di.module.HomeModule
import com.kaiwukj.android.communityhui.mvp.contract.HomeContract
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreListRequest
import com.kaiwukj.android.communityhui.mvp.presenter.HomePresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HRecommendAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc
 */
class HomeFragment : BaseSupportFragment<HomePresenter>(), HomeContract.View, OnRefreshListener {

    @Inject
    lateinit var mHomeAdapter: HRecommendAdapter

    @Inject
    lateinit var mLayoutManager: RecyclerView.LayoutManager


    companion object {
        const val EXTRA_KEY_HOME_FRAGMENT_URL = "HOME_FRAGMENT"
        const val RECOMMEND_FLAG = 1

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHomeComponent
                .builder()
                .appComponent(appComponent)
                .homeModule(HomeModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.requestServiceList(StoreListRequest(RECOMMEND_FLAG), false)
        smart_refresh_home.setOnRefreshListener(this)
        rv_home.layoutManager = mLayoutManager
        rv_home.adapter = mHomeAdapter
    }

    override fun showLoading() {
        qmui_empty_view.setLoadingShowing(true)
    }

    override fun hideLoading() {
        qmui_empty_view.hide()
    }

    override
    fun getFragment(): Fragment = this

    override fun post(runnable: Runnable?) {
    }

    override fun showMessage(message: String) {
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun killMyself() {

    }

    override fun onResponseError() {
        smart_refresh_home.finishRefresh()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mPresenter?.requestStoreRecommend(StoreListRequest(RECOMMEND_FLAG), true)
        mPresenter?.requestStaffRecommend(StoreListRequest(RECOMMEND_FLAG), true)
    }

}

