package com.example.android.movie.adapters.Movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.movie.model.MovieReviews.Reviews;
import com.example.android.movie.R;

import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewsMovieAdapter extends RecyclerView.Adapter<ReviewsMovieAdapter.MyViewHolder> {

    private List<Reviews> reviews = new ArrayList<>();
    private Context context;

    public ReviewsMovieAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reviews, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.content.setText(reviews.get(position).getContent());
        holder.author.setText(reviews.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setList(List<Reviews> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
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
