package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.di.component.DaggerEditMineInfoComponent
import com.kaiwukj.android.communityhui.di.module.EditMineInfoModule
import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.http.entity.request.MineCollectionResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.presenter.EditMineInfoPresenter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.utils.McaUtils
import com.qmuiteam.qmui.widget.QMUITopBar
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import kotlinx.android.synthetic.main.fragment_edit_mine_address.*
import me.yokeyword.fragmentation.ISupportFragment


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  服务评价
 */
class EditMineAddressFragment : BaseSwipeBackFragment<EditMineInfoPresenter>(), EditMineInfoContract.View {

    lateinit var myAddressResult: MyAddressResult
    private var hintDialog: QMUITipDialog? = null

    companion object {
        const val EDIT_MINE_ADDRESS_FRAGMENT = "EDIT_MINE_ADDRESS_FRAGMENT"

        fun newInstance(myAddressResult: MyAddressResult): EditMineAddressFragment {
            val fragment = EditMineAddressFragment()
            fragment.myAddressResult = myAddressResult
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
        return attachToSwipeBack(inflater.inflate(com.kaiwukj.android.communityhui.R.layout.fragment_edit_mine_address, container, false))
    }


    override fun initData(savedInstanceState: Bundle?) {
        if (myAddressResult.communityId != 0) {
            //编辑地址
            custom_edit_address_name.contentText = myAddressResult.name
            custom_edit_address_phone.contentText = myAddressResult.phone
            custom_edit_address_detail.contentText = myAddressResult.address
            tv_edit_address_city.text = myAddressResult.area
        }

        qbtn_save_address.setOnClickListener {
            it.isEnabled = false
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
            if (McaUtils.isEmpty(area)) {
                showMessage("请选择服务地点")
                return@setOnClickListener
            }
            myAddressResult.area = area

            val detailAddress = custom_edit_address_detail.contentText
            if (McaUtils.isEmpty(detailAddress)) {
                showMessage("请输入详细地址")
                return@setOnClickListener
            }
            myAddressResult.address = detailAddress

            if (myAddressResult.communityId != 0) {
                //编辑地址
                mPresenter?.upDateMyAddress(myAddressResult)
            } else {
                //新增地址
                mPresenter?.addMyAddress(myAddressResult)
            }

        }
    }


    override fun onSupportVisible() {
        super.onSupportVisible()
        activity?.findViewById<QMUITopBar>(com.kaiwukj.android.communityhui.R.id.qtb_edit_mine_info)?.setTitle(getString(com.kaiwukj.android.communityhui.R.string.edit_mine_address))
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
                .setTipWord("编辑成功")
                .create()

        tipDialog.show()

        Handler().postDelayed({
            hintDialog?.dismiss()
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
}
