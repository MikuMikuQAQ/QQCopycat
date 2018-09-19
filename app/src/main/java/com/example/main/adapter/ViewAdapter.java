package com.example.main.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    private Context context;

    public ViewAdapter(FragmentManager fm, List<Fragment> fragmentList, Context context) {
        super(fm);
        this.fragmentList = fragmentList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList != null?fragmentList.size():0;
    }
}
