package com.kaiwukj.android.communityhui.mvp.ui.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerStoreComponent
import com.kaiwukj.android.communityhui.di.module.StoreModule
import com.kaiwukj.android.communityhui.mvp.contract.StoreContract
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.request.CollectionRequest
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreDetailResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.communityhui.mvp.presenter.StorePresenter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms
import kotlinx.android.synthetic.main.fragment_store_detail.*
import kotlinx.android.synthetic.main.include_store_detail_header.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/16
 * @desc 门店详情
 */
class StoreDetailFragment : BaseSwipeBackFragment<StorePresenter>(), StoreContract.View {
    var mShopId: Int? = null
    //  1技工 2门店
    val mTypeId: Int = 2

    companion object {
        fun newInstance(shopId: Int?): StoreDetailFragment {
            val fragment = StoreDetailFragment()
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
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_store_detail, container, false))
    }

    override fun initData(savedInstanceState: Bundle?) {
        initTopBar()
        mShopId?.let {
            mPresenter?.requestStoreDetail(it)
        }
        val requestCollection = mShopId?.let { CollectionRequest(it, mTypeId) }

        cb_store_detail_header_address.setOnCheckedChangeListener { compoundButton, b ->

            requestCollection?.let {
                if (b) {
                    mPresenter?.requestAddCollection(requestCollection)
                } else {
                    mPresenter?.requestMoveCollection(requestCollection)
                }
            }


        }
    }

    private fun initTopBar() {
        qtb_store_detail.addLeftBackImageButton().setOnClickListener { killMyself() }
        qtb_store_detail.setTitle(getString(R.string.store_detail_title))

    }

    override fun onGetStoreDetail(detailResult: StoreDetailResult) {
        tv_store_detail_header.text = detailResult.storeName
        tv_store_detail_header_address.text = detailResult.address
        cb_store_detail_header_address.isChecked = detailResult.favoriteFlag == 1
        context?.let { GlideArms.with(it).load(Api.IMG_URL + detailResult.licenseImg).centerCrop().into(iv_store_detail_license) }
    }

    override fun onRefreshFinish() {
    }

    override fun onLoadMoreFinish() {
    }


    override fun getContextView(): Context? = context


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
