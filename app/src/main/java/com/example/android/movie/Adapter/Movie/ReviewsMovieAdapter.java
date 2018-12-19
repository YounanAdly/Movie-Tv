package com.example.android.movie.Adapter.Movie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.movie.Model.MovieReviews.Reviews;
import com.example.android.movie.R;

import java.util.ArrayList;

/**
 * Created by yuyu on 18-Nov-18.
 */

public class ReviewsMovieAdapter extends RecyclerView.Adapter<ReviewsMovieAdapter.MyViewHolder> {

    private ArrayList<Reviews> reviews;
    private Context context;

    public ReviewsMovieAdapter(ArrayList<Reviews> reviews, Context context) {
        this.reviews = reviews;
        this.context = context;
    }

    @Override
    public ReviewsMovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reviews, parent, false);
        return new ReviewsMovieAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewsMovieAdapter.MyViewHolder holder, int position) {
        holder.content.setText(reviews.get(position).getContent());
        holder.author.setText(reviews.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        TextView author;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.content = (TextView)itemView.findViewById(R.id.content);
            this.author = (TextView)itemView.findViewById(R.id.author);
        }
    }
}
