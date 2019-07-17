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


    companion object {
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
        mPresenter?.processRecommendData()
        rv_home.layoutManager = mLayoutManager
        rv_home.adapter = mHomeAdapter;
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
}
