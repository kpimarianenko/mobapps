package com.example.task3;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CountriesPagerAdapter extends FragmentPagerAdapter {

    private Country[] countries;

    public CountriesPagerAdapter(FragmentManager manager, Country[] countries) {
        super(manager);
        this.countries = countries;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position > 0 && position <= countries.length)
            return CountryFragment.newInstance(this.countries[position - 1]);
        return MainScreenFragment.newInstance();
    }

    @Override
    public int getCount() {
        return countries.length + 1;
    }
}
