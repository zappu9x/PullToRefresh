package com.vetrio.pulltorefresh;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.menu_pager) ViewPager mViewPager;
   // @BindView(R.id.menu_tab) TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
        mViewPager.setAdapter(new MenuPagerAdapter(getSupportFragmentManager()));
       // mTabLayout.setupWithViewPager(mViewPager);
    }
}
