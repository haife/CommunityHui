package com.kaiwukj.android.communityhui.mvp.ui.adapter

import android.content.Context
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.mvp.http.entity.result.StoreListResult
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-16
 *
 */
class StoreListAdapter(data: ArrayList<StoreListResult>, layoutId: Int, val context: Context) : BaseQuickAdapter<StoreListResult, BaseViewHolder>(layoutId, data) {

    override fun convert(helper: BaseViewHolder, item: StoreListResult) {
        val iv = helper.getView<ImageView>(R.id.iv_store_list)
        var shopTypeName: String? = ""
        var oneStr: String? = ""
        for ((index, str: StoreListResult.StoreSortResponseListBean) in item.storeSortResponseList.withIndex()) {
            if (index == 0)
                oneStr = str.serviceName
            else shopTypeName = "$shopTypeName\t|\t${str.serviceName}"
        }
        helper.setText(R.id.iv_store_list_name, item.storeName).setText(R.id.iv_store_list_price, item.address)
        helper.setText(R.id.iv_store_list_message, oneStr + shopTypeName)
        GlideArms.with(context).load(item.storeLogoImg).centerCrop().into(iv)

    }


}