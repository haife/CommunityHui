package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment
import com.kaiwukj.android.communityhui.di.component.DaggerMineComponent
import com.kaiwukj.android.communityhui.di.module.MineModule
import com.kaiwukj.android.communityhui.mvp.contract.MineContract
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.HouseKeepingServiceType
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.OrderListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.SocialUserHomePageResult
import com.kaiwukj.android.communityhui.mvp.presenter.MinePresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HomeViewPagerAdapter
import com.kaiwukj.android.communityhui.mvp.ui.widget.home.ScaleTransitionPagerTitleView
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.utils.McaUtils
import kotlinx.android.synthetic.main.fragment_service_all_oreder.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  全部服务订单
 */
class ServiceOrderFragment : BaseSupportFragment<MinePresenter>(), MineContract.View {
    override fun onGetOrderList(result: OrderListResult) {
    }

    private var mFragmentList: List<Fragment> = ArrayList()

    var mItemIndex: Int = 0
    private val titleList: ArrayList<HouseKeepingServiceType> = arrayListOf()

    companion object {
        const val SERVICE_ORDER_FRAGMENT = "SERVICE_ORDER_FRAGMENT"
        const val TYPE_WAITING = 3
        const val TYPE_SERVING = 4
        const val TYPE_FINISHED = 5
        const val TYPE_ALL = 0

        fun newInstance(itemIndex: Int): ServiceOrderFragment {
            val fragment = ServiceOrderFragment()
            fragment.mItemIndex = itemIndex
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
        return inflater.inflate(R.layout.fragment_service_all_oreder, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {

        initMagicIndicatorView()
        when (mItemIndex) {
            TYPE_WAITING -> view_pager_service_order_container.currentItem = 0
            TYPE_SERVING -> view_pager_service_order_container.currentItem = 1
            TYPE_FINISHED -> view_pager_service_order_container.currentItem = 2
            TYPE_ALL -> view_pager_service_order_container.currentItem = 3
        }
    }

    private fun initMagicIndicatorView() {
        val wait = HouseKeepingServiceType(TYPE_WAITING, getString(R.string.order_stores_wait))
        val serving = HouseKeepingServiceType(TYPE_SERVING, getString(R.string.order_stores_serving))
        val finished = HouseKeepingServiceType(TYPE_FINISHED, getString(R.string.order_stores_finish))
        val allIn = HouseKeepingServiceType(TYPE_ALL, getString(R.string.order_stores_all))
        titleList.add(wait)
        titleList.add(serving)
        titleList.add(finished)
        titleList.add(allIn)
        val mMIndicatorNavigator = CommonNavigator(context)
        mMIndicatorNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return titleList.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView = ScaleTransitionPagerTitleView(context)
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                simplePagerTitleView.text = titleList[index].string_name
                simplePagerTitleView.width = McaUtils.getScreenWidth(context) / 4
                simplePagerTitleView.normalColor = ContextCompat.getColor(context, R.color.home_color_hot_service_text)
                simplePagerTitleView.selectedColor = ContextCompat.getColor(context, R.color.common_text_dark_color)
                simplePagerTitleView.setOnClickListener { view_pager_service_order_container.currentItem = index }
                mFragmentList = mFragmentList + ServiceOrderListFragment.newInstance(titleList[index].int_type)
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                indicator.lineHeight = 4.0f
                indicator.setColors(ContextCompat.getColor(context, R.color.common_text_dark_color))
                return indicator
            }
        }
        view_pager_service_order_container.offscreenPageLimit = 4
        magic_indicator_service_order.navigator = mMIndicatorNavigator
        ViewPagerHelper.bind(magic_indicator_service_order, view_pager_service_order_container)
        //bind fragmentViewPager
        val homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager, mFragmentList)
        view_pager_service_order_container.adapter = homeViewPagerAdapter

    }

    override fun onGetMineInfo(result: MineUserInfoResult) {

    }

    override fun onGetOtherHomePageData(result: SocialUserHomePageResult) {
    }

    override fun post(runnable: Runnable?) {
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
