package fr.johnsudaar.moviie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.johnsudaar.moviie.activities.MovieDetailActivity;
import fr.johnsudaar.moviie.models.Movie;

public class FriendViewHolder extends RecyclerView.ViewHolder {
    public TextView text;
    public FriendViewHolder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.text);
    }
}
