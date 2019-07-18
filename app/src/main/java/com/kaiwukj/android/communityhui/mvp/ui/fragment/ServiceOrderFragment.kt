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
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.BouseKeepingServiceType
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
    private var mFragmentList: List<Fragment> = ArrayList()

    var mItemIndex: Int = 0

    companion object {
        const val SERVICE_ORDER_FRAGMENT = "SERVICE_ORDER_FRAGMENT"

        fun newInstance(itemIndex: Int): ServiceOrderFragment {
            val fragment = ServiceOrderFragment()
            fragment.mItemIndex = itemIndex
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
        return inflater.inflate(R.layout.fragment_service_all_oreder, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        val bean1 = BouseKeepingServiceType(1, "待服务")
        val bean2 = BouseKeepingServiceType(2, "服务中")
        val bean3 = BouseKeepingServiceType(1, "已完结")
        val bean4 = BouseKeepingServiceType(1, "全部")
        val list: ArrayList<BouseKeepingServiceType> = arrayListOf()

        list.add(bean1)
        list.add(bean2)
        list.add(bean3)
        list.add(bean4)
        initMagicIndicatorView(list)
        view_pager_service_order_container.currentItem = mItemIndex
    }


    private fun initMagicIndicatorView(magicIndicatorContentList: List<BouseKeepingServiceType>) {
        val mMIndicatorNavigator = CommonNavigator(context)
        mMIndicatorNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return magicIndicatorContentList.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView = ScaleTransitionPagerTitleView(context)
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                simplePagerTitleView.text = magicIndicatorContentList[index].string_name
                simplePagerTitleView.width = McaUtils.getScreenWidth(context) / 4
                simplePagerTitleView.normalColor = ContextCompat.getColor(context, R.color.home_color_hot_service_text)
                simplePagerTitleView.selectedColor = ContextCompat.getColor(context, R.color.common_text_dark_color)
                simplePagerTitleView.setOnClickListener { view_pager_service_order_container.currentItem = index }
                mFragmentList = mFragmentList + ServiceOrderListFragment.newInstance(magicIndicatorContentList[index].int_type)
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

        view_pager_service_order_container.offscreenPageLimit = 1
        magic_indicator_service_order.navigator = mMIndicatorNavigator
        ViewPagerHelper.bind(magic_indicator_service_order, view_pager_service_order_container)

        //bind fragmentViewPager
        val homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager, mFragmentList)
        view_pager_service_order_container.adapter = homeViewPagerAdapter

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
