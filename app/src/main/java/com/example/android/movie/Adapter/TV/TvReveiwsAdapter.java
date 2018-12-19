package com.example.android.movie.Adapter.TV;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.movie.Model.TvReviews.Reviews;
import com.example.android.movie.R;

import java.util.ArrayList;

/**
 * Created by yuyu on 21-Nov-18.
 */

public class TvReveiwsAdapter extends RecyclerView.Adapter<TvReveiwsAdapter.MyViewHolder> {
    private ArrayList<Reviews> tvReviews;
    private Context context;

    public TvReveiwsAdapter(ArrayList<Reviews> reviews, Context context) {
        this.tvReviews = reviews;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reviews, parent, false);
        return new TvReveiwsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.content.setText(tvReviews.get(position).getContent());
        holder.author.setText(tvReviews.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return tvReviews.size();
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
