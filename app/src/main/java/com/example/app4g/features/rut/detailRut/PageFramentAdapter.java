package com.example.app4g.features.rut.detailRut;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PageFramentAdapter  extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public PageFramentAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment,String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
}
