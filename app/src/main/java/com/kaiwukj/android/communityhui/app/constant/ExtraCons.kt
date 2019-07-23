package com.kaiwukj.android.communityhui.app.constant

/**
 * @author Eddie Android Developer
 * @company Q | 樽尚汇
 * @since 2019/2/20$
 * TODO:
 */
class ExtraCons {
    companion object {

        const val CIRCLE_TOPIC_TYPE_ID: String = "CIRCLE_TOPIC_TYPE_ID"

        const val CIRCLE_TOPIC_TYPE_TITLE: String = "CIRCLE_TOPIC_TYPE_TITLE"
        /*传递给家政模块Activity 用来标识展示哪个Fragment*/
        const val EXTRA_KEY_HOUSE_KEEP: String = "EXTRA_KEY_HOUSE_KEEP"

        const val EXTRA_KEY_HOUSE_KEEP_ENTITY: String = "EXTRA_KEY_HOUSE_KEEP_ENTITY"

        /*跳转到门店模块，区分是首页还是查看门店详情*/
        const val EXTRA_KEY_STORE: String = "EXTRA_KEY_STORE"
        /*传递门店ID*/
        const val EXTRA_KEY_STORE_SHOP_ID: String = "EXTRA_KEY_STORE_SHOP_ID"

        /*跳转到我的模块，个人信息模块*/
        const val EXTRA_KEY_EDIT_MINE: String = "EXTRA_KEY_EDIT_MINE"

        /*跳转到我订单*/
        const val EXTRA_KEY_ORDER_MINE: String = "EXTRA_KEY_ORDER_MINE"
        /*跳转到我订单 选中的Item*/
        const val EXTRA_KEY_ORDER_MINE_INDEX: String = "EXTRA_KEY_ORDER_MINE_INDEX"

        /*选择的阿姨用户ID*/
        const val EXTRA_KEY_STAFF_USER_ID: String = "EXTRA_KEY_STAFF_USER_ID"

        /*选择的阿姨的服务Id*/
        const val EXTRA_KEY_STAFF_SETVIE_TYPE_ID: String = "EXTRA_KEY_STAFF_SETVIE_TYPE_ID"

    }
}