package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.MineOrderUrl
import com.kaiwukj.android.communityhui.di.component.DaggerMineComponent
import com.kaiwukj.android.communityhui.di.module.MineModule
import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.request.OrderCommentRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.OrderListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult
import com.kaiwukj.android.communityhui.mvp.presenter.MinePresenter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import kotlinx.android.synthetic.main.fragment_evaluate_service.*

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  服务评价
 */
class EvaluateServiceFragment : BaseSwipeBackFragment<MinePresenter>(), MineContract.View {
    lateinit var orderData: OrderListResult
    private lateinit var commentRequest: OrderCommentRequest
    private var professionStarSize: Int = 0
    private var attitudeStarSize: Int = 0
    private var chatStarSize: Int = 0
    private var ethicsStarSize: Int = 0

    companion object {
        fun newInstance(orderData: OrderListResult): EvaluateServiceFragment {
            val fragment = EvaluateServiceFragment()
            fragment.orderData = orderData
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_evaluate_service, container, false))
    }

    override fun initData(savedInstanceState: Bundle?) {
        commentRequest = OrderCommentRequest()
        tv_user_nick_name.text = orderData.realName
        context?.let { GlideArms.with(it).load(Api.IMG_URL + orderData.avatar).circleCrop().into(qiv_user_profile_photo) }
        qbtn_order_detail_bottom.setOnClickListener {

            if (professionStarSize + attitudeStarSize + chatStarSize + ethicsStarSize == 0) {
                showMessage("请给技工评分")
                return@setOnClickListener
            }

            commentRequest.orderId = orderData.orderId
            commentRequest.content = et_service_order_comment_content.text.toString()
            commentRequest.score = ((professionStarSize + attitudeStarSize + chatStarSize + ethicsStarSize) / 4)
            mPresenter?.requestCommentOrderData(commentRequest)
        }

        rating_bar_profession.setOnRatingChangeListener {
            professionStarSize = it.toInt()
        }
        rating_bar_attitude.setOnRatingChangeListener {
            attitudeStarSize = it.toInt()
        }
        rating_order_chat.setOnRatingChangeListener {
            chatStarSize = it.toInt()
        }
        rating_order_ethics.setOnRatingChangeListener {
            ethicsStarSize = it.toInt()
        }
    }

    override fun onGetMineInfo(result: MineUserInfoResult) {

    }

    override fun onGetOtherHomePageData(result: SocialUserHomePageResult) {
    }


    override fun showLoading() {

    }

    override fun onGetOrderList(result: OrderListResult) {
    }

    override fun post(runnable: Runnable?) {
    }


    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        val dialog: QMUITipDialog = QMUITipDialog.Builder(context).setTipWord(message).create()
        dialog.setTitle(message)
        dialog.show()
        Handler().postDelayed({
            dialog.dismiss()
            activity?.finish()
            ARouter.getInstance().build(MineOrderUrl).withString(ExtraCons.EXTRA_KEY_ORDER_MINE_INDEX, ServiceOrderFragment.TYPE_WAITING.toString()).withString(ExtraCons.EXTRA_KEY_ORDER_MINE, ServiceOrderFragment.SERVICE_ORDER_FRAGMENT).navigation(context)
        }, 800)
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun killMyself() {
        activity?.onBackPressed()
    }
}
