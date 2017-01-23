package fr.johnsudaar.moviie.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ProgressBar;

import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.adapters.MovieViewPagerAdapter;
import fr.johnsudaar.moviie.tasks.MoviesFetcherAsyncTask;

public class Movies extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        setSupportActionBar(toolbar);
        viewPager.setAdapter(new MovieViewPagerAdapter(getSupportFragmentManager(), this.getApplicationContext()));
        tabLayout.setupWithViewPager(viewPager);

    }
}
