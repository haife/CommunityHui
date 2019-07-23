package com.kaiwukj.android.communityhui.mvp.ui.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.StoreListURL
import com.kaiwukj.android.communityhui.di.component.DaggerStoreComponent
import com.kaiwukj.android.communityhui.di.module.StoreModule
import com.kaiwukj.android.communityhui.mvp.contract.StoreContract
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreDetailResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.communityhui.mvp.presenter.StorePresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.StoreListAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.fragment_store.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 门店列表
 */
class CollectionStoreListFragment : BaseSwipeBackFragment<StorePresenter>(), StoreContract.View {


    lateinit var mStoreListAdapter: StoreListAdapter

    companion object {
        fun newInstance(): CollectionStoreListFragment {
            val fragment = CollectionStoreListFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerStoreComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .storeModule(StoreModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        initTopBar()
        val list = arrayListOf<StoreListResult>()
        for (i in 0..10) {
            list.add(StoreListResult())
        }
        rv_store_list.layoutManager = LinearLayoutManager(context!!)
        rv_store_list.addItemDecoration(RecycleViewDivide(drawableId = null, divideHeight = 20))
        mStoreListAdapter = StoreListAdapter(list, R.layout.recycle_item_store_list, context!!)

        rv_store_list.adapter = mStoreListAdapter

        mStoreListAdapter.setOnItemClickListener { adapter, view, position ->
            ARouter.getInstance().build(StoreListURL).withString(ExtraCons.EXTRA_KEY_STORE, StoreSortListFragment.STORE_SORT_LIST_FRAGMENT).navigation()

        }

    }
    override fun onGetStoreDetail(detailResult: StoreDetailResult) {

    }
    override fun onGetStoreRecommend(list: StoreListResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getContextView(): Context? = context


    override fun post(runnable: Runnable?) {
    }

    private fun initTopBar() {
        qtb_store_list.visibility = View.GONE
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
