package com.kaiwukj.android.communityhui.mvp.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSupportActivity;
import com.kaiwukj.android.communityhui.app.constant.ARouterUrlKt;
import com.kaiwukj.android.communityhui.app.constant.SPParam;
import com.kaiwukj.android.communityhui.di.component.DaggerMainComponent;
import com.kaiwukj.android.communityhui.di.module.MainModule;
import com.kaiwukj.android.communityhui.mvp.contract.MainContract;
import com.kaiwukj.android.communityhui.mvp.presenter.MainPresenter;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.ChatMessageFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.HomeFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.MineFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.SocialCircleFragment;
import com.kaiwukj.android.communityhui.utils.SPUtils;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;
import timber.log.Timber;

import static com.kaiwukj.android.communityhui.app.constant.ARouterUrlKt.MainRouterUrl;

@Route(path = MainRouterUrl)
public class MainActivity extends BaseSupportActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.bnve_main_bottom_navigation)
    BottomNavigationViewEx mMainBottomBnve;

    @Inject
    RxPermissions mRxPermissions;

    private ISupportFragment[] mFragments = new ISupportFragment[4];

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }


    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initWidget();
        requestPermissions();
        buildBottomMenuListener();

    }


    private void buildBottomMenuListener() {
        mMainBottomBnve.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home_home_pager:
                    showHideFragment(mFragments[0]);
                    break;
                case R.id.home_circle_page:
                    showHideFragment(mFragments[1]);
                    break;
                case R.id.home_news:
                    showHideFragment(mFragments[2]);
                    break;
                case R.id.home_mine:
                    showHideFragment(mFragments[3]);
                    break;
            }
            return true;
        });
    }


    /**
     * 初始化任务
     */
    @Override
    public void initWidget() {
        //判断token
        String token = SPUtils.getInstance().getString(SPParam.SP_LOGIN_TOKEN);
        if (token == null) {
            ARouter.getInstance().build(ARouterUrlKt.LoginRouterUrl).navigation();
            killMyself();
        }
        mMainBottomBnve.enableAnimation(false);
        mMainBottomBnve.enableShiftingMode(false);
        addFragment();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void showMessage(@NonNull String message) {
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
    }

    @Override
    public void killMyself() {
        finish();
    }

    private void addFragment() {
        ISupportFragment recommendFragment = findFragment(HomeFragment.class);
        if (recommendFragment == null) {
            mFragments[0] = HomeFragment.Companion.newInstance();
            mFragments[1] = SocialCircleFragment.newInstance();
            mFragments[2] = ChatMessageFragment.newInstance();
            mFragments[3] = MineFragment.Companion.newInstance();
            loadMultipleRootFragment(R.id.fl_main_container, 0, mFragments);
        }
    }

    @SuppressLint("CheckResult")
    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(MainActivity.this);
        rxPermission
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CALL_PHONE)
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        Timber.e("%s is granted.", permission.name);
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        Timber.d("%s is denied. More info should be provided.", permission.name);
                    } else {
                        // 用户拒绝了该权限，并且选中『不再询问』
                        Timber.e("%s is denied.", permission.name);
                    }
                });
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public void post(Runnable runnable) {

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mRxPermissions = null;
    }
}
