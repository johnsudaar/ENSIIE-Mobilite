package fr.johnsudaar.moviie;

// Not by me.
// From github :)

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import fr.johnsudaar.moviie.adapters.MovieViewHolderAdapter;

public class MoviesFragment extends Fragment {
    private int tab;
    private RecyclerView recyclerView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tab = getArguments().getInt("tab");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        final View view = inflater.inflate(R.layout.fragment_movies, null, false);
        ProgressBar pb = (ProgressBar) view.findViewById(R.id.progress);
        Button failed = (Button) view.findViewById(R.id.failed_button);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyler_view);

        // Recycler view
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        final MovieViewHolderAdapter adapter  = new MovieViewHolderAdapter(failed, recyclerView, pb, tab, this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager){
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                adapter.loadNextData();
            }
        };

        if(tab != 3) {
            recyclerView.addOnScrollListener(scrollListener);

            adapter.setScrollListener(scrollListener);
        }

        failed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.reset();
                adapter.loadNextData();;
            }
        });
        return view;
    }
}
