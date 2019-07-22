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
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.HomeUiData
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.presenter.HomePresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HRecommendAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
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
class HomeFragment : BaseSupportFragment<HomePresenter>(), HomeContract.View {


    @Inject
    lateinit var mHomeAdapter: HRecommendAdapter

    @Inject
    lateinit var mLayoutManager: RecyclerView.LayoutManager

    val uiData = HomeUiData()

    companion object {
        const val EXTRA_KEY_HOME_FRAGMENT_URL = "HOME_FRAGMENT"
        const val RECOMMEND_FLAG = "1"

        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
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
        mPresenter?.requestServiceList()

        rv_home.layoutManager = mLayoutManager
        rv_home.adapter = mHomeAdapter
        childOnClickListener()
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

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

    private fun childOnClickListener() {
        mHomeAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.tv_home_banner_top_house_keeping -> {
                    //start(HouseKeepFragment.newInstance())
                }
            }
        }
    }

    /**
     * 首页服务列表
     * @param result HomeServiceEntity
     */
    override fun onGetServiceList(result: List<HomeServiceEntity>) {
        uiData.homeServiceList = result
    }

    override fun onGetStoreRecommend() {
    }

    override fun onGetStaffRecommend() {
    }
}

