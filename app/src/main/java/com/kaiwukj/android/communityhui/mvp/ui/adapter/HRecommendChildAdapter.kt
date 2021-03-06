package com.kaiwukj.android.communityhui.mvp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.constant.AppointmentUrl
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.app.constant.StoreListURL
import com.kaiwukj.android.communityhui.mvp.http.api.Api
import com.kaiwukj.android.communityhui.mvp.http.entity.multi.HRecommendMultiItemEntity
import com.kaiwukj.android.communityhui.mvp.ui.fragment.AppointmentPersonInfoFragment
import com.kaiwukj.android.communityhui.mvp.ui.fragment.StoreSortListFragment
import com.kaiwukj.android.communityhui.utils.DiskCacheStrategyType
import com.kaiwukj.android.mcas.http.imageloader.ImageConfigImpl
import com.kaiwukj.android.mcas.http.imageloader.ImageLoader
import com.kaiwukj.android.mcas.utils.McaUtils

class HRecommendChildAdapter(val list: HRecommendMultiItemEntity, val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mViewType: Int by lazy { list.itemType }
    private var imageLoader: ImageLoader? = McaUtils.obtainAppComponentFromContext(context).imageLoader()
    private val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                HRecommendMultiItemEntity.STORES_RECOMMEND -> {
                    val itemView: View = layoutInflater.inflate(R.layout.recycle_item_home_shops_recommend_item_layout, null)
                    RecommendStoreViewHolder(itemView)
                }

                HRecommendMultiItemEntity.WOMAN_RECOMMEND -> {
                    val itemView: View = layoutInflater.inflate(R.layout.recycle_item_home_officer_recommend_item_layout, null)
                    RecommendStaffViewHolder(itemView)
                }
                else -> throw NullPointerException()
            }


    override fun getItemCount(): Int = when (mViewType) {
        HRecommendMultiItemEntity.STORES_RECOMMEND -> list.recommendStoreList.size
        HRecommendMultiItemEntity.WOMAN_RECOMMEND -> list.recommendStaffList.size
        else -> 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecommendStoreViewHolder -> bindShopRecommendViewHolder(holder, position)
            is RecommendStaffViewHolder -> bindStaffRecommendViewHolder(holder, position)
        }
    }


    private fun bindShopRecommendViewHolder(holder: RecommendStoreViewHolder, position: Int) {
        val storeListResult = list.recommendStoreList[position]
        loadImage(holder.recommendShopIv, storeListResult.storeLogoImg)
        holder.recommendShopNameTv.text = storeListResult.storeName
        holder.recommendShopAddressTv.text = storeListResult.address
        holder.itemView.setOnClickListener {
            ARouter.getInstance().build(StoreListURL).withString(ExtraCons.EXTRA_KEY_STORE, StoreSortListFragment.FRAGMENT_KEY_STORE_SORT_LIST)
                    .withString(ExtraCons.EXTRA_KEY_STORE_SHOP_ID, storeListResult.id.toString()).navigation()
        }
    }

    private fun bindStaffRecommendViewHolder(holder: RecommendStaffViewHolder, position: Int) {
        val staffListResult = list.recommendStaffList[position]
        loadImage(holder.recommendStaffIv, staffListResult.avatar)
        holder.recommendStaffNameTv.text = staffListResult.realName
        holder.recommendStaffGradeTv.text = String.format(context.getString(R.string.home_format_grade), staffListResult.avgScore)
        holder.recommendStaffMessageTv.text = String.format(context.getString(R.string.home_format_staff_message),
                staffListResult.worktime, staffListResult.age, staffListResult.nativePlace)
        holder.itemView.setOnClickListener {
            ARouter.getInstance().build(AppointmentUrl)
                    .withString(ExtraCons.EXTRA_KEY_HOUSE_KEEP, AppointmentPersonInfoFragment.APPOINTMENT_PERSON_INFO_FRAGMENT)
                    .withString(ExtraCons.EXTRA_KEY_STAFF_SETVIE_TYPE_ID, staffListResult.serviceTypeId.toString())
                    .withString(ExtraCons.EXTRA_KEY_STAFF_USER_ID, staffListResult.storeemployeeId.toString()).navigation()
        }
    }

    /**
     * 加载图片
     * @param intoIv ImageView
     * @param drawableRes Int
     * @param imageUrl String
     */
    private fun loadImage(intoIv: ImageView, imageUrl: String) {
        imageLoader?.loadImage(context, ImageConfigImpl.builder().url(Api.IMG_URL + imageUrl)
                .cacheStrategy(DiskCacheStrategyType.All).imageView(intoIv)
                .isCenterCrop(true)
                .build())
    }

    /**
     * Recycle Item 类型
     * @param position Int
     * @return Int
     */
    override fun getItemViewType(position: Int): Int {
        return mViewType
    }

    class RecommendStoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recommendShopIv: ImageView = itemView!!.findViewById(R.id.iv_shops_recommend)
        var recommendShopNameTv: TextView = itemView!!.findViewById(R.id.tv_home_shops_recommend_name)
        var recommendShopAddressTv: TextView = itemView!!.findViewById(R.id.tv_home_shops_recommend_address)
    }

    class RecommendStaffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recommendStaffIv: ImageView = itemView!!.findViewById(R.id.iv_home_officer_recommend)
        var recommendStaffNameTv: TextView = itemView!!.findViewById(R.id.tv_home_officer_recommend_name)
        var recommendStaffGradeTv: TextView = itemView!!.findViewById(R.id.tv_home_officer_grade)
        var recommendStaffMessageTv: TextView = itemView!!.findViewById(R.id.tv_home_officer_message)
    }

}


