package com.kaiwukj.android.communityhui.mvp.ui.activity


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.textfield.TextInputLayout
import com.irozon.sneaker.Sneaker
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.constant.LoginRouterUrl
import com.kaiwukj.android.communityhui.app.constant.MainRouterUrl
import com.kaiwukj.android.communityhui.app.constant.SPParam
import com.kaiwukj.android.communityhui.di.component.DaggerLoginComponent
import com.kaiwukj.android.communityhui.di.module.LoginModule
import com.kaiwukj.android.communityhui.mvp.contract.LoginContract
import com.kaiwukj.android.communityhui.mvp.http.entity.request.LoginRequest
import com.kaiwukj.android.communityhui.mvp.presenter.LoginPresenter
import com.kaiwukj.android.communityhui.mvp.ui.widget.login.LoginTimeCount
import com.kaiwukj.android.communityhui.utils.InputMethodUtils
import com.kaiwukj.android.communityhui.utils.SPUtils
import com.kaiwukj.android.mcas.base.BaseActivity
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import kotlinx.android.synthetic.main.activity_login.*


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 *
 * @desc  Login Screen
 */
@Route(path = LoginRouterUrl)
class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.View, TextWatcher {
    private var timeCount: LoginTimeCount? = null
    private lateinit var phoneNumber: String
    private lateinit var phoneCode: String
    private var hintDialog: QMUITipDialog? = null
    override fun getActivity(): FragmentActivity = this

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(LoginModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_login
    }


    override fun initData(savedInstanceState: Bundle?) {
        //TODO:判断是否存储过手机号
        val number: String = SPUtils.getInstance().getString(SPParam.SP_LOGIN_PHONE)
        et_phone_number.setText(number)
        et_phone_number.addTextChangedListener(this)
        et_login_phone_code.addTextChangedListener(this)
        clickListener()

        tv_login_agreement_desc.setOnClickListener {
            val intent = Intent(this@LoginActivity,WebActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getPhoneNumber(): String {
        return et_phone_number.text.toString()
    }

    private fun getPhoneCode(): String {
        return et_login_phone_code.text.toString()
    }

    /**
     * 检查手机号
     * @param phoneNumber String
     * @return Boolean
     */
    private fun checkPhoneNumber(phoneNumber: String?): Boolean {
        if (phoneNumber.isNullOrEmpty()) {
            showInputError(text_input_layout_phone_number, getString(R.string.input_phone_number))
            return false
        }
        return if (phoneNumber.length == 11 && phoneNumber.startsWith(prefix = "1")) {
            InputMethodUtils.hideSoftInput(this)
            true
        } else {
            showInputError(text_input_layout_phone_number, getString(R.string.phone_number_error_format_desc))
            false
        }
    }

    private fun showInputError(textInputLayout: TextInputLayout, errorMsg: String) {
        textInputLayout.error = errorMsg
        textInputLayout.editText?.isFocusable = true
        textInputLayout.editText?.isFocusableInTouchMode = true
        textInputLayout.editText?.requestFocus()
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        text_input_layout_phone_number.isErrorEnabled = false
        text_input_layout_phone_code.isErrorEnabled = false
    }

    /**
     * 登录成功
     */
    override fun loginSuccess() {
        Sneaker.with(this)
                .setTitle(getString(R.string.login_success))
                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setMessage(getString(R.string.success))
                .sneak(R.color.app_color_theme)

        var runnable: Runnable? = Runnable {
            launchActivity(Intent())
            killMyself()
        }
        Handler().postDelayed(runnable, 2000)
    }

    /**
     * 验证码发送成功
     */
    override fun sendVerifyCodeComplete() {


    }


    override fun hideLoading() {
        Handler().postDelayed({
            hintDialog?.dismiss()
        }, 800)
    }

    override fun showMessage(message: String) {
        if (message.isNotEmpty())
            Sneaker.with(this)
                    .setTitle(getString(R.string.login_failure))
                    .setMessage(message)
                    .sneakWarning()
    }

    override fun launchActivity(intent: Intent) {
        ARouter.getInstance().build(MainRouterUrl).navigation(this)
    }

    override fun clickListener() {
        btn_get_phone_code.setOnClickListener {
            //TODO 获取手机短息验证码
            phoneNumber = getPhoneNumber()
            if (checkPhoneNumber(phoneNumber)) {
                timeCount = LoginTimeCount(LoginTimeCount.MILL_IS_IN_FUTURE, LoginTimeCount.COUNT_DOWN_INTERVAL, it as? Button)
                timeCount?.start()
                mPresenter?.requestVerifyCode(phoneNumber)
            }
        }

        qbtn_login.setOnClickListener {
            //TODO:点击登陆
            phoneNumber = getPhoneNumber()
            phoneCode = getPhoneCode()
            InputMethodUtils.hideSoftInput(this)
            if (checkPhoneNumber(phoneNumber)) {
                if (phoneCode.isNullOrEmpty()) showInputError(text_input_layout_phone_code, getString(R.string.phone_code_empty_error_desc))
                else mPresenter?.requestLogin(LoginRequest(phoneCode, phoneNumber))
            }

        }

    }


    override fun killMyself() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        timeCount?.cancel()
    }
}
