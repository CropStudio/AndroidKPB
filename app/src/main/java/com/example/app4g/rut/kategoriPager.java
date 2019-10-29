package com.example.app4g.rut;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that stores fragments for tabs
 */
public class kategoriPager extends FragmentPagerAdapter {

    private static final String TAG = "DashboardPagerAdapter";

    private final List<Fragment> mFragmentList = new ArrayList<>();


    public kategoriPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment){
        mFragmentList.add(fragment);
    }


}
