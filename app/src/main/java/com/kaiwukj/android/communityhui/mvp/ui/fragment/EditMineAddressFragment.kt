package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerEditMineInfoComponent
import com.kaiwukj.android.communityhui.di.module.EditMineInfoModule
import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.presenter.EditMineInfoPresenter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.qmuiteam.qmui.widget.QMUITopBar

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  服务评价
 */
class EditMineAddressFragment : BaseSwipeBackFragment<EditMineInfoPresenter>(), EditMineInfoContract.View {

    companion object {
        const val EDIT_MINE_ADDRESS_FRAGMENT = "EDIT_MINE_ADDRESS_FRAGMENT"
        fun newInstance(): EditMineAddressFragment {
            val fragment = EditMineAddressFragment()
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_edit_mine_address, container, false))
    }



    override fun initData(savedInstanceState: Bundle?) {
    }



    override fun onSupportVisible() {
        super.onSupportVisible()
        activity?.findViewById<QMUITopBar>(R.id.qtb_edit_mine_info)?.setTitle(getString(R.string.edit_mine_address))
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
