package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.MineOrderUrl
import com.kaiwukj.android.communityhui.di.component.DaggerMineComponent
import com.kaiwukj.android.communityhui.di.module.MineModule
import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.communityhui.mvp.presenter.MinePresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.StoreListAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.fragment_service_oreder_list.*

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  服务订单子列表
 */
class ServiceOrderListFragment : BaseSupportFragment<MinePresenter>(), MineContract.View {
    private var mFragmentList: List<Fragment> = ArrayList()

    companion object {
        fun newInstance(int_type: Int): ServiceOrderListFragment {
            val fragment = ServiceOrderListFragment()
            return fragment
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerMineComponent
                .builder()
                .appComponent(appComponent)
                .mineModule(MineModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_service_oreder_list, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        val list = arrayListOf<StoreListResult>()
        for (i in 0..10) {
            list.add(StoreListResult())
        }
        rv_service_order_list.layoutManager = LinearLayoutManager(context!!)
        rv_service_order_list.addItemDecoration(RecycleViewDivide(drawableId = null, divideHeight = 20,
                divideColor = ContextCompat.getColor(context!!, R.color.window_background_color)))
        val mStoreListAdapter = StoreListAdapter(list, R.layout.recycle_item_service_order_list, context!!)
        rv_service_order_list.adapter = mStoreListAdapter
        mStoreListAdapter.setOnItemClickListener { adapter, view, position ->
            ARouter.getInstance().build(MineOrderUrl).withString(ExtraCons.EXTRA_KEY_ORDER_MINE, ServiceOrderDetailFragment.SERVICE_ORDER_DETAIL_FRAGMENT).navigation(context)
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
    }
}
