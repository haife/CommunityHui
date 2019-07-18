package com.kaiwukj.android.communityhui.mvp.ui.activity


import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackActivity
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.MineInfoUrl
import com.kaiwukj.android.communityhui.di.component.DaggerEditMineInfoComponent
import com.kaiwukj.android.communityhui.di.module.EditMineInfoModule
import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.presenter.EditMineInfoPresenter
import com.kaiwukj.android.communityhui.mvp.ui.fragment.MineAddressListFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.PersonHomePageFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.SettingFragment
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.activity_edit_mine_info.*
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc
 */
@Route(path = MineInfoUrl)
class EditMineInfoActivity : BaseSwipeBackActivity<EditMineInfoPresenter>(), EditMineInfoContract.View {
    @Autowired(name = ExtraCons.EXTRA_KEY_EDIT_MINE)
    @JvmField
    var mTargetStr: String? = null


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerEditMineInfoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .editMineInfoModule(EditMineInfoModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        ARouter.getInstance().inject(this)
        return R.layout.activity_edit_mine_info
    }


    override fun initData(savedInstanceState: Bundle?) {
        initTopBar()
        when (mTargetStr) {
            PersonHomePageFragment.PERSON_HOME_PAGE_FRAGMENT -> {
                loadRootFragment(R.id.fl_edit_mine_info_container, PersonHomePageFragment.newInstance())
            }
            MineAddressListFragment.MINE_ADDRESS_LIST_FRAGMENT -> {
                loadRootFragment(R.id.fl_edit_mine_info_container, MineAddressListFragment.newInstance())
            }
            SettingFragment.SETTING_FRAGMENT -> {
                qtb_edit_mine_info.setTitle(getString(R.string.setting_title))
                loadRootFragment(R.id.fl_edit_mine_info_container, SettingFragment.newInstance())
            }


        }


    }

    private fun initTopBar() {
        qtb_edit_mine_info.addLeftBackImageButton().setOnClickListener { killMyself() }
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return DefaultHorizontalAnimator()
    }

    override fun killMyself() {
        onBackPressedSupport()
    }

    override fun post(runnable: Runnable?) {
    }
}
