package com.ensiie.tp5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment {
    private static final String EXTRA_TAB = "extra_tab";

    private int tab;

    public static MainFragment newInstance(int tab) {
        final Bundle argument = new Bundle();
        argument.putInt(EXTRA_TAB, tab);

        final MainFragment fragment = new MainFragment();
        fragment.setArguments(argument);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tab = getArguments().getInt(EXTRA_TAB);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = (TextView) view.findViewById(R.id.text);
        switch (tab) {
            case 0:
                textView.setText(getString(R.string.fragment_music));
                break;
            case 1:
                textView.setText(getString(R.string.fragment_books));
                break;
            case 2:
                textView.setText(getString(R.string.fragment_games));
                break;
        }
        return view;
    }
}
