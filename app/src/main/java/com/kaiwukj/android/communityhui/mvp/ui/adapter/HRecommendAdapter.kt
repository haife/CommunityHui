package com.kaiwukj.android.communityhui.mvp.ui.adapter

import android.content.Context
import android.graphics.Typeface
import android.widget.ImageView
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
import com.kaiwukj.android.communityhui.utils.DiskCacheStrategyType
import com.kaiwukj.android.mcas.http.imageloader.ImageConfigImpl
import com.kaiwukj.android.mcas.http.imageloader.ImageLoader
import com.kaiwukj.android.mcas.utils.McaUtils
import com.youth.banner.Banner
import java.io.Serializable

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-16
 *
 */
class HRecommendAdapter(data: MutableList<HRecommendMultiItemEntity>?, val context: Context) : BaseMultiItemQuickAdapter<HRecommendMultiItemEntity, BaseViewHolder>(data) {
    private val typeFaceMediumBold = Typeface.createFromAsset(context.assets, "PingFangSC-Medium-Bold.ttf")
    private val typeFaceLight = Typeface.createFromAsset(context.assets, "PingFangSC-Light-Face.ttf")
    private var imageLoader: ImageLoader? = McaUtils.obtainAppComponentFromContext(context).imageLoader()
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
                    val list  = item.homeServiceList
                    ARouter.getInstance().build(HouseKeepUrl).withSerializable(ExtraCons.EXTRA_KEY_HOUSE_KEEP_ENTITY, list as Serializable)
                            .withString(ExtraCons.EXTRA_KEY_HOUSE_KEEP, EXTRA_KEY_HOME_FRAGMENT_URL).navigation()
                }

            }
            HRecommendMultiItemEntity.HOT_SERVICE_TYPE -> {
                processServiceItem(item, helper)
            }

            HRecommendMultiItemEntity.STORES_RECOMMEND -> {
                helper.getView<TextView>(R.id.tv_home_shops_recommend_more).setOnClickListener {
                    ARouter.getInstance().build(StoreListURL).withString(ExtraCons.EXTRA_KEY_STORE, EXTRA_KEY_HOME_FRAGMENT_URL).navigation()
                }
            }


            HRecommendMultiItemEntity.WOMAN_RECOMMEND -> {
                helper.getView<TextView>(R.id.tv_home_officer_recommend_more).setOnClickListener {
                    ARouter.getInstance().build(HouseKeepUrl).withString(ExtraCons.EXTRA_KEY_HOUSE_KEEP, EXTRA_KEY_HOME_FRAGMENT_URL).navigation()
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


    }

    /**
     *
     * @param item HRecommendMultiItemEntity
     * @param helper BaseViewHolder
     */
    private fun processServiceItem(item: HRecommendMultiItemEntity, helper: BaseViewHolder) {
        val homeServiceList = item.homeServiceList
        helper.setText(R.id.tv_home_moon_woman_service, homeServiceList[0].dicValue)
                .setText(R.id.tv_home_carer_service, homeServiceList[1].dicValue)
                .setText(R.id.tv_home_child_rearing, homeServiceList[2].dicValue)
                .setText(R.id.tv_home_prolactin, homeServiceList[3].dicValue)
        val moonIv = helper.getView<ImageView>(R.id.iv_home_moon_woman_service)
        if (homeServiceList[0].img.isNotEmpty())
        loadImage(moonIv, homeServiceList[0].img)
        val carerIv = helper.getView<ImageView>(R.id.iv_home_carer)
        if (homeServiceList[1].img.isNotEmpty())
        loadImage(carerIv, homeServiceList[1].img)
        val rearingIv = helper.getView<ImageView>(R.id.iv_home_child_rearing)
        if (homeServiceList[2].img.isNotEmpty())
        loadImage(rearingIv, homeServiceList[2].img)
        val proIv = helper.getView<ImageView>(R.id.iv_home_prolactin)
        if (homeServiceList[3].img.isNotEmpty())
        loadImage(proIv, homeServiceList[3].img)
        helper.setTypeface(typeFaceMediumBold, R.id.tv_home_moon_woman_service, R.id.tv_home_carer_service, R.id.tv_home_child_rearing, R.id.tv_home_prolactin)
    }


    /**
     * 加载图片
     * @param intoIv ImageView
     * @param drawableRes Int
     * @param imageUrl String
     */
    fun loadImage(intoIv: ImageView, imageUrl: String, isRadius: Boolean = false) {
        imageLoader?.loadImage(context, ImageConfigImpl.builder().url(imageUrl)
                .cacheStrategy(DiskCacheStrategyType.All).imageView(intoIv)
                .isCenterCrop(true)
                .build())
    }


    /**
     * 释放资源 防止内存泄露
     */
    fun onDestroy() {
        shareRecycledViewPool.clear()
    }


}