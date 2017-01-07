package com.ensiie.tp5;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class AppBarTabsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_tab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        setSupportActionBar(toolbar);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }
}

class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager manager) { super(manager); }

    // Appelé pour chaque tab avec en paramètre la position de la tab
    @Override
    public Fragment getItem(int position) {
        // Définition des données qu‘on souhaite passer à un fragment
        Bundle argument = new Bundle();
        argument.putInt("tab", position);
        // Création du fragment
        MainFragment fragment = new MainFragment();
        fragment.setArguments(argument);
        return fragment;
    }

    @Override
    public int getCount() { return 3; } // Nombre de tabs

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return getString()
                break;
            case 1:
                break;
            case 2:
                break;
        }
        return "TAB " + position;
    }
}
