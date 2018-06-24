package com.example.pctruong.appbanhang.view.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;



/**
 * Created by PCTruong on 30/05/2018.
 */

public class ViewPagerLoginSignUp extends FragmentStatePagerAdapter {
    public ViewPagerLoginSignUp(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

}