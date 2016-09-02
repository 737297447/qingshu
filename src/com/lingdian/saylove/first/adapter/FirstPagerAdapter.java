/**
 * 
 */
package com.lingdian.saylove.first.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FirstPagerAdapter extends FragmentStatePagerAdapter {

	private int mCount;
    private Fragment[] fragments;
    
    public FirstPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public FirstPagerAdapter(FragmentManager fragmentManager,Fragment[] fragments) {
        super(fragmentManager);
        this.fragments = fragments;
        mCount = fragments.length;
    }

    @Override
    public Fragment getItem(int position) {
    	return fragments[position % fragments.length];
    }

    @Override
    public int getCount() {
        return mCount;
    }

}