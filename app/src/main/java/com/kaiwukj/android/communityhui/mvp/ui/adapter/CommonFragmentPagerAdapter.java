package com.kaiwukj.android.communityhui.mvp.ui.adapter;


import java.util.ArrayList;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * 作者：Rance on 2016/11/25 16:36
 * 邮箱：rance935@163.com
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {
    ArrayList<androidx.fragment.app.Fragment> list;

    public CommonFragmentPagerAdapter(FragmentManager fm, ArrayList<androidx.fragment.app.Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public androidx.fragment.app.Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }
}
