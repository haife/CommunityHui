package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.MineInfoUrl
import com.kaiwukj.android.communityhui.app.constant.MineOrderUrl
import com.kaiwukj.android.communityhui.di.component.DaggerMineComponent
import com.kaiwukj.android.communityhui.di.module.MineModule
import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.presenter.MinePresenter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.include_mine_order_container.*
import kotlinx.android.synthetic.main.include_mine_top_container.*


class MineFragment : BaseSupportFragment<MinePresenter>(), MineContract.View {
    override fun post(runnable: Runnable?) {
    }

    companion object {
        fun newInstance(): MineFragment {
            val fragment = MineFragment()
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
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        initBottomGroupView()
        cl_mine_head_top.setOnClickListener {
            ARouter.getInstance().build(MineInfoUrl).withString(ExtraCons.EXTRA_KEY_EDIT_MINE, PersonHomePageFragment.PERSON_HOME_PAGE_FRAGMENT).navigation(context)
        }

        tv_mine_order_contracting.setOnClickListener {
            ARouter.getInstance().build(MineOrderUrl).withInt(ExtraCons.EXTRA_KEY_ORDER_MINE_INDEX, 0).withString(ExtraCons.EXTRA_KEY_ORDER_MINE, ServiceOrderFragment.SERVICE_ORDER_FRAGMENT).navigation(context)
        }
        tv_mine_order_servicing.setOnClickListener {
            ARouter.getInstance().build(MineOrderUrl).withInt(ExtraCons.EXTRA_KEY_ORDER_MINE_INDEX, 1).withString(ExtraCons.EXTRA_KEY_ORDER_MINE, ServiceOrderFragment.SERVICE_ORDER_FRAGMENT).navigation(context)
        }
        tv_mine_order_finished.setOnClickListener {
            ARouter.getInstance().build(MineOrderUrl).withInt(ExtraCons.EXTRA_KEY_ORDER_MINE_INDEX, 2).withString(ExtraCons.EXTRA_KEY_ORDER_MINE, ServiceOrderFragment.SERVICE_ORDER_FRAGMENT).navigation(context)
        }
        tv_mine_order_all.setOnClickListener {
            ARouter.getInstance().build(MineOrderUrl).withInt(ExtraCons.EXTRA_KEY_ORDER_MINE_INDEX, 3).withString(ExtraCons.EXTRA_KEY_ORDER_MINE, ServiceOrderFragment.SERVICE_ORDER_FRAGMENT).navigation(context)
        }
    }

    /**
     * 初始化GroupList
     */
    private fun initBottomGroupView() {
        val mineAddressItem = qui_group_list_mine.createItemView(getString(R.string.mine_address))
        mineAddressItem.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
        val mineServiceItem = qui_group_list_mine.createItemView(getString(R.string.mine_custom_service))
        mineServiceItem.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
        val mineCollectItem = qui_group_list_mine.createItemView(getString(R.string.mine_collection))
        mineCollectItem.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
        val mineSettingItem = qui_group_list_mine.createItemView(getString(R.string.mine_setting))
        mineSettingItem.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON

        val onClickListener = View.OnClickListener { v ->
            when (v) {
                mineAddressItem -> {
                    ARouter.getInstance().build(MineInfoUrl).withString(ExtraCons.EXTRA_KEY_EDIT_MINE, MineAddressListFragment.MINE_ADDRESS_LIST_FRAGMENT).navigation(context)
                }
                mineServiceItem -> {
                }
                mineCollectItem -> {
                    ARouter.getInstance().build(MineOrderUrl).withString(ExtraCons.EXTRA_KEY_ORDER_MINE, MineCollectionFragment.MINE_COLLECTION_FRAGMENT).navigation(context)
                }
                mineSettingItem ->{
                    ARouter.getInstance().build(MineInfoUrl).withString(ExtraCons.EXTRA_KEY_EDIT_MINE, SettingFragment.SETTING_FRAGMENT).navigation(context)
                }
            }
        }

        QMUIGroupListView.newSection(context)
                .setUseTitleViewForSectionSpace(false)
                .addItemView(mineAddressItem, onClickListener)
                .addItemView(mineServiceItem, onClickListener)
                .addItemView(mineCollectItem, onClickListener)
                .addItemView(mineSettingItem, onClickListener)
                .addTo(qui_group_list_mine)

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
