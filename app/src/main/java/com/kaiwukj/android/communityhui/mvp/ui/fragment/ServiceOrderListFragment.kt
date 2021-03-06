package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.OrderListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult
import com.kaiwukj.android.communityhui.mvp.presenter.MinePresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.OrderListAdapter
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

    //TODO:3:待服务 4：服务中 5：已完结，不传值即为查看所有订单
    private var mType: Int? = null
    private var orderList = ArrayList<OrderListResult>()
    lateinit var mOrderListAdapter: OrderListAdapter
    private var pageNum = 1

    companion object {
        fun newInstance(type: Int): ServiceOrderListFragment {
            val fragment = ServiceOrderListFragment()
            fragment.mType = type
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
        mPresenter?.requestMineOrderData(mType, pageNum)
        initRecycleView()

    }

    private fun initRecycleView() {
        smart_refresh_order_list.setEnableLoadMoreWhenContentNotFull(false)
        rv_service_order_list.layoutManager = LinearLayoutManager(context!!)
        rv_service_order_list.addItemDecoration(RecycleViewDivide(drawableId = null, divideHeight = 20, divideColor = ContextCompat.getColor(context!!, R.color.window_background_color)))
        mOrderListAdapter = OrderListAdapter(R.layout.recycle_item_service_order_list, orderList, context!!)
        rv_service_order_list.adapter = mOrderListAdapter

        val emptyView = LayoutInflater.from(context).inflate(R.layout.empty_view_common_container, null)
        mOrderListAdapter.emptyView = emptyView

        smart_refresh_order_list.setOnRefreshListener {
            pageNum = 1
            smart_refresh_order_list.setEnableLoadMore(false)
            mPresenter?.requestMineOrderData(mType, pageNum)
        }

        smart_refresh_order_list.setOnLoadMoreListener {
            pageNum++
            smart_refresh_order_list.setEnableRefresh(false)
            mPresenter?.requestMineOrderData(mType, pageNum)
        }

        mOrderListAdapter.setOnItemClickListener { adapter, view, position ->
            //TODO 跳转订单详情
            val order: OrderListResult = orderList[position]
            ARouter.getInstance().build(MineOrderUrl)
                    .withString(ExtraCons.EXTRA_KEY_ORDER_MINE, ServiceOrderDetailFragment.SERVICE_ORDER_DETAIL_FRAGMENT)
                    .withSerializable(ExtraCons.EXTRA_KEY_ORDER_DETAIL_KEY, order)
                    .navigation(context)
        }
    }

    override fun onGetOrderList(result: OrderListResult) {
        if (pageNum == 1) {
            smart_refresh_order_list.setEnableLoadMore(true)
            smart_refresh_order_list.finishRefresh()
            orderList.clear()
        } else {
            if (result.result.isEmpty()) {
                smart_refresh_order_list.finishRefresh()
                smart_refresh_order_list.finishLoadMoreWithNoMoreData()
                return
            } else {
                smart_refresh_order_list.setEnableRefresh(true)
                smart_refresh_order_list.finishRefresh()
            }
        }
        orderList.addAll(result.result)
        mOrderListAdapter.notifyDataSetChanged()
    }


    override fun onGetMineInfo(result: MineUserInfoResult) {

    }

    override fun onGetOtherHomePageData(result: SocialUserHomePageResult) {
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
