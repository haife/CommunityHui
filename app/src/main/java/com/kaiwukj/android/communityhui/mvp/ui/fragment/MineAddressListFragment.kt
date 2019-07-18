package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerEditMineInfoComponent
import com.kaiwukj.android.communityhui.di.module.EditMineInfoModule
import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity
import com.kaiwukj.android.communityhui.mvp.presenter.EditMineInfoPresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HouseKeepListAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
import kotlinx.android.synthetic.main.fragment_mine_address_list.*

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  我的地址
 */
class MineAddressListFragment : BaseSwipeBackFragment<EditMineInfoPresenter>(), EditMineInfoContract.View {
    override fun post(runnable: Runnable?) {
    }

    companion object {
        const val MINE_ADDRESS_LIST_FRAGMENT = "MINE_ADDRESS_LIST_FRAGMENT"
        fun newInstance(): MineAddressListFragment {
            val fragment = MineAddressListFragment()
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
        return inflater.inflate(R.layout.fragment_mine_address_list, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        val list2: MutableList<HRecommendMultiItemEntity> = mutableListOf()
        for (i in 1..2) {
            list2.add(HRecommendMultiItemEntity(""))
        }
        rv_mine_address_list.layoutManager = LinearLayoutManager(context)
        rv_mine_address_list.addItemDecoration(RecycleViewDivide(drawableId = null, divideHeight = 20,
                divideColor = ContextCompat.getColor(context!!, R.color.window_background_color)))
        val mHouseAdapter = HouseKeepListAdapter(list2, R.layout.recycle_item_mine_address_list, context!!)
        rv_mine_address_list.adapter = mHouseAdapter

        mHouseAdapter.setOnItemClickListener { adapter, view, position ->
            start(EditMineAddressFragment.newInstance())
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
}
