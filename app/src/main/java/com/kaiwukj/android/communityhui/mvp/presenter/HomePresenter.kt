package com.kaiwukj.android.communityhui.mvp.presenter

import com.kaiwukj.android.communityhui.mvp.contract.HomeContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity.Companion.STRING_HOT_SERVICE
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity.Companion.STRING_STORES_RECOMMEND
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity.Companion.STRING_WOMAN_RECOMMEND
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreListRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.HomeServiceEntity
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StaffListResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HRecommendAdapter
import com.kaiwukj.android.mcas.di.scope.FragmentScope
import com.kaiwukj.android.mcas.http.imageloader.ImageLoader
import com.kaiwukj.android.mcas.mvp.BasePresenter
import com.kaiwukj.android.mcas.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
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
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var hRecommendMultiItemList: MutableList<HRecommendMultiItemEntity>
    @Inject
    lateinit var mRecommendAdapter: HRecommendAdapter

    /**
     * 首页服务列表
     */
    fun requestServiceList(request: StoreListRequest, isRefresh: Boolean) {
        mModel.requestServiceList()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { mRootView.showLoading() }
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    mRootView.killMyself()
                }
                .subscribe(object : ErrorHandleSubscriber<HomeServiceEntity>(mErrorHandler) {
                    override fun onNext(data: HomeServiceEntity) {
                        if (data.code == Api.RequestSuccess) {
                            processRecommendData(data.result)
                            requestStoreRecommend(request, isRefresh)
                        }
                    }
                    override fun onError(t: Throwable) {
                        mRootView.onResponseError()
                    }
                })
    }


    /**
     * 推荐门店
     * @param request StoreListRequest
     * 是否推荐 0默认，1推荐 不传为默认所有
     */
    fun requestStoreRecommend(request: StoreListRequest, isRefresh: Boolean) {
        request.recommendFlag = 1
        mModel.requestStoreRecommend(request, isRefresh)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<StoreListResult>(mErrorHandler) {
                    override fun onNext(data: StoreListResult) {
                        if (data.code == Api.RequestSuccess) {
                            requestStaffRecommend(request, isRefresh)
                            //推荐门店
                            val recommendStore = HRecommendMultiItemEntity(STRING_STORES_RECOMMEND)
                            recommendStore.recommendStoreList = data.result.list
                            hRecommendMultiItemList.add(recommendStore)
                        }
                    }


                })
    }

    /**
     * 推荐阿姨
     * @param request StoreListRequest
     * 是否推荐 0默认，1推荐 不传为默认所有
     */
    fun requestStaffRecommend(request: StoreListRequest, isRefresh: Boolean) {
        mModel.requestStaffRecommend(request, isRefresh)
                .subscribeOn(Schedulers.io())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .unsubscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread()).subscribe(object : ErrorHandleSubscriber<StaffListResult>(mErrorHandler) {

                    override fun onNext(data: StaffListResult) {
                        if (data.code == Api.RequestSuccess) {
                            //推荐阿姨
                            val recommendStaff = HRecommendMultiItemEntity(STRING_WOMAN_RECOMMEND)
                            recommendStaff.recommendStaffList = data.result.list
                            hRecommendMultiItemList.add(recommendStaff)
                            mRecommendAdapter.notifyDataSetChanged()
                        }
                    }


                })
    }


    /**
     * TODO 处理首页推荐的数据 提供给View层
     */
    fun processRecommendData(list: List<HomeServiceEntity>) {
        val bannerEntity = HRecommendMultiItemEntity(HRecommendMultiItemEntity.STRING_BANNER_TYPE)
        hRecommendMultiItemList.add(bannerEntity)
        //家政服务类型
        val recommendShopEntity = HRecommendMultiItemEntity(STRING_HOT_SERVICE)
        recommendShopEntity.homeServiceList = list
        hRecommendMultiItemList.add(recommendShopEntity)
        mRecommendAdapter.notifyDataSetChanged()
    }


}
