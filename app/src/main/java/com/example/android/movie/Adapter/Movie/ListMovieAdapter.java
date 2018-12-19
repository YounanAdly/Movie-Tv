package com.example.android.movie.Adapter.Movie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movie.Common.Common;
import com.example.android.movie.Interface.ItemClickListener;
import com.example.android.movie.Model.Result;
import com.example.android.movie.MovieDetails;
import com.example.android.movie.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yuyu on 11-Nov-18.
 */

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.MyViewHolder> {

    private ArrayList<Result> mMovies;

    private Context context;
    public static final String SELECTED_MOVIE = "selected_movie";
    private int lastPosition = -1;

    public ListMovieAdapter(ArrayList<Result> mMovies, Context context) {
        this.mMovies = mMovies;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_views, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         String image = Common.IMAGE_LOAD + mMovies.get(position).getPosterPath();

        Picasso.with(context)
                .load(image)
                .into(holder.imageMovie);


        //Animation Scroll
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;

        holder.nameMovie.setText(mMovies.get(position).getTitle());



        holder.rating.setText(String.valueOf(mMovies.get(position).getVoteAverage()));

        holder.dateMovie.setText(mMovies.get(position).getReleaseDate());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, MovieDetails.class);
                Result result = mMovies.get(position);


                intent.putExtra(SELECTED_MOVIE, result);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageMovie;
        TextView nameMovie;
        TextView rating;
        TextView dateMovie;
        ItemClickListener itemClickListener;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.imageMovie = (ImageView) itemView.findViewById(R.id.image_movie);
            this.nameMovie = (TextView) itemView.findViewById(R.id.name_movie);
            this.rating = (TextView) itemView.findViewById(R.id.rating);
            this.dateMovie = (TextView) itemView.findViewById(R.id.date);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
