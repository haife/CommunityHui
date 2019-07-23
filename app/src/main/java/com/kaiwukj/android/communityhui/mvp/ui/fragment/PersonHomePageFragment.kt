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
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.presenter.EditMineInfoPresenter
import com.kaiwukj.android.communityhui.utils.InputMethodUtils
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.fragment_person_home_page.*

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  我的信息主页
 */
class PersonHomePageFragment : BaseSwipeBackFragment<EditMineInfoPresenter>(), EditMineInfoContract.View {
    var mUserInfo: MineUserInfoResult? = null

    companion object {
        const val PERSON_HOME_PAGE_FRAGMENT = "PERSON_HOME_PAGE_FRAGMENT"

        fun newInstance(userInfo: MineUserInfoResult?): PersonHomePageFragment {
            val fragment = PersonHomePageFragment()
            fragment.mUserInfo = userInfo
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_person_home_page, container, false))
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_home_page_name.isEnabled = false
        tv_click_edit_nick_name.setOnClickListener {
            tv_home_page_name.setText("")
            tv_home_page_name.isEnabled = true
            tv_home_page_name.isFocusable = true
            tv_home_page_name.isFocusableInTouchMode = true
            tv_home_page_name.requestFocus()
            InputMethodUtils.showSoftInput(tv_home_page_name)
        }
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

    override fun post(runnable: Runnable?) {
    }

}
