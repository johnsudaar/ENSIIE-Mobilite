package fr.johnsudaar.moviie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.johnsudaar.moviie.activities.MovieDetailActivity;
import fr.johnsudaar.moviie.models.Movie;

public class FriendViewHolder extends RecyclerView.ViewHolder {
    public TextView text;
    public ImageButton add;
    public ImageView logoBg;
    public TextView logoText;
    public FriendViewHolder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.text);
        add =(ImageButton) itemView.findViewById(R.id.button);
        logoBg = (ImageView) itemView.findViewById(R.id.logo_bg);
        logoText = (TextView) itemView.findViewById(R.id.logo_text);
    }
}
