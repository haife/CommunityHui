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
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerHouseKeepComponent
import com.kaiwukj.android.communityhui.di.module.HouseKeepModule
import com.kaiwukj.android.communityhui.mvp.contract.HouseKeepContract
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.BouseKeepingServiceType
import com.kaiwukj.android.communityhui.mvp.presenter.HouseKeepPresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HomeViewPagerAdapter
import com.kaiwukj.android.communityhui.mvp.ui.widget.home.ScaleTransitionPagerTitleView
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.utils.McaUtils
import kotlinx.android.synthetic.main.fragment_house_keep_service_list.*
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
 * @time 2019/7/16
 * @desc 家政服务人员列表container
 */
class HouseKeepListFragment : BaseSwipeBackFragment<HouseKeepPresenter>(), HouseKeepContract.View {

    private var mFragmentList: List<Fragment> = ArrayList()

    companion object {
        fun newInstance(): HouseKeepListFragment {
            val fragment = HouseKeepListFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHouseKeepComponent
                .builder()
                .appComponent(appComponent)
                .houseKeepModule(HouseKeepModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_house_keep_service_list, container, false))
    }


    override fun initData(savedInstanceState: Bundle?) {
        val bean1 = BouseKeepingServiceType(1, "月嫂")
        val bean2 = BouseKeepingServiceType(2, "护工")
        val bean3 = BouseKeepingServiceType(1, "育婴师")
        val bean4 = BouseKeepingServiceType(1, "催乳师")
        val list: ArrayList<BouseKeepingServiceType> = arrayListOf()

        list.add(bean1)
        list.add(bean2)
        list.add(bean3)
        list.add(bean4)
        initMagicIndicatorView(list)
        initTopBar()

    }


    private fun initTopBar() {
        qtb_house_keeping_staff_list.addLeftBackImageButton().setOnClickListener { killMyself() }
        qtb_house_keeping_staff_list.setTitle(getString(R.string.house_keeping_title_str))
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
                simplePagerTitleView.setOnClickListener { view_pager_house_keeping_list_container.currentItem = index }
                mFragmentList = mFragmentList + HouseStaffListFragment.newInstance(magicIndicatorContentList[index].int_type)
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
        view_pager_house_keeping_list_container.offscreenPageLimit = 1
        magic_indicator_house_keeping_list.navigator = mMIndicatorNavigator
        ViewPagerHelper.bind(magic_indicator_house_keeping_list, view_pager_house_keeping_list_container)

        //bind fragmentViewPager
        val homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager, mFragmentList)
        view_pager_house_keeping_list_container.adapter = homeViewPagerAdapter

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

    override fun post(runnable: Runnable?) {
    }


}
