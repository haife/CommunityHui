package com.kaiwukj.android.communityhui.mvp.ui.adapter

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kaiwukj.android.communityhui.BuildConfig
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.HouseKeepUrl
import com.kaiwukj.android.communityhui.app.constant.StoreListURL
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity
import com.kaiwukj.android.communityhui.mvp.ui.fragment.HomeFragment.Companion.EXTRA_KEY_HOME_FRAGMENT_URL
import com.kaiwukj.android.communityhui.utils.BannerImageLoader
import com.youth.banner.Banner

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-16
 *
 */
class HRecommendAdapter(data: MutableList<HRecommendMultiItemEntity>?, val context: Context) : BaseMultiItemQuickAdapter<HRecommendMultiItemEntity, BaseViewHolder>(data) {
    private val typeFaceMedium = Typeface.createFromAsset(context.assets, "PingFangSC-Medium-Bold.ttf")
    private val typeFaceLight = Typeface.createFromAsset(context.assets, "PingFangSC-Light-Face.ttf")
    private val bannerUrls: ArrayList<String> = arrayListOf()
    //private var recommendRestaurantAdapter: HRecommendChildAdapter? = null

    //RecycleView线程池
    private val shareRecycledViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    init {
        addItemType(HRecommendMultiItemEntity.BANNER_TYPE, R.layout.recycle_item_home_banner_top_layout)
        addItemType(HRecommendMultiItemEntity.HOT_SERVICE_TYPE, R.layout.recycle_item_home_hot_services_layout)
        addItemType(HRecommendMultiItemEntity.STORES_RECOMMEND, R.layout.recycle_item_home_shops_recommend_layout)
        addItemType(HRecommendMultiItemEntity.WOMAN_RECOMMEND, R.layout.recycle_item_home_officer_recommend_layout)
    }


    override fun convert(helper: BaseViewHolder, item: HRecommendMultiItemEntity) {
        when (item.itemType) {
            HRecommendMultiItemEntity.BANNER_TYPE -> {
                item.bannerData.forEach {
                    if (bannerUrls.size == 0) bannerUrls.add(BuildConfig.API_HOST) else return@forEach
                }
                var banner: Banner = helper.getView(R.id.banner_home)
                banner.setImages(bannerUrls)
                banner.startAutoPlay()
                banner.setImageLoader(BannerImageLoader())
                banner.start()

                helper.getView<TextView>(R.id.tv_home_banner_top_house_keeping).setOnClickListener {
                    ARouter.getInstance().build(HouseKeepUrl).withString(ExtraCons.EXTRA_KEY_HOUSE_KEEP,EXTRA_KEY_HOME_FRAGMENT_URL).navigation()
                }

            }
            HRecommendMultiItemEntity.HOT_SERVICE_TYPE -> {

            }

            HRecommendMultiItemEntity.STORES_RECOMMEND -> {
                helper.getView<TextView>(R.id.tv_home_shops_recommend_more).setOnClickListener {
                    ARouter.getInstance().build(StoreListURL).navigation()
                }
            }


            HRecommendMultiItemEntity.WOMAN_RECOMMEND -> {
                helper.getView<TextView>(R.id.tv_home_officer_recommend_more).setOnClickListener {
                    ARouter.getInstance().build(HouseKeepUrl).withString(ExtraCons.EXTRA_KEY_HOUSE_KEEP,EXTRA_KEY_HOME_FRAGMENT_URL).navigation()
                }
            }

        }

        /**
         * 添加横向滚动适配器方法
         * @param helper BaseViewHolder
         * @param adapter HRecommendChildAdapter?
         * @param itemType Int
         */
        /* private fun setItemAdapter(helper: BaseViewHolder, adapter: HRecommendChildAdapter?, itemType: Int) {
             val recommendRestaurantRv: RecyclerView = helper.getView(R.id.rv_recommend_parent_container)
             val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
             //TODO 横向嵌套RecyclerView滑动数据预加载 注意只适合横向嵌套
             linearLayoutManager.initialPrefetchItemCount = 5
             //TODO:Item的高度是固定的，设置这个选项可以提高性能.总得来说就是就是避免整个布局绘制。就是避免requestLayout.
             recommendRestaurantRv.setHasFixedSize(true)
             //TODO: 复用RecycledViewPool
             recommendRestaurantRv.setRecycledViewPool(shareRecycledViewPool)

             recommendRestaurantRv.layoutManager = linearLayoutManager
             recommendRestaurantRv.adapter = adapter

             recommendRestaurantRv.addItemDecoration(HorizontalSpacesItemDecoration(42))
             //横向滚动滑动定位
             when (itemType) {
                 HRecommendMultiItemEntity.TASTE_OF_LIFE -> PagerSnapHelper().attachToRecyclerView(recommendRestaurantRv)
                 else -> FixLinearSnapHelper().attachToRecyclerView(recommendRestaurantRv)
             }
         }*/




        /**
         * 释放资源 防止内存泄露
         */
        fun onDestroy() {
            shareRecycledViewPool.clear()
        }


    }


}