package com.kaiwukj.android.communityhui.app.impl;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.kaiwukj.android.mcas.utils.McaUtils;
import com.squareup.leakcanary.RefWatcher;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import timber.log.Timber;

/**
 * Copyright © KaiWu Technology Company
 *
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/15
 * @desc
 */
public class FragmentLifecycleCallbacksImpl extends FragmentManager.FragmentLifecycleCallbacks {
    @Override
    public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
    }

    @Override
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        f.setRetainInstance(true);
    }

    @Override
    public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
    }

    @Override
    public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
    }

    @Override
    public void onFragmentStarted(FragmentManager fm, Fragment f) {
    }

    @Override
    public void onFragmentResumed(FragmentManager fm, Fragment f) {
    }

    @Override
    public void onFragmentPaused(FragmentManager fm, Fragment f) {
    }

    @Override
    public void onFragmentStopped(FragmentManager fm, Fragment f) {
    }

    @Override
    public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
    }

    @Override
    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
    }

    @Override
    public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
        RefWatcher refWatcher = ((RefWatcher) McaUtils
                .obtainAppComponentFromContext(f.getActivity())
                .extras()
                .get(IntelligentCache.getKeyOfKeep(RefWatcher.class.getName())));
        if (refWatcher != null) {
            refWatcher.watch(f);
        }

    }

    @Override
    public void onFragmentDetached(FragmentManager fm, Fragment f) {
        Timber.i(f.toString() + " - onFragmentDetached");
    }
}
