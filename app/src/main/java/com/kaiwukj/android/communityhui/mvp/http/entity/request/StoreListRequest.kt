package com.kaiwukj.android.communityhui.mvp.http.entity.request

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/22
 * @desc $desc
 */
data class StoreListRequest(var recommendFlag: Int? = 0, var serviceTypeId: Int? = null, var hmstoreId: Int? = null, var pageNum: Int? = null)