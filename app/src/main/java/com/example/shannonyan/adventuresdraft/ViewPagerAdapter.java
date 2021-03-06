package com.example.shannonyan.adventuresdraft;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
//        position = position+1;
//        Bundle bundle = new Bundle();
//        bundle.putString("message", "Fragment:" + position);
//        prefFragment.setArguments(bundle);
        switch(position) {
            case 0: return CreatePickUpFragment.newInstance("FirstFragment, Instance 1");
            case 1: return CreateSecondFragment.newInstance("SecondFragment, Instance 1");
            default: return PrefFragment.newInstance("FirstFragment, Instance 1");
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
