package com.kaiwukj.android.communityhui.mvp.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;
import timber.log.Timber;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSupportActivity;
import com.kaiwukj.android.communityhui.di.component.DaggerMainComponent;
import com.kaiwukj.android.communityhui.di.module.MainModule;
import com.kaiwukj.android.communityhui.mvp.contract.MainContract;
import com.kaiwukj.android.communityhui.mvp.presenter.MainPresenter;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.HomeFragment;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

public class MainActivity extends BaseSupportActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.bnve_main_bottom_navigation)
    BottomNavigationViewEx mMainBottomBnve;

    @Inject
    RxPermissions mRxPermissions;

    private ISupportFragment[] mFragments = new ISupportFragment[1];

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
                case R.id.home_class_page:
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
            loadMultipleRootFragment(R.id.fl_main_container, 0, mFragments);
        } else {
            mFragments[0] = findFragment(HomeFragment.class);
        }
    }

    @SuppressLint("CheckResult")
    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(MainActivity.this);
        rxPermission
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.ACCESS_NOTIFICATION_POLICY,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.BIND_NOTIFICATION_LISTENER_SERVICE,
                        Manifest.permission.SEND_SMS)
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
