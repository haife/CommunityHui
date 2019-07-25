package com.kaiwukj.android.communityhui.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.util.EasyUtils;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackActivity;
import com.kaiwukj.android.communityhui.im.runtimepermissions.PermissionsManager;
import com.kaiwukj.android.communityhui.mvp.contract.ChatContract;
import com.kaiwukj.android.communityhui.mvp.presenter.ChatPresenter;
import com.kaiwukj.android.communityhui.mvp.ui.im.ChatFragment;
import com.kaiwukj.android.mcas.di.component.AppComponent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 *
 */
public class ChatActivity extends BaseSwipeBackActivity<ChatPresenter> implements ChatContract.View {
    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;
    String toChatUsername;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // make sure only one chat activity is opened
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        if (EasyUtils.isSingleActivity(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public String getToChatUsername() {
        return toChatUsername;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_em_chat;

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        activityInstance = this;
        //get user id or group id
        toChatUsername = getIntent().getExtras().getString("userId");
        //use EaseChatFratFragment
        chatFragment = new ChatFragment();
        //pass parameters to chat fragment
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
