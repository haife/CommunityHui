package com.kaiwukj.android.communityhui.di.module;

import com.kaiwukj.android.communityhui.mvp.contract.ChatContract;
import com.kaiwukj.android.communityhui.mvp.model.ChatModel;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class ChatModule {

    @Binds
    abstract ChatContract.Model bindChatModel(ChatModel model);
}