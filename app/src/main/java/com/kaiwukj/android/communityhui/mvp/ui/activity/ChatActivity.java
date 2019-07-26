package com.kaiwukj.android.communityhui.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.util.EasyUtils;
import com.kaiwukj.android.communityhui.R;
import com.kaiwukj.android.communityhui.app.base.BaseSupportActivity;
import com.kaiwukj.android.communityhui.di.component.DaggerChatComponent;
import com.kaiwukj.android.communityhui.mvp.contract.ChatContract;
import com.kaiwukj.android.communityhui.mvp.presenter.ChatPresenter;
import com.kaiwukj.android.mcas.di.component.AppComponent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class ChatActivity extends BaseSupportActivity<ChatPresenter> implements ChatContract.View {
    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;
    String toChatUsername;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerChatComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_chat_message;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        activityInstance = this;
        //get user id or group id
        toChatUsername = getIntent().getExtras().getString("userId");
        //use EaseChatFratFragment
        chatFragment = new EaseChatFragment();
        //pass parameters to chat fragment
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }


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
    public String getToChatUsername(){
        return toChatUsername;
    }



    @Override
    public void showMessage(@NonNull String message) {

    }
}
