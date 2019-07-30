package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerMineComponent
import com.kaiwukj.android.communityhui.di.module.MineModule
import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.OrderListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult
import com.kaiwukj.android.communityhui.mvp.presenter.MinePresenter
import com.kaiwukj.android.communityhui.mvp.ui.activity.LoginActivity
import com.kaiwukj.android.communityhui.utils.MyDataCleanManager
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.qmuiteam.qmui.widget.QMUILoadingView
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import kotlinx.android.synthetic.main.fragment_setting.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  服务评价
 */
class SettingFragment : BaseSwipeBackFragment<MinePresenter>(), MineContract.View {

    private var hintDialog: QMUITipDialog? = null

    companion object {
        const val SETTING_FRAGMENT = "SETTING_FRAGMENT"
        fun newInstance(): SettingFragment {
            return SettingFragment()
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_setting, container, false))
    }

    override fun initData(savedInstanceState: Bundle?) {
        initBottomGroupView()
        //退出登录
        qbtn_login_out.setOnClickListener {
            mPresenter?.requestLoginOut()
        }
    }


    /**
     * 初始化GroupList
     */
    private fun initBottomGroupView() {
        val finishDialog = QMUITipDialog.Builder(context).setTipWord(getString(R.string.setting_clearing_cache_success)).create()
        val clearCacheItem = qui_group_list_setting.createItemView(getString(R.string.setting_clear_cache))
        clearCacheItem.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM
        val loadingViewContainer: View = LayoutInflater.from(context).inflate(R.layout.custom_clear_cache_loading_layout, null)
        val cacheSizeTv = loadingViewContainer.findViewById<TextView>(R.id.tv_cache_size)
        val loadingView = loadingViewContainer.findViewById<QMUILoadingView>(R.id.load_view_cache)
        clearCacheItem.addAccessoryCustomView(loadingViewContainer)
        val aboutCompany = qui_group_list_setting.createItemView(getString(R.string.setting_about_company))
        aboutCompany.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
        val cacheSize: String = MyDataCleanManager.getTotalCacheSize(context)
        cacheSizeTv.text = cacheSize

        val onClickListener = View.OnClickListener { v ->
            when (v) {
                clearCacheItem -> {
                    if (!MyDataCleanManager.getTotalCacheSize(context).contains("0.0")) {
                        hintDialog = QMUITipDialog.Builder(context).setTipWord(getString(R.string.setting_clearing_cache)).create()
                        MyDataCleanManager.clearAllCache(context)
                    } else {
                        hintDialog = QMUITipDialog.Builder(context).setTipWord(getString(R.string.setting_not_need_clear_cache)).create()
                        hintDialog?.show()
                        Handler().postDelayed({
                            hintDialog?.dismiss()
                        }, 1500)
                        return@OnClickListener
                    }

                    loadingView.visibility = View.VISIBLE
                    cacheSizeTv.visibility = View.GONE
                    hintDialog?.show()
                    loadingView.start()
                    Handler().postDelayed({
                        loadingView.stop()
                        cacheSizeTv.text = MyDataCleanManager.getTotalCacheSize(context)
                        loadingView.visibility = View.GONE
                        cacheSizeTv.visibility = View.VISIBLE
                        hintDialog?.dismiss()
                        finishDialog.show()
                        Handler().postDelayed({
                            finishDialog.dismiss()
                        }, 500)
                    }, 1500)

                }
                aboutCompany -> {

                }

            }
        }

        QMUIGroupListView.newSection(context)
                .setUseTitleViewForSectionSpace(false)
                .addItemView(clearCacheItem, onClickListener)
                .addItemView(aboutCompany, onClickListener)
                .addTo(qui_group_list_setting)

    }


    override fun onGetMineInfo(result: MineUserInfoResult) {

    }

    override fun onGetOrderList(result: OrderListResult) {
    }


    override fun onGetOtherHomePageData(result: SocialUserHomePageResult) {
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
    }

    override fun launchActivity(intent: Intent) {
        intent.setClass(context!!, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        activity?.finish()
    }

    override fun post(runnable: Runnable?) {
    }

    override fun killMyself() {
        activity?.onBackPressed()
    }
}
