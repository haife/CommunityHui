package com.kaiwukj.android.mcas.integration.lifecycle;


import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;

import androidx.fragment.app.Fragment;

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/1
 * @desc  让 {@link Fragment} 实现此接口,即可正常使用 {@link RxLifecycle}
 */
public interface FragmentLifecycleable extends Lifecycleable<FragmentEvent> {
}
