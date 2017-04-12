package com.vetrio.pulltorefresh;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MenuPagerAdapter extends FragmentPagerAdapter {

    public MenuPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
//        switch (position){
//            case 0:
//                new DrinkFragment();
//            case 1:
//                KanjiFragment mKanjiFragment = new KanjiFragment();
//                mKanjiFragment.setListViewScrollListener(mListViewScrollListener);
//                mDataUpdateListeners[1] = mKanjiFragment;
//                return mKanjiFragment;
//            case 2:
//                GrammarFragment mGrammarFragment  = new GrammarFragment();
//                mGrammarFragment.setListViewScrollListener(mListViewScrollListener);
//                mDataUpdateListeners[2] = mGrammarFragment;
//                return mGrammarFragment;
//            case 3:
//                ExampleFragment mExampleFragment = new ExampleFragment();
//                mExampleFragment.setListViewScrollListener(mListViewScrollListener);
//                mDataUpdateListeners[3] = mExampleFragment;
//                return mExampleFragment;
//            default:
//                return null;
//        }
        return new DrinkFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Food";
            case 1:
                return "Drink";
            case 2:
                return "Dessert";
            default:
                return null;
        }
    }
}
