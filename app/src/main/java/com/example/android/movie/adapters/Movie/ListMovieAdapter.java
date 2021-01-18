package com.example.android.movie.adapters.Movie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movie.ui.Movie.MovieDetails;
import com.example.android.movie.model.Result;
import com.example.android.movie.R;
import com.example.android.movie.repository.Repository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.MyViewHolder> {

    private List<Result> mMovies = new ArrayList<>();
    public static final String SELECTED_MOVIE = "selected_movie";
    private Context context;
    private int lastPosition = -1;

    public ListMovieAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_views, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String image = Repository.IMAGE_LOAD + mMovies.get(position).getPosterPath();
        Picasso.with(context)
                .load(image)
                .into(holder.imageMovie);


        //Animation Scroll
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        lastPosition = position;
        holder.itemView.startAnimation(animation);
        holder.nameMovie.setText(mMovies.get(position).getTitle());
        holder.rating.setText(String.valueOf(mMovies.get(position).getVoteAverage()));
        holder.dateMovie.setText(mMovies.get(position).getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void setList(List<Result> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageMovie;
        TextView nameMovie;
        TextView rating;
        TextView dateMovie;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.imageMovie = (ImageView) itemView.findViewById(R.id.image_movie);
            this.nameMovie = (TextView) itemView.findViewById(R.id.name_movie);
            this.rating = (TextView) itemView.findViewById(R.id.rating);
            this.dateMovie = (TextView) itemView.findViewById(R.id.date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, MovieDetails.class);
            Result result = mMovies.get(getAdapterPosition());
            intent.putExtra(SELECTED_MOVIE, result);
            context.startActivity(intent);
        }

    }
}
