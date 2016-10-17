package com.ningso.deviceinfo.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by NingSo on 16/3/10.下午10:49
 *
 * @author: NingSo
 * @Email: ningdev@163.com
 */
public class BaseTabAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private Fragment[] fragments;

    public BaseTabAdapter(FragmentManager fm, String[] titles, Fragment[] fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}