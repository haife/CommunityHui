package com.kaiwukj.android.communityhui.mvp.ui.adapter

import android.content.Context
import android.graphics.Typeface
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.request.MineCollectionResult
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms

/**
 * @author Haife Android Developer
 * @company KW | 开物
 * @since 2019-07-16
 *
 */
class CollectionListAdapter(data: ArrayList<MineCollectionResult>?, layoutId: Int, val context: Context) : BaseQuickAdapter<MineCollectionResult, BaseViewHolder>(layoutId, data) {
    private val typeFaceMedium = Typeface.createFromAsset(context.assets, "PingFangSC-Medium-Bold.ttf")
    private val typeFaceLight = Typeface.createFromAsset(context.assets, "PingFangSC-Light-Face.ttf")

    override fun convert(helper: BaseViewHolder, item: MineCollectionResult) {
        when (item.id) {

            1 -> {
                val image = helper.getView<ImageView>(R.id.iv_collection_staff_list)
                //收藏的护工
                GlideArms.with(context).load(Api.IMG_URL + item.icon).centerCrop().into(image)
                helper.setText(R.id.iv_collection_staff_list_name, item.name)
                        .setText(R.id.iv_collection_staff_list_message, String.format(context.getString(R.string.home_format_order_detail_message), item.workLong, item.age, item.name))
                        .setText(R.id.iv_collection_staff_list_order_number, String.format(context.getString(R.string.home_format_staff_order_number), item.volume))
            }

            2 -> {
                // 收藏的门店
                val image = helper.getView<ImageView>(R.id.iv_store_list_collection)
                val tvTags = helper.getView<TextView>(R.id.iv_store_list_collection_message)
                var shopTypeName: String? = ""
                var oneStr: String? = ""
                for ((index, string) in item.serviceNames.withIndex()) {
                    if (index == 0) {
                        oneStr = string
                    } else {
                        shopTypeName = "$shopTypeName\t|\t$string"
                    }

                }
                tvTags.text = oneStr + shopTypeName
                GlideArms.with(context).load(Api.IMG_URL + item.icon).centerCrop().into(image)
                helper.setText(R.id.iv_store_list_collection_name, item.name)
                        .setText(R.id.iv_store_list_collection_address, item.address)
                        .setText(R.id.tv_store_list_order_number, String.format(context.getString(R.string.home_format_staff_order_number), item.volume))

            }
        }

    }


}