package com.kaiwukj.android.mcas.base;


import com.kaiwukj.android.mcas.di.component.AppComponent;

import androidx.annotation.NonNull;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/1
 * @desc 框架要求框架中的每个 {@link android.app.Application} 都需要实现此类, 以满足规范
 */
public interface App {
    @NonNull
    AppComponent getAppComponent();
}
