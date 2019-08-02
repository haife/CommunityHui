package com.kaiwukj.android.communityhui.di.component;

import com.kaiwukj.android.communityhui.di.module.ChatModule;
import com.kaiwukj.android.communityhui.mvp.contract.ChatContract;
import com.kaiwukj.android.communityhui.mvp.ui.activity.ChatActivity;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.ChatListFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.ChatMessageFragment;
import com.kaiwukj.android.communityhui.mvp.ui.fragment.PushMessageListFragment;
import com.kaiwukj.android.mcas.di.component.AppComponent;
import com.kaiwukj.android.mcas.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Component;


@ActivityScope
@Component(modules = ChatModule.class, dependencies = AppComponent.class)
public interface ChatComponent {
    void inject(ChatActivity activity);
    void inject(ChatListFragment fragment);
    void inject(ChatMessageFragment fragment);
    void inject(PushMessageListFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ChatComponent.Builder view(ChatContract.View view);

        ChatComponent.Builder appComponent(AppComponent appComponent);

        ChatComponent build();
    }
}