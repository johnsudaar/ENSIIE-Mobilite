package fr.johnsudaar.moviie.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileReader;

import fr.johnsudaar.moviie.Configuration;
import fr.johnsudaar.moviie.FriendViewHolder;
import fr.johnsudaar.moviie.MovieViewHolder;
import fr.johnsudaar.moviie.R;


public class FriendAdapter extends RecyclerView.Adapter<FriendViewHolder> {

    private String[] friends;

    public FriendAdapter(String[] f) {
        friends = f;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend,parent,false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        holder.text.setText(friends[position]);
    }

    @Override
    public int getItemCount() {
        return friends.length;
    }
}
