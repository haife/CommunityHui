package com.kaiwukj.android.communityhui.mvp.ui.adapter

import android.content.Context
import android.graphics.Typeface
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.HorizontalSpacesDecoration
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.HouseKeepUrl
import com.kaiwukj.android.communityhui.app.constant.StoreListURL
import com.kaiwukj.android.communityhui.mvp.helper.FixLinearSnapHelper
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.ui.fragment.HomeFragment.Companion.EXTRA_KEY_HOME_FRAGMENT_URL
import com.kaiwukj.android.communityhui.mvp.ui.fragment.HouseKeepListFragment.Companion.HOUSE_KEEP_LIST_FRAGMENT
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-16
 *
 */
class HRecommendAdapter(data: MutableList<HRecommendMultiItemEntity>?, val context: Context) : BaseMultiItemQuickAdapter<HRecommendMultiItemEntity, BaseViewHolder>(data) {
    private val typeFaceMediumBold = Typeface.createFromAsset(context.assets, "PingFangSC-Medium-Bold.ttf")
    private val hintDialog: QMUITipDialog = QMUITipDialog.Builder(context).setTipWord(context.getString(R.string.home_service_no_open_hint)).create()
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
//                item.bannerData.forEach {
//                    if (bannerUrls.size == 0) bannerUrls.add(BuildConfig.API_HOST) else return@forEach
//                }
//                var banner: Banner = helper.getView(R.id.banner_home)
//                banner.setImages(bannerUrls)
//                banner.startAutoPlay()
//                banner.setImageLoader(BannerImageLoader())
//                banner.start()

                helper.getView<TextView>(R.id.tv_home_banner_top_house_keeping).setOnClickListener {
                    ARouter.getInstance().build(HouseKeepUrl).withString(ExtraCons.EXTRA_KEY_HOUSE_KEEP, EXTRA_KEY_HOME_FRAGMENT_URL).navigation()
                }
                
                helper.getView<TextView>(R.id.tv_home_banner_top_family).setOnClickListener {
                    showServiceNoOpenDialog()
                }
                helper.getView<TextView>(R.id.tv_home_banner_top_life).setOnClickListener {
                    showServiceNoOpenDialog()
                }
                helper.getView<TextView>(R.id.tv_home_banner_top_refer).setOnClickListener {
                    showServiceNoOpenDialog()
                }
                helper.getView<TextView>(R.id.tv_home_banner_top_all).setOnClickListener {
                }


            }
            HRecommendMultiItemEntity.HOT_SERVICE_TYPE -> {

                processServiceItem(item, helper)

                helper.getView<ImageView>(R.id.iv_home_moon_woman_service).setOnClickListener {
                    launcherHouseKeepList(item.homeServiceList[0])
                }
                helper.getView<ImageView>(R.id.iv_home_carer).setOnClickListener {
                    launcherHouseKeepList(item.homeServiceList[1])
                }
                helper.getView<ImageView>(R.id.iv_home_child_rearing).setOnClickListener {
                    launcherHouseKeepList(item.homeServiceList[2])
                }
                helper.getView<ImageView>(R.id.iv_home_prolactin).setOnClickListener {
                    launcherHouseKeepList(item.homeServiceList[3])
                }
            }

            HRecommendMultiItemEntity.STORES_RECOMMEND -> {
                val shopRv = helper.getView<RecyclerView>(R.id.rv_home_shops_recommend)
                initRecycle(shopRv, item)
                helper.getView<TextView>(R.id.tv_home_shops_recommend_more).setOnClickListener {
                    ARouter.getInstance().build(StoreListURL).withString(ExtraCons.EXTRA_KEY_STORE, EXTRA_KEY_HOME_FRAGMENT_URL).navigation()
                }
            }

            HRecommendMultiItemEntity.WOMAN_RECOMMEND -> {
                val staffRv = helper.getView<RecyclerView>(R.id.rv_home_officer_recommend)
                initRecycle(staffRv, item)
                helper.getView<TextView>(R.id.tv_home_officer_recommend_more).setOnClickListener {
                    ARouter.getInstance().build(HouseKeepUrl).withString(ExtraCons.EXTRA_KEY_HOUSE_KEEP, EXTRA_KEY_HOME_FRAGMENT_URL).navigation()
                }
            }

        }
    }

    //TODO("服务暂未开通")
    private fun showServiceNoOpenDialog() {
        hintDialog.show()
        Handler().postDelayed({ hintDialog?.hide() }, 800)
    }


    /**
     * 推荐横向列表
     * @param recyclerView RecyclerView
     * @param item HRecommendMultiItemEntity
     */
    private fun initRecycle(recyclerView: RecyclerView, item: HRecommendMultiItemEntity) {
        recyclerView.addItemDecoration(HorizontalSpacesDecoration(20))
        val horizontalLM = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val childAdapter = HRecommendChildAdapter(item, context)
        recyclerView.layoutManager = horizontalLM
        recyclerView.onFlingListener = null
        FixLinearSnapHelper().attachToRecyclerView(recyclerView)
        recyclerView.adapter = childAdapter
    }

    /**
     *  处理家政逻辑
     * @param item HRecommendMultiItemEntity
     * @param helper BaseViewHolder
     */
    private fun processServiceItem(item: HRecommendMultiItemEntity, helper: BaseViewHolder) {
        val homeServiceList = item.homeServiceList
        helper.setText(R.id.tv_home_moon_woman_service, homeServiceList[0].dicValue)
                .setText(R.id.tv_home_carer_service, homeServiceList[1].dicValue)
                .setText(R.id.tv_home_child_rearing, homeServiceList[2].dicValue)
                .setText(R.id.tv_home_prolactin, homeServiceList[3].dicValue)
        helper.setTypeface(typeFaceMediumBold, R.id.tv_home_moon_woman_service, R.id.tv_home_carer_service, R.id.tv_home_child_rearing, R.id.tv_home_prolactin)
    }

    /**
     * 跳转到家政列表
     * @param entity HomeServiceEntity
     */
    private fun launcherHouseKeepList(entity: HomeServiceEntity) {
        ARouter.getInstance().build(HouseKeepUrl).withString(ExtraCons.EXTRA_KEY_HOUSE_KEEP_ENTITY, entity.id.toString())
                .withString(ExtraCons.EXTRA_KEY_HOUSE_KEEP, HOUSE_KEEP_LIST_FRAGMENT).navigation()
    }

    /**
     * 释放资源 防止内存泄露
     */
    fun onDestroy() {
        shareRecycledViewPool.clear()
    }


}