package com.kaiwukj.android.communityhui.mvp.ui.fragment


import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.StoreListURL
import com.kaiwukj.android.communityhui.di.component.DaggerStoreComponent
import com.kaiwukj.android.communityhui.di.module.StoreModule
import com.kaiwukj.android.communityhui.mvp.contract.StoreContract
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.BouseKeepingServiceType
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreListRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreDetailResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.communityhui.mvp.presenter.StorePresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HomeViewPagerAdapter
import com.kaiwukj.android.communityhui.mvp.ui.widget.home.ScaleTransitionPagerTitleView
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.utils.McaUtils
import kotlinx.android.synthetic.main.fragment_store_sort.*
import kotlinx.android.synthetic.main.include_store_sort_header.*
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
 * @desc 门店一级推荐
 */
class StoreSortListFragment : BaseSwipeBackFragment<StorePresenter>(), StoreContract.View {

    private var mFragmentList: ArrayList<Fragment> = ArrayList()
    //0默认，1推荐
    private var request: StoreListRequest = StoreListRequest(RECOMMEND_FALG)
    private var mShopId: Int? = null

    companion object {
        const val STORE_SORT_LIST_FRAGMENT = "STORE_SORT_LIST_FRAGMENT"
        const val RECOMMEND_FALG = 1
        fun newInstance(shopId: Int?): StoreSortListFragment {
            val fragment = StoreSortListFragment()
            fragment.mShopId = shopId
            return fragment
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerStoreComponent
                .builder()
                .appComponent(appComponent)
                .storeModule(StoreModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_store_sort, container, false))
    }

    override fun initData(savedInstanceState: Bundle?) {
        initTopBar()
        request.hmstoreId = mShopId
        mShopId?.let { mPresenter?.requestStoreDetail(it) }
        //查看门店详情
        tv_store_sort_header_look_detail.setOnClickListener {
            ARouter.getInstance().build(StoreListURL).withString(ExtraCons.EXTRA_KEY_STORE, STORE_SORT_LIST_FRAGMENT)
                    .withString(ExtraCons.EXTRA_KEY_STORE_SHOP_ID, mShopId.toString()).navigation()
        }
    }

    private fun initTopBar() {
        qtb_store_list_sort.addLeftBackImageButton().setOnClickListener { killMyself() }
        qtb_store_list_sort.setTitle(getString(R.string.store_title))
    }

    /**
     * 获取门店详情
     * @param detailResult StoreDetailResult
     */
    override fun onGetStoreDetail(detailResult: StoreDetailResult) {
        tv_store_sort_header.text = detailResult.storeName
        tv_store_sort_header_address.text = detailResult.address
        cb_store_sort_header_address.isChecked = detailResult.favoriteFlag == 1
        val listTab = ArrayList<BouseKeepingServiceType>()
        val itemRecommend = BouseKeepingServiceType(0, getString(R.string.home_shops_recommend_desc))
        listTab.add(itemRecommend)
        //请求门店下推荐技工实体类
        val recommend = StoreListRequest(1, serviceTypeId = null, hmstoreId = mShopId)
        mFragmentList.add(HouseStaffListFragment.newInstance(recommend, 2))
        val itemAll = BouseKeepingServiceType(0, getString(R.string.mine_order_all))
        listTab.add(itemAll)
        //请求门店下所有技工实体类
        val all = StoreListRequest(null, serviceTypeId = null, hmstoreId = mShopId)
        mFragmentList.add(HouseStaffListFragment.newInstance(all, 2))

        for ((index, element) in detailResult.storeSortResponseList.withIndex()) {
            val itemAll = BouseKeepingServiceType(element.serviceTypeId, element.serviceName)
            listTab.add(itemAll)
            val itemType = StoreListRequest(recommendFlag = null, serviceTypeId = element.serviceTypeId, hmstoreId = mShopId)
            mFragmentList.add(HouseStaffListFragment.newInstance(itemType, 2))
        }

        initMagicIndicatorView(listTab)
    }

    override fun getContextView(): Context? = context


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
                simplePagerTitleView.width = McaUtils.getScreenWidth(context) / 4
                simplePagerTitleView.typeface = Typeface.createFromAsset(context.assets, "PingFangSC-Medium-Bold.ttf")
                simplePagerTitleView.normalColor = ContextCompat.getColor(context, R.color.home_color_hot_service_text)
                simplePagerTitleView.selectedColor = ContextCompat.getColor(context, R.color.common_text_dark_color)
                simplePagerTitleView.setOnClickListener { view_pager_store_sort_list_container.currentItem = index }
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
        view_pager_store_sort_list_container.offscreenPageLimit = 1
        magic_indicator_store_sort_list.navigator = mMIndicatorNavigator
        ViewPagerHelper.bind(magic_indicator_store_sort_list, view_pager_store_sort_list_container)

        //bind fragmentViewPager
        val homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager, mFragmentList)
        view_pager_store_sort_list_container.adapter = homeViewPagerAdapter

    }

    override fun onGetStoreRecommend(list: StoreListResult) {

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
