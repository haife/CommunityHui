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
import com.kaiwukj.android.communityhui.di.component.DaggerEditMineInfoComponent
import com.kaiwukj.android.communityhui.di.module.EditMineInfoModule
import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.BouseKeepingServiceType
import com.kaiwukj.android.communityhui.mvp.presenter.EditMineInfoPresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HomeViewPagerAdapter
import com.kaiwukj.android.communityhui.mvp.ui.widget.home.ScaleTransitionPagerTitleView
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.utils.McaUtils
import kotlinx.android.synthetic.main.fragment_mine_collection.*
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
 * @desc  我的收藏
 */
class MineCollectionFragment : BaseSwipeBackFragment<EditMineInfoPresenter>(), EditMineInfoContract.View {
    private var mFragmentList: ArrayList<Fragment> = ArrayList()

    companion object {
        const val MINE_COLLECTION_FRAGMENT = "MINE_COLLECTION_FRAGMENT"
        fun newInstance(): MineCollectionFragment {
            val fragment = MineCollectionFragment()
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_mine_collection, container, false))
    }


    override fun initData(savedInstanceState: Bundle?) {
        val bean1 = BouseKeepingServiceType(1, "护工")
        val bean2 = BouseKeepingServiceType(1, "店铺")
        val list: ArrayList<BouseKeepingServiceType> = arrayListOf()

        list.add(bean1)
        list.add(bean2)
        mFragmentList.add(CollectionStaffListFragment.newInstance(list[0].int_type))
        mFragmentList.add(CollectionStoreListFragment.newInstance())
        initMagicIndicatorView(list)
    }


    private fun initMagicIndicatorView(magicIndicatorContentList: List<BouseKeepingServiceType>) {
        val mMIndicatorNavigator = CommonNavigator(context)
        mMIndicatorNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return magicIndicatorContentList.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView = ScaleTransitionPagerTitleView(context)
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
                simplePagerTitleView.text = magicIndicatorContentList[index].string_name
                simplePagerTitleView.width = McaUtils.getScreenWidth(context) / 2
                simplePagerTitleView.normalColor = ContextCompat.getColor(context, R.color.home_color_hot_service_text)
                simplePagerTitleView.selectedColor = ContextCompat.getColor(context, R.color.common_text_dark_color)
                simplePagerTitleView.setOnClickListener { view_pager_my_collection_container.currentItem = index }
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

        magic_indicator_my_collection.navigator = mMIndicatorNavigator
        ViewPagerHelper.bind(magic_indicator_my_collection, view_pager_my_collection_container)

        //bind fragmentViewPager
        val homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager, mFragmentList)
        view_pager_my_collection_container.adapter = homeViewPagerAdapter

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
        activity?.onBackPressed()
    }
}
