package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.isseiaoki.simplecropview.FreeCropImageView
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.R.color
import com.kaiwukj.android.communityhui.R.layout
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.di.component.DaggerEditMineInfoComponent
import com.kaiwukj.android.communityhui.di.module.EditMineInfoModule
import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.http.entity.request.MineCollectionResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MineUserInfoResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.presenter.EditMineInfoPresenter
import com.kaiwukj.android.communityhui.utils.InputMethodUtils
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.kaiwukj.android.mcas.http.imageloader.glide.GlideArms
import com.lzy.imagepicker.ImagePicker
import com.lzy.imagepicker.bean.ImageItem
import com.lzy.imagepicker.ui.ImageGridActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.fragment_person_home_page.*
import java.text.SimpleDateFormat


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  我的信息主页
 */
class PersonHomePageFragment : BaseSwipeBackFragment<EditMineInfoPresenter>(), EditMineInfoContract.View {
    private val formatter = SimpleDateFormat("yyyy-MM-dd")

    var mUserInfo: MineUserInfoResult? = MineUserInfoResult()

    companion object {
        const val PERSON_HOME_PAGE_FRAGMENT = "PERSON_HOME_PAGE_FRAGMENT"
        private val REQUEST_CODE_CHOOSE_IMAGE = 1
        private val REQUEST_CODE_CHOOSE_TYPE = 2
        fun newInstance(userInfo: MineUserInfoResult?): PersonHomePageFragment {
            val fragment = PersonHomePageFragment()
            fragment.mUserInfo = userInfo
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
        return attachToSwipeBack(inflater.inflate(layout.fragment_person_home_page, container, false))
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_home_page_name.isEnabled = false
        initViewListener()
        initLayout()
    }

    private fun initViewListener() {
        tv_click_edit_nick_name.setOnClickListener {
            tv_home_page_name.setText("")
            tv_home_page_name.isEnabled = true
            tv_home_page_name.isFocusable = true
            tv_home_page_name.isFocusableInTouchMode = true
            tv_home_page_name.requestFocus()
            InputMethodUtils.showSoftInput(tv_home_page_name)
        }

        tv_person_home_page_birthday.setOnClickListener {
            //时间选择器
            TimePickerBuilder(context, OnTimeSelectListener
            { date, v ->
                tv_person_home_page_birthday.text = formatter.format(date)
                mUserInfo?.birthday = date
            }).setCancelColor(ContextCompat.getColor(context!!, color.common_text_slight_color))
                    .isCyclic(false)
                    .setSubmitColor(ContextCompat.getColor(context!!, color.app_color_theme))
                    .setLineSpacingMultiplier(1.8f)
                    .build().show()
        }

        rg_person_home_sex.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb_person_home_page_man -> mUserInfo?.gender = 1
                R.id.rb_person_home_page_woman -> mUserInfo?.gender = 2
            }
        }

        qiv_person_hom_profile_photo.setOnClickListener {
            requestPermissions()
        }
    }

    override fun onSupportInvisible() {
        super.onSupportInvisible()
        mUserInfo?.let { mPresenter?.updateMineInfoData(it) }
    }

    private fun initLayout() {
        mUserInfo?.let {
            tv_home_page_name.setText(it.nickName)
            et_person_home_page_sign.setText(it.perSign)
            tv_person_home_page_phone.text = it.phoneNo
            if (it.gender == 1)
                rg_person_home_sex.check(R.id.rb_person_home_page_man)
            else
                rg_person_home_sex.check(R.id.rb_person_home_page_woman)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            val listImg = data!!.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS) as ArrayList<ImageItem>
            context?.let { GlideArms.with(it).load(listImg[0].path).into(qiv_person_hom_profile_photo) }
            mPresenter?.requestQIToken(listImg[0].path)
        }
    }


    @SuppressLint("CheckResult")
    private fun requestPermissions() {
        val rxPermission = RxPermissions(activity!!)
        rxPermission
                .requestEach(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .subscribe { permission ->
                    if (permission.granted) {
                        // 用户已经同意该权限
                        val intent = Intent(context, ImageGridActivity::class.java)
                        startActivityForResult(intent, REQUEST_CODE_CHOOSE_IMAGE)
                        ImagePicker.getInstance().isMultiMode = false;
                        ImagePicker.getInstance().setFreeCrop(true, FreeCropImageView.CropMode.FREE)
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框

                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』

                    }
                }

    }

    override fun onGetMyAddressList(result: MyAddressResult) {
    }

    override fun onGetMyCollectionData(list: List<MineCollectionResult>) {
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        mUserInfo?.headImg = message
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun killMyself() {
        activity?.onBackPressed()
    }

    override fun post(runnable: Runnable?) {
    }

}
