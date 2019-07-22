package com.kaiwukj.android.communityhui.mvp.http.entity.multi

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.HRecommendBannerBean
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-16
 *
 */
data class HRecommendMultiItemEntity(private val typeItemStr: String) : MultiItemEntity {

    var bannerData: List<HRecommendBannerBean> = arrayListOf()

    var homeServiceList: List<HomeServiceEntity> = arrayListOf()

    var recommendStoreList: List<StoreListResult> = arrayListOf()

    var recommendStaffList: List<StaffListResult> = arrayListOf()

    companion object {
        //banner
        const val BANNER_TYPE = 0
        const val STRING_BANNER_TYPE = "banner_type"
        //热门活动
        const val HOT_SERVICE_TYPE = 1
        const val STRING_HOT_SERVICE = "string_hot_service"

        //店铺推荐
        const val STORES_RECOMMEND = 3
        const val STRING_STORES_RECOMMEND = "string_stores_recommend"
        //阿姨推荐
        const val WOMAN_RECOMMEND = 4
        const val STRING_WOMAN_RECOMMEND = "woman_recommend"

        //没有定义的type
        const val NUll_TYPE = -1
    }


    override fun getItemType(): Int = when (typeItemStr) {
        STRING_BANNER_TYPE -> BANNER_TYPE
        STRING_HOT_SERVICE -> HOT_SERVICE_TYPE
        STRING_STORES_RECOMMEND -> STORES_RECOMMEND
        STRING_WOMAN_RECOMMEND -> WOMAN_RECOMMEND
        else -> {
            NUll_TYPE
        }
    }


}