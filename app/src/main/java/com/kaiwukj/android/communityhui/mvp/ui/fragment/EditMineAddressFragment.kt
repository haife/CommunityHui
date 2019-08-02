package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.google.gson.Gson
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.di.component.DaggerEditMineInfoComponent
import com.kaiwukj.android.communityhui.di.module.EditMineInfoModule
import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.http.entity.bean.JsonBean
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineCollectionResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.presenter.EditMineInfoPresenter
import com.kaiwukj.android.communityhui.utils.GetJsonDataUtil
import com.kaiwukj.android.communityhui.utils.InputMethodUtils
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.utils.McaUtils
import com.qmuiteam.qmui.widget.QMUITopBar
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import kotlinx.android.synthetic.main.fragment_edit_mine_address.*
import me.yokeyword.fragmentation.ISupportFragment
import org.json.JSONArray
import java.util.regex.Pattern


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  服务评价
 */
class EditMineAddressFragment : BaseSwipeBackFragment<EditMineInfoPresenter>(), EditMineInfoContract.View {
    var myAddressResult = MyAddressResult()
    private var hintDialog: QMUITipDialog? = null
    private var options1Items: List<JsonBean> = ArrayList()
    private val options2Items = ArrayList<List<Any>>()
    private val options3Items = ArrayList<List<List<Any>>>()
    private var thread: Thread? = null
    private val MSG_LOAD_DATA = 0x0001
    private val MSG_LOAD_SUCCESS = 0x0002
    private val MSG_LOAD_FAILED = 0x0003
    //判断是新增地址还是编辑地址
    private var isEditAddress = true
    private var isLoaded = false
    //是否是预约跳转过来
    private var isFromToAppointment = false

    companion object {
        const val EDIT_MINE_ADDRESS_FRAGMENT = "EDIT_MINE_ADDRESS_FRAGMENT"

        fun newInstance(myAddressResult: MyAddressResult): EditMineAddressFragment {
            val fragment = EditMineAddressFragment()
            fragment.myAddressResult = myAddressResult
            fragment.isFromToAppointment = myAddressResult.isFromToAppointment
            return fragment
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerEditMineInfoComponent
                .builder()
                .appComponent(appComponent)
                .editMineInfoModule(EditMineInfoModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return attachToSwipeBack(inflater.inflate(R.layout.fragment_edit_mine_address, container, false))
    }


    override fun initData(savedInstanceState: Bundle?) {
        initClick()
        mHandler.sendEmptyMessage(MSG_LOAD_DATA)
        if (!McaUtils.isEmpty(myAddressResult.name)) {
            //编辑地址
            custom_edit_address_name.setEdText(myAddressResult.name)
            custom_edit_address_phone.setEdText(myAddressResult.phone)
            custom_edit_address_detail.setEdText(myAddressResult.address)
            tv_edit_address_city.text = myAddressResult.area
            isEditAddress = true
        } else {
            isEditAddress = false
        }

        qbtn_save_address.setOnClickListener {

            val name = custom_edit_address_name.contentText
            if (McaUtils.isEmpty(name)) {
                showMessage("请填写姓名")
                return@setOnClickListener
            }
            myAddressResult.name = name


            val phone = custom_edit_address_phone.contentText
            if (McaUtils.isEmpty(phone)) {
                showMessage("请输入手机号")
                return@setOnClickListener
            }
            myAddressResult.phone = phone

            val area = tv_edit_address_city.text.toString()
            val p: Pattern = Pattern.compile("\\s*|\t|\r|\n")
            val matcher = p.matcher(area)
            val address = matcher.replaceAll("")
            if (McaUtils.isEmpty(area)) {
                showMessage("请选择服务地点")
                return@setOnClickListener
            }
            myAddressResult.area = address
            val detailAddress = custom_edit_address_detail.contentText
            if (McaUtils.isEmpty(detailAddress)) {
                showMessage("请输入详细地址")
                return@setOnClickListener
            }
            myAddressResult.address = detailAddress
            it.isEnabled = false
            if (isEditAddress) {
                //编辑地址
                mPresenter?.upDateMyAddress(myAddressResult)
            } else {
                //新增地址
                mPresenter?.addMyAddress(myAddressResult)
                if (isFromToAppointment) {

                }
            }

        }
    }

    private fun initClick() {

        ll_edit_address_detail.setOnClickListener {
            InputMethodUtils.hideSoftInput(it)
            showPickerView()
        }
    }


    override fun onSupportVisible() {
        if (isFromToAppointment) {
            qtb_mine_address.visibility = View.VISIBLE
            qtb_mine_address.setTitle(getString(R.string.mine_address_title))
            qtb_mine_address.addLeftBackImageButton().setOnClickListener { activity?.onBackPressed() }
        } else {
            qtb_mine_address.visibility = View.GONE
            activity?.findViewById<QMUITopBar>(R.id.qtb_edit_mine_info)?.setTitle(getString(R.string.edit_mine_address))
        }
    }


    override fun onGetMyCollectionData(list: List<MineCollectionResult>) {
    }

    override fun onGetMyAddressList(result: MyAddressResult) {
    }

    override fun post(runnable: Runnable?) {
    }


    override fun showLoading() {
        //请求成功的回调函数
        val tipDialog = QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord(getString(R.string.mine_address_edit_success))
                .create()

        tipDialog.show()

        Handler().postDelayed({
            tipDialog?.dismiss()
            val bundle = Bundle()
            bundle.putSerializable(ExtraCons.EXTRA_KEY_CHOICE_ADDRESS, myAddressResult)
            setFragmentResult(ISupportFragment.RESULT_OK, bundle)
            killMyself()
        }, 800)
    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        hintDialog = QMUITipDialog.Builder(context).setTipWord(message).create()
        hintDialog?.setTitle(message)
        hintDialog?.show()
        Handler().postDelayed({
            hintDialog?.dismiss()
            if (message == getString(com.kaiwukj.android.communityhui.R.string.social_post_card_success)) {
                killMyself()
            }
        }, 800)
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun killMyself() {
        activity?.onBackPressed()
    }


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_LOAD_DATA -> if (thread == null) {

                    thread = Thread(Runnable {
                        // 子线程中解析省市区数据
                        initJsonData()
                    })
                    thread!!.start()
                }

                MSG_LOAD_SUCCESS -> {
                    isLoaded = true
                }

                MSG_LOAD_FAILED -> {
                }
            }
        }
    }


