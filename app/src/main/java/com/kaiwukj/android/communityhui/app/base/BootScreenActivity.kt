package com.kaiwukj.android.communityhui.app.base

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.kaiwukj.android.communityhui.app.constant.SPParam
import com.kaiwukj.android.communityhui.mvp.ui.activity.LoginActivity
import com.kaiwukj.android.communityhui.mvp.ui.activity.MainActivity
import com.kaiwukj.android.communityhui.utils.SPUtils

/**
 * @author Eddie Android Developer
 * @company Q | 樽尚汇
 * @since 2019/2/19
 * first start screen
 */
class BootScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //TODO 判断登陆状态
        val userCode = SPUtils.getInstance().getString(SPParam.SP_LOGIN_TOKEN)
        if (userCode.isNullOrEmpty()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        this@BootScreenActivity.finish()


    }
}
