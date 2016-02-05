package com.dukhabar.youthutsav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

/**
 * Created by sysAdmin on 05/02/16.
 */
public class ANshu extends Fragment {


    private final String LOG_TAG = getClass().getSimpleName();
    ScrollerViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View team_viewpager = inflater.inflate(R.layout.team_viewpager, container, false);


        viewPager = (ScrollerViewPager) team_viewpager.findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) team_viewpager.findViewById(R.id.indicator);


        final TeamAdapter adapter = new TeamAdapter(getChildFragmentManager());

        adapter.addFragment(new avp(4), "AVP");
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();

        // just set viewPager
        springIndicator.setViewPager(viewPager);


        return team_viewpager;
    }


    static class TeamAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public TeamAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}

