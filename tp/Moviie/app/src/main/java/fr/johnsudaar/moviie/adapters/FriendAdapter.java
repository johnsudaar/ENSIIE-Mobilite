package fr.johnsudaar.moviie.adapters;

import android.content.Context;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;

import fr.johnsudaar.moviie.Configuration;
import fr.johnsudaar.moviie.FriendViewHolder;
import fr.johnsudaar.moviie.MovieViewHolder;
import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.tasks.FriendAdderAsyncTask;


public class FriendAdapter extends RecyclerView.Adapter<FriendViewHolder> {

    private Context ctx;
    private String[] friends;

    public FriendAdapter(String[] f, Context c) {
        friends = f;
        ctx = c;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend,parent,false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FriendViewHolder holder, final int position) {
        holder.text.setText(friends[position]);
        if(Configuration.get().getLoggedInUser().getFriendsAsList().contains(friends[position])){
            holder.add.setVisibility(View.INVISIBLE);
        } else if(Configuration.get().getLoggedInUser().getUsername().equals(friends[position])) {
            holder.add.setVisibility(View.INVISIBLE);
        } else {
            holder.add.setVisibility(View.VISIBLE);
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.add.setVisibility(View.INVISIBLE);
                    FriendAdderAsyncTask task = new FriendAdderAsyncTask(ctx, FriendAdapter.this);
                    task.execute(friends[position]);
                }
            });
        }

        switch (friends[position].hashCode()%4){
            case 0:
                DrawableCompat.setTint(holder.logoBg.getDrawable(), ctx.getResources().getColor(R.color.logo_1));
                break;
            case 1:
                DrawableCompat.setTint(holder.logoBg.getDrawable(), ctx.getResources().getColor(R.color.logo_2));
                break;
            case 2:
                DrawableCompat.setTint(holder.logoBg.getDrawable(), ctx.getResources().getColor(R.color.logo_3));
                break;
            case 3:
                DrawableCompat.setTint(holder.logoBg.getDrawable(), ctx.getResources().getColor(R.color.logo_4));
                break;
        }

        holder.logoText.setText(""+friends[position].toUpperCase().charAt(0));
    }

    @Override
    public int getItemCount() {
        return friends.length;
    }

    public void setData(String[] d){
        this.friends = d;
        this.notifyDataSetChanged();
    }
}