    private fun showPickerView() {// 弹出选择器
        OptionsPickerBuilder(context, OnOptionsSelectListener { options1, options2, options3, v ->
            //返回的分别是三个级别的选中位置
            val opt1tx = if (options1Items.isNotEmpty())
                options1Items[options1].pickerViewText
            else
                ""
            val opt2tx = if (options2Items.size > 0 && options2Items[options1].isNotEmpty())
                options2Items[options1][options2]
            else
                ""
            val opt3tx = if (options2Items.size > 0
                    && options3Items[options1].isNotEmpty()
                    && options3Items[options1][options2].isNotEmpty()) {
                options3Items[options1][options2][options3]
            } else
                ""
            tv_edit_address_city.text = "$opt1tx\t$opt2tx\t$opt3tx"
        })
                .setTitleText(getString(R.string.mine_address_choice_area))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)
                .setDividerColor(ContextCompat.getColor(context!!, R.color.common_divide_line_color))
                .setContentTextSize(16)
                .setSubmitColor(ContextCompat.getColor(context!!, R.color.app_color_theme))
                .setCancelColor(ContextCompat.getColor(context!!, R.color.common_text_slight_color))
                .setSelectOptions(13, 0, 0)
                .setLineSpacingMultiplier(2.0f)

                .build<Any>().also {
                    it.setPicker(options1Items, options2Items, options3Items)//三级选择器
                    it.show()
                }
    }

    private fun initJsonData() {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         */
        val JsonData = GetJsonDataUtil().getJson(context, "province.json")//获取assets目录下的json文件数据

        val jsonBean = parseData(JsonData)//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean

        for (i in 0 until jsonBean.size) {//遍历省份
            val cityList = ArrayList<String>()//该省的城市列表（第二级）
            val province_AreaList = ArrayList<ArrayList<String>>()//该省的所有地区列表（第三极）

            for (c in 0 until jsonBean[i].cityList.size) {//遍历该省份的所有城市
                val cityName = jsonBean[i].cityList[c].name
                cityList.add(cityName)//添加城市
                val city_AreaList = ArrayList<String>()//该城市的所有地区列表
                city_AreaList.addAll(jsonBean[i].cityList[c].area)
                province_AreaList.add(city_AreaList)//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList)

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList)
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS)

    }


    fun parseData(result: String): ArrayList<JsonBean> {//Gson 解析
        val detail = ArrayList<JsonBean>()
        try {
            val data = JSONArray(result)
            val gson = Gson()
            for (i in 0 until data.length()) {
                val entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean::class.java)
                detail.add(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED)
        }

        return detail
    }


    override fun onDestroy() {
        super.onDestroy()
        mHandler?.removeCallbacksAndMessages(null)
    }
}
