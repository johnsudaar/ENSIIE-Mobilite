package fr.johnsudaar.moviie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import fr.johnsudaar.moviie.activities.MovieDetailActivity;
import fr.johnsudaar.moviie.models.Movie;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private ImageView seen;
    private Context ctx;
    private Movie movie;
    public MovieViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.image_view);
        seen = (ImageView) itemView.findViewById(R.id.seen);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, MovieDetailActivity.class);
                intent.putExtra("movie", movie);
                ctx.startActivity(intent);
            }
        });
    }

    public void setContext(Context ctx) {
        this.ctx = ctx;
    }

    public void setMovie(Movie m){
        this.movie = m;
        Picasso.with(ctx)
                .load(movie.getPosterUrl())
                .error(R.mipmap.no_picture_vailable)
                .into(imageView);
        if(m.isSeen()) {
            this.seen.setVisibility(View.VISIBLE);
        } else {
            this.seen.setVisibility(View.INVISIBLE);
        }
    }
}
