package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment
import com.kaiwukj.android.communityhui.di.component.DaggerMineComponent
import com.kaiwukj.android.communityhui.di.module.MineModule
import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.presenter.MinePresenter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import kotlinx.android.synthetic.main.fragment_mine.*


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
        DaggerMineComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mineModule(MineModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(com.kaiwukj.android.communityhui.R.layout.fragment_mine, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        initBottomGroupView()

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

                }
                mineServiceItem -> {

                }
                mineCollectItem -> {

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
