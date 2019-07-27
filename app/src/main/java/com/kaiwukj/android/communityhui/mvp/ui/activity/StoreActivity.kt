package com.kaiwukj.android.communityhui.mvp.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackActivity
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.StoreListURL
import com.kaiwukj.android.communityhui.di.component.DaggerStoreComponent
import com.kaiwukj.android.communityhui.di.module.StoreModule
import com.kaiwukj.android.communityhui.mvp.contract.StoreContract
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreDetailResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.communityhui.mvp.presenter.StorePresenter
import com.kaiwukj.android.communityhui.mvp.ui.fragment.HomeFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.StoreDetailFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.StoreListFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.StoreSortListFragment
import com.kaiwukj.android.mcas.di.component.AppComponent
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/18
 * @desc 门店列表
 */
@Route(path = StoreListURL)
class StoreActivity : BaseSwipeBackActivity<StorePresenter>(), StoreContract.View {

    @Autowired(name = ExtraCons.EXTRA_KEY_STORE)
    @JvmField
    var mTargetStr: String? = null

    @Autowired(name = ExtraCons.EXTRA_KEY_STORE_SHOP_ID)
    @JvmField
    var mShopId: String? = null

    override fun post(runnable: Runnable?) {
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerStoreComponent
                .builder()
                .appComponent(appComponent)
                .storeModule(StoreModule(this))
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        ARouter.getInstance().inject(this)
        return R.layout.activity_store
    }


    override fun initData(savedInstanceState: Bundle?) {
        when (mTargetStr) {
            HomeFragment.EXTRA_KEY_HOME_FRAGMENT_URL -> loadRootFragment(R.id.fl_store_container, StoreListFragment.newInstance())
            StoreSortListFragment.FRAGMENT_KEY_STORE_SORT_LIST -> loadRootFragment(R.id.fl_store_container, StoreSortListFragment.newInstance(mShopId?.toInt()))
            StoreSortListFragment.STORE_SORT_LIST_FRAGMENT -> loadRootFragment(R.id.fl_store_container, StoreDetailFragment.newInstance(mShopId?.toInt()))
        }
    }

    override fun getContextView(): Context? = this

    override fun onGetStoreRecommend(list: StoreListResult) {
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
        finish()
    }

    override fun onRefreshFinish() {
    }

    override fun onLoadMoreFinish() {
    }

    override fun onGetStoreDetail(detailResult: StoreDetailResult) {
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return DefaultHorizontalAnimator()
    }
}
