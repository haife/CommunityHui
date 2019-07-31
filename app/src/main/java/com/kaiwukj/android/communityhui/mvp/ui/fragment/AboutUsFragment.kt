package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.OrderListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult
import com.kaiwukj.android.communityhui.mvp.presenter.MinePresenter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.qmuiteam.qmui.widget.QMUITopBar


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc
 */
class AboutUsFragment : BaseSwipeBackFragment<MinePresenter>(), MineContract.View {

    companion object {
        fun newInstance(): AboutUsFragment {
            return AboutUsFragment()
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_about_us, container, false))
    }

    override fun post(runnable: Runnable?) {
    }

    override fun initData(savedInstanceState: Bundle?) {
        activity?.findViewById<QMUITopBar>(R.id.qtb_edit_mine_info)?.setTitle(getString(R.string.setting_about_company))
    }

    override fun onGetMineInfo(result: MineUserInfoResult) {
    }

    override fun onGetOtherHomePageData(result: SocialUserHomePageResult) {
    }

    override fun onGetOrderList(result: OrderListResult) {
    }

    override fun showMessage(message: String) {
    }


}
