package com.kaiwukj.android.communityhui.mvp.ui.activity


import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackActivity
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.MineOrderUrl
import com.kaiwukj.android.communityhui.di.component.DaggerMineComponent
import com.kaiwukj.android.communityhui.di.module.MineModule
import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.presenter.MinePresenter
import com.kaiwukj.android.communityhui.mvp.ui.fragment.ServiceOrderDetailFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.ServiceOrderFragment
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.activity_mine.*
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator

@Route(path = MineOrderUrl)
class MineActivity : BaseSwipeBackActivity<MinePresenter>(), MineContract.View {
    @Autowired(name = ExtraCons.EXTRA_KEY_ORDER_MINE)
    @JvmField
    var mTargetStr: String? = null
    @Autowired(name = ExtraCons.EXTRA_KEY_ORDER_MINE_INDEX)
    @JvmField
    var mItemIndex: Int = 0

    override fun post(runnable: Runnable?) {
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMineComponent
                .builder()
                .appComponent(appComponent)
                .mineModule(MineModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        ARouter.getInstance().inject(this)
        return R.layout.activity_mine
    }


    override fun initData(savedInstanceState: Bundle?) {
        initTopBar()


        when (mTargetStr) {
            ServiceOrderFragment.SERVICE_ORDER_FRAGMENT -> {
                qtb_mine_order_info.setTitle(getString(R.string.service_order_title))
                loadRootFragment(R.id.fl_mine_order_container, ServiceOrderFragment.newInstance(mItemIndex))
            }

            ServiceOrderDetailFragment.SERVICE_ORDER_DETAIL_FRAGMENT -> {
                qtb_mine_order_info.setTitle(getString(R.string.service_order_detail_title))
                loadRootFragment(R.id.fl_mine_order_container, ServiceOrderDetailFragment.newInstance())
            }
        }


    }

    private fun initTopBar() {
        qtb_mine_order_info.addLeftBackImageButton().setOnClickListener { killMyself() }
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

}
