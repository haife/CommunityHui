package com.kaiwukj.android.communityhui.mvp.presenter

import android.app.Application
import com.kaiwukj.android.communityhui.mvp.contract.HomeContract
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity.Companion.STRING_BANNER_TYPE
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity.Companion.STRING_HOT_SERVICE
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity.Companion.STRING_STORES_RECOMMEND
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity.Companion.STRING_WOMAN_RECOMMEND
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeRecommendData
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HRecommendAdapter
import com.kaiwukj.android.mcas.di.scope.FragmentScope
import com.kaiwukj.android.mcas.http.imageloader.ImageLoader
import com.kaiwukj.android.mcas.integration.AppManager
import com.kaiwukj.android.mcas.mvp.BasePresenter
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc
 */
@FragmentScope
class HomePresenter
@Inject
constructor(model: HomeContract.Model, rootView: HomeContract.View) :
        BasePresenter<HomeContract.Model, HomeContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager
    @Inject
    lateinit var mHomeRecommendData: HomeRecommendData
    @Inject
    lateinit var hRecommendMultiItemList: MutableList<HRecommendMultiItemEntity>
    @Inject
    lateinit var mRecommendAdapter: HRecommendAdapter

    /**
     * TODO 处理首页推荐的数据 提供给View层
     */
    fun processRecommendData() {
        //判断banner是否为空
        if (mHomeRecommendData.arr_index_banner_data != null && mHomeRecommendData.arr_index_banner_data.size !== 0) {
            val bannerEntity = HRecommendMultiItemEntity(STRING_BANNER_TYPE)
            hRecommendMultiItemList.add(bannerEntity)
        }
        //实体类处理
        val recommendShopEntity = HRecommendMultiItemEntity(STRING_HOT_SERVICE)
        hRecommendMultiItemList.add(recommendShopEntity)
        //实体类处理
        val bean = HRecommendMultiItemEntity(STRING_STORES_RECOMMEND)
        hRecommendMultiItemList.add(bean)
        //实体类处理
        val bean2 = HRecommendMultiItemEntity(STRING_WOMAN_RECOMMEND)
        hRecommendMultiItemList.add(bean2)
        mRecommendAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
