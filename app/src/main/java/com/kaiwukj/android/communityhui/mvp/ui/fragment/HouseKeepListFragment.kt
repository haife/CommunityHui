package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerHouseKeepComponent
import com.kaiwukj.android.communityhui.di.module.HouseKeepModule
import com.kaiwukj.android.communityhui.mvp.contract.HouseKeepContract
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreListRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreStaffRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult
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
 * @desc 家政服务人员列表
 */
class HouseKeepListFragment : BaseSwipeBackFragment<HouseKeepPresenter>(), HouseKeepContract.View {

    private var mFragmentList: List<HouseStaffListFragment> = ArrayList()
    var mItemIndex: String = "1"
    private var mBarList = ArrayList<HomeServiceEntity>()
    private var request: StoreStaffRequest = StoreStaffRequest()

    companion object {
        const val HOUSE_KEEP_LIST_FRAGMENT = "HOUSE_KEEP_LIST_FRAGMENT"
        fun newInstance(itemIndex: String, barList: ArrayList<HomeServiceEntity>): HouseKeepListFragment {
            val fragment = HouseKeepListFragment()
            fragment.mItemIndex = itemIndex
            fragment.mBarList = barList
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
        initTopBar()
        initMagicIndicatorView(mBarList)
        cb_price_sort.setOnCheckedChangeListener { compoundButton, b ->
            request.servicePrice = b
            requestData()
        }
        cb_order_number_sort.setOnCheckedChangeListener { compoundButton, b ->
            request.serviceHome = b
            requestData()
        }
        cb_order_age_sort.setOnCheckedChangeListener { compoundButton, b ->
            request.workStartTime = b
            requestData()
        }
        cb_order_grade_sort.setOnCheckedChangeListener { compoundButton, b ->
            request.score = b
            requestData()
        }
    }

    /**
     * 请求fragment数据
     */
    private fun requestData() {
        mFragmentList[view_pager_house_keeping_list_container.currentItem].sortStaffList(request)
    }


    private fun initTopBar() {
        qtb_house_keeping_staff_list.addLeftBackImageButton().setOnClickListener { killMyself() }
        qtb_house_keeping_staff_list.setTitle(getString(R.string.house_keeping_title_str))
    }


    override fun onSelectStaffList(result: List<StaffListResult>) {

    }


    private fun initMagicIndicatorView(magicIndicatorContentList: List<HomeServiceEntity>) {
        val mMIndicatorNavigator = CommonNavigator(context)
        mMIndicatorNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return magicIndicatorContentList.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView = ScaleTransitionPagerTitleView(context)
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                simplePagerTitleView.text = magicIndicatorContentList[index].dicValue
                simplePagerTitleView.width = McaUtils.getScreenWidth(context) / 4
                simplePagerTitleView.normalColor = ContextCompat.getColor(context, R.color.home_color_hot_service_text)
                simplePagerTitleView.selectedColor = ContextCompat.getColor(context, R.color.common_text_dark_color)
                simplePagerTitleView.setOnClickListener { view_pager_house_keeping_list_container.currentItem = index }
                var data = StoreListRequest(0, serviceTypeId = magicIndicatorContentList[index].id.toInt())
                mFragmentList = mFragmentList + HouseStaffListFragment.newInstance(data, 1)
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
        view_pager_house_keeping_list_container.offscreenPageLimit = 4
        magic_indicator_house_keeping_list.navigator = mMIndicatorNavigator
        ViewPagerHelper.bind(magic_indicator_house_keeping_list, view_pager_house_keeping_list_container)

        //bind fragmentViewPager
        val homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager, mFragmentList)
        view_pager_house_keeping_list_container.adapter = homeViewPagerAdapter
        //取下标
        for ((index, title) in mBarList.withIndex()) {
            if (mItemIndex == title.id.toString()) {
                view_pager_house_keeping_list_container.currentItem = index
            }
        }

    }

    override fun onGetServiceList(result: List<HomeServiceEntity>) {
        initMagicIndicatorView(result)
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
