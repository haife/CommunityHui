package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment
import com.kaiwukj.android.communityhui.di.component.DaggerEditMineInfoComponent
import com.kaiwukj.android.communityhui.di.module.EditMineInfoModule
import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.presenter.EditMineInfoPresenter
import com.kaiwukj.android.mcas.di.component.AppComponent

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  服务评价
 */
class PersonHomePageFragmnt : BaseSupportFragment<EditMineInfoPresenter>(), EditMineInfoContract.View {
    override fun post(runnable: Runnable?) {
    }

    companion object {
        fun newInstance(): PersonHomePageFragmnt {
            val fragment = PersonHomePageFragmnt()
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
        return inflater.inflate(R.layout.fragment_person_home_page, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {

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