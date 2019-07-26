package com.kaiwukj.android.communityhui.mvp.http.entity.result

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/20
 * @desc 登录模块Json返回数据
 */


/**
 * 登录结果
 * @property code String
 * @property desc String
 * @property result String
 * @constructor
 */
data class LoginResult(
    val code: String,
    val desc: String,
    val result: MyResult
)

data class MyResult(
    val alias: String,
    val token: String
)

data class LoginVerifyCodeResult(
    val code: String,
    val desc: String,
    val result: Result
)

data class Result(
        val alias: String,
        val token: String
)


