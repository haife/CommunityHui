package com.kaiwukj.android.communityhui.mvp.ui.fragment


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.EXTRA_APP_PACKAGE
import android.provider.Settings.EXTRA_CHANNEL_ID
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSupportFragment
import com.kaiwukj.android.communityhui.di.component.DaggerHomeComponent
import com.kaiwukj.android.communityhui.di.module.HomeModule
import com.kaiwukj.android.communityhui.mvp.contract.HomeContract
import com.kaiwukj.android.communityhui.mvp.http.entity.request.StoreListRequest
import com.kaiwukj.android.communityhui.mvp.presenter.HomePresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.HRecommendAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc
 */
class HomeFragment : BaseSupportFragment<HomePresenter>(), HomeContract.View, OnRefreshListener {

    @Inject
    lateinit var mHomeAdapter: HRecommendAdapter

    @Inject
    lateinit var mLayoutManager: RecyclerView.LayoutManager

    companion object {
        const val EXTRA_KEY_HOME_FRAGMENT_URL = "HOME_FRAGMENT"
        const val RECOMMEND_FLAG = 1

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerHomeComponent
                .builder()
                .appComponent(appComponent)
                .homeModule(HomeModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.requestServiceList(StoreListRequest(RECOMMEND_FLAG), false)
        smart_refresh_home.setOnRefreshListener(this)
        rv_home.layoutManager = mLayoutManager
        rv_home.adapter = mHomeAdapter
        checkNotifySetting()
    }

    /**
     * 跳转到设置界面
     */
    private fun initClickListener() {
        try {
            // 根据isOpened结果，判断是否需要提醒用户跳转AppInfo页面，去打开App通知权限
            val intent = Intent()
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            //这种方案适用于 API 26, 即8.0（含8.0）以上可以用
            intent.putExtra(EXTRA_APP_PACKAGE, context?.packageName)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {
                intent.putExtra(EXTRA_CHANNEL_ID, context?.applicationInfo?.uid)
                //这种方案适用于 API21——25，即 5.0——7.1 之间的版本可以使用
                intent.putExtra("app_package", context?.packageName)
                intent.putExtra("app_uid", context?.applicationInfo?.uid)
            }


            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            // 出现异常则跳转到应用设置界面：锤子坚果3——OC105 API25
            val intent = Intent()
            //下面这种方案是直接跳转到当前应用的设置界面。
            //https://blog.csdn.net/ysy950803/article/details/71910806
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", context?.applicationInfo.toString(), null)
            intent.data = uri
            startActivity(intent)
        }

    }


    private fun checkNotifySetting() {
        val manager = NotificationManagerCompat.from(context!!)
        // areNotificationsEnabled方法的有效性官方只最低支持到API 19，低于19的仍可调用此方法不过只会返回true，即默认为用户已经开启了通知。
        val isOpened = manager.areNotificationsEnabled()
        if (isOpened) {

        } else {
            val dialog = MaterialDialog(context!!)
                    .cornerRadius(6f)
                    .title(R.string.notify_title)
                    .message(R.string.notify_no_open)
                    .positiveButton(text = "下次再说") { materialDialog -> materialDialog.dismiss() }
                    .negativeButton(text = "设置") { initClickListener() }
            dialog.show()
        }
    }

    override fun showLoading() {
        qmui_empty_view.setLoadingShowing(true)
    }

    override fun hideLoading() {
        qmui_empty_view.hide()
    }

    override
    fun getFragment(): Fragment = this

    override fun post(runnable: Runnable?) {
    }

    override fun showMessage(message: String) {
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun killMyself() {

    }

    override fun onResponseError() {
        smart_refresh_home.finishRefresh()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mPresenter?.requestStoreRecommend(StoreListRequest(RECOMMEND_FLAG), true)
    }

}

