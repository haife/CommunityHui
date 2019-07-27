package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.MineInfoUrl
import com.kaiwukj.android.communityhui.app.constant.MineOrderUrl
import com.kaiwukj.android.communityhui.app.constant.SocialCircleUrl
import com.kaiwukj.android.communityhui.di.component.DaggerMineComponent
import com.kaiwukj.android.communityhui.di.module.MineModule
import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.OrderListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult
import com.kaiwukj.android.communityhui.mvp.presenter.MinePresenter
import com.kaiwukj.android.communityhui.mvp.ui.activity.SocialCircleActivity
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.include_mine_order_container.*
import kotlinx.android.synthetic.main.include_mine_top_container.*
import timber.log.Timber


class MineFragment : BaseSupportFragment<MinePresenter>(), MineContract.View {

    private var userInfoResult: MineUserInfoResult? = null
    //是否刷新个人信息
    private var refreshUserInfo = false


    override fun post(runnable: Runnable?) {
    }

    companion object {
        var CHOICE_INDEX = 0

        fun newInstance(): MineFragment {
            return MineFragment()
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
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.getMineInfoData()
        initBottomGroupView()
        initClick()
    }

    private fun initClick() {
        cl_mine_head_top.setOnClickListener {
            if (userInfoResult != null) {
                refreshUserInfo = true
                ARouter.getInstance().build(MineInfoUrl).withSerializable(ExtraCons.EXTRA_KEY_MINE_USER_INFO, userInfoResult)
                        .withString(ExtraCons.EXTRA_KEY_EDIT_MINE, PersonHomePageFragment.PERSON_HOME_PAGE_FRAGMENT).navigation(context)
            }
        }

        tv_mine_card_num.setOnClickListener {
            CHOICE_INDEX = 0
            launcherPersonPage()

        }
        tv_mine_reply_num.setOnClickListener {
            CHOICE_INDEX = 1
            launcherPersonPage()
        }
        tv_mine_fans_num.setOnClickListener {
            CHOICE_INDEX = 2
            launcherPersonPage()
        }
        tv_mine_collection_num.setOnClickListener {
            CHOICE_INDEX = 3
            launcherPersonPage()

        }

        // 3:待服务 4：服务中 5：已完结，
        tv_mine_order_contracting.setOnClickListener {
            launcherOrderList(ServiceOrderFragment.TYPE_WAITING)
        }
        tv_mine_order_servicing.setOnClickListener {
            launcherOrderList(ServiceOrderFragment.TYPE_SERVING)
        }
        tv_mine_order_finished.setOnClickListener {
            launcherOrderList(ServiceOrderFragment.TYPE_FINISHED)
        }
        tv_mine_order_all.setOnClickListener {
            launcherOrderList(ServiceOrderFragment.TYPE_ALL)
        }

        smart_refresh_view_mine.setOnRefreshListener {
            refreshUserInfo = true
            mPresenter?.getMineInfoData()
        }
    }

    /**
     * 跳转到个人主页
     * @param url String
     */
    private fun launcherPersonPage() {
        if (userInfoResult != null)
            ARouter.getInstance().build(SocialCircleUrl).withString(SocialCircleActivity.FRAGMENT_KEY, SocialCirclePersonPageFragment.SOCIAL_CIRCLE_PERSON_PAGEF_RAGMENT)
                    .withInt(ExtraCons.EXTRA_KEY_USER_ID, userInfoResult!!.userId).navigation()
    }

    /**
     * 环信初始化登录
     */
    private fun initHx() {
        EMClient.getInstance().login(userInfoResult?.hxName, "kaiwu2019", object : EMCallBack {
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups()
                EMClient.getInstance().chatManager().loadAllConversations()
                Timber.e("登录聊天服务器成功！")
            }

            override fun onProgress(progress: Int, status: String) {
            }

            override fun onError(code: Int, message: String) {
                Timber.e("登录聊天服务器失败！" + message)
            }
        })
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
                    userInfoResult?.let {
                        ARouter.getInstance().build(MineOrderUrl).withString(ExtraCons.EXTRA_KEY_STAFF_USER_ID, it.userId.toString())
                                .withString(ExtraCons.EXTRA_KEY_ORDER_MINE, MineCollectionFragment.MINE_COLLECTION_FRAGMENT).navigation(context)

                    }
                }
                mineSettingItem -> {
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

    override fun onSupportVisible() {
        super.onSupportVisible()
        if (refreshUserInfo)
            mPresenter?.getMineInfoData()
    }

    /**
     * 获取个人信息
     * @param result MineUserInfoResult
     */
    override fun onGetMineInfo(result: MineUserInfoResult) {
        userInfoResult = result
        if (refreshUserInfo) {
            refreshUserInfo = false
        } else {
            initHx()
        }
        tv_user_explain.text = if (result.perSign.isNullOrEmpty()) getString(R.string.user_info_no_setting) else result.perSign

        context?.let { GlideArms.with(it).load(Api.IMG_URL + result.headImg).circleCrop().into(qiv_user_profile_photo) }
        tv_mine_user_nick_name.text = result.nickName

        smart_refresh_view_mine.finishRefresh()
    }


    override fun onGetOtherHomePageData(result: SocialUserHomePageResult) {
        tv_mine_card_num.text = "${result.noteCount}\n发布"
        tv_mine_reply_num.text = "${result.replyCount}\n回复"
        tv_mine_fans_num.text = "${result.fansCount}\n粉丝"
        tv_mine_collection_num.text = "${result.focusedCount}\n关注"
    }

    /**
     * 跳转到订单列表
     * @param type Int
     */
    private fun launcherOrderList(type: Int) {
        ARouter.getInstance().build(MineOrderUrl).withString(ExtraCons.EXTRA_KEY_ORDER_MINE_INDEX, type.toString()).withString(ExtraCons.EXTRA_KEY_ORDER_MINE, ServiceOrderFragment.SERVICE_ORDER_FRAGMENT).navigation(context)
    }

    override fun onGetOrderList(result: OrderListResult) {
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
        smart_refresh_view_mine.finishRefresh()
    }

    override fun showMessage(message: String) {
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun killMyself() {

    }
}
