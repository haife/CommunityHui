package com.kaiwukj.android.communityhui.mvp.ui.adapter

import android.content.Context
import android.graphics.Typeface
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-16
 *
 */
class StoreListAdapter(data: ArrayList<StoreListResult>, layoutId: Int, val context: Context) : BaseQuickAdapter<StoreListResult, BaseViewHolder>(layoutId, data) {
    private val typeFaceMedium = Typeface.createFromAsset(context.assets, "PingFangSC-Medium-Bold.ttf")

    override fun convert(helper: BaseViewHolder, item: StoreListResult) {
        var shopTypeName: String? = ""
        var oneStr: String? = ""
        for ((index, str: StoreListResult.StoreSortResponseListBean) in item.storeSortResponseList.withIndex()) {
            if (index == 0)
                oneStr = str.serviceName
            else shopTypeName = "$shopTypeName\t|\t${str.serviceName}"
        }
        helper.setText(R.id.iv_store_list_name, item.storeName).setTypeface(typeFaceMedium).setText(R.id.iv_store_list_price, item.address)
        helper.setText(R.id.iv_store_list_message, oneStr + shopTypeName)
    }


}