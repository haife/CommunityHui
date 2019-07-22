package com.kaiwukj.android.communityhui.mvp.ui.adapter

import android.content.Context
import android.graphics.Typeface
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/18
 * @desc 我的地址列表
 */
class MineAddressListAdapter(data: MutableList<HRecommendMultiItemEntity>?, layoutId: Int, val context: Context) : BaseQuickAdapter<HRecommendMultiItemEntity, BaseViewHolder>(layoutId, data) {
    private val typeFaceMedium = Typeface.createFromAsset(context.assets, "PingFangSC-Medium-Bold.ttf")
    private val typeFaceLight = Typeface.createFromAsset(context.assets, "PingFangSC-Light-Face.ttf")

    override fun convert(helper: BaseViewHolder, item: HRecommendMultiItemEntity) {
    }


}