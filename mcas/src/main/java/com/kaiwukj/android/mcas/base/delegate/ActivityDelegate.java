package com.kaiwukj.android.mcas.base.delegate;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/1
 * {@link Activity} 代理类,用于框架内部在每个 {@link Activity} 的对应生命周期中插入需要的逻辑
 * @see ActivityDelegateImpl
 */
public interface ActivityDelegate {
    String LAYOUT_LINEARLAYOUT = "LinearLayout";
    String LAYOUT_FRAMELAYOUT = "FrameLayout";
    String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    String ACTIVITY_DELEGATE = "ACTIVITY_DELEGATE";

    void onCreate(@Nullable Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onSaveInstanceState(@NonNull Bundle outState);

    void onDestroy();
}
