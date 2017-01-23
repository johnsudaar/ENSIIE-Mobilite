package fr.johnsudaar.moviie.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.johnsudaar.moviie.MoviesFragment;
import fr.johnsudaar.moviie.R;

public class MovieViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    public MovieViewPagerAdapter(FragmentManager fm, Context c) {
        super(fm);
        this.context = c;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle arguments = new Bundle();
        arguments.putInt("tab", position);

        MoviesFragment fragment = new MoviesFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch(position) {
            case 0:
                return context.getString(R.string.popular);
            case 1:
                return context.getString(R.string.top_rated);
            case 2:
                return context.getString(R.string.coming_soon);
        }
        return "";
    }
}
