package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerMineComponent
import com.kaiwukj.android.communityhui.di.module.MineModule
import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.OrderListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult
import com.kaiwukj.android.communityhui.mvp.presenter.MinePresenter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms
import kotlinx.android.synthetic.main.fragment_evaluate_service.*

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  服务评价
 */
class EvaluateServiceFragment : BaseSwipeBackFragment<MinePresenter>(), MineContract.View {
    lateinit var orderData: OrderListResult

    companion object {
        fun newInstance(orderData: OrderListResult): EvaluateServiceFragment {
            val fragment = EvaluateServiceFragment()
            fragment.orderData = orderData
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_evaluate_service, container, false))
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_user_nick_name.text = orderData.realName
        context?.let { GlideArms.with(it).load(Api.IMG_URL + orderData.avatar).circleCrop().into(qiv_user_profile_photo) }

        qbtn_order_detail_bottom.setOnClickListener {
            activity?.finish()
        }
    }

    override fun onGetMineInfo(result: MineUserInfoResult) {

    }

    override fun onGetOtherHomePageData(result: SocialUserHomePageResult) {
    }


    override fun showLoading() {

    }

    override fun onGetOrderList(result: OrderListResult) {
    }

    override fun post(runnable: Runnable?) {
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
