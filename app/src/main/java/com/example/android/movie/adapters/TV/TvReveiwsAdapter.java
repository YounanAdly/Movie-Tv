package com.example.android.movie.adapters.TV;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.movie.model.TvReviews.Reviews;
import com.example.android.movie.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class TvReveiwsAdapter extends RecyclerView.Adapter<TvReveiwsAdapter.MyViewHolder> {

    private List<Reviews> tvReviews = new ArrayList<>();
    private Context context;

    public TvReveiwsAdapter(Context context) {
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

    public void setList(List<Reviews> tvReviews) {
        this.tvReviews = tvReviews;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        TextView author;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.content = (TextView) itemView.findViewById(R.id.content);
            this.author = (TextView) itemView.findViewById(R.id.author);
        }
    }
}
