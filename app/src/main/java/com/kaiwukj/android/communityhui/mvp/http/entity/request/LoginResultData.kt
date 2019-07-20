package com.kaiwukj.android.communityhui.mvp.http.entity.request

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/20
 * @desc $desc
 */

//登录请求
data class LoginRequest(
    val code: String,
    val phoneNo: String
)
data class LoginCodeRequest(
    val code: String,
    val phoneNo: String
)