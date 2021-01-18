package com.example.android.movie.adapters.TV;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movie.model.TV.TvResult;
import com.example.android.movie.R;
import com.example.android.movie.repository.Repository;
import com.example.android.movie.ui.Tv.TvDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class TvListSeriesAdapter extends RecyclerView.Adapter<TvListSeriesAdapter.MyViewHolder> {

    private List<TvResult> tvResults = new ArrayList<>();
    private Context context;

    public static final String SELECTED_TV = "selected_tv";
    private int lastPosition = -1;

    public TvListSeriesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_tv_views, parent, false);
        return new TvListSeriesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Animation Scroll
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;

        String imagee = Repository.IMAGE_LOAD + tvResults.get(position).getPosterPath();
        Picasso.with(context)
                .load(imagee)
                .into(holder.imagetv);

        holder.nametv.setText(tvResults.get(position).getName());
        holder.ratingtv.setText(String.valueOf(tvResults.get(position).getVoteAverage()));
        holder.datetv.setText(tvResults.get(position).getFirstAirDate());

    }

    @Override
    public int getItemCount() {
        return tvResults.size();
    }


    public void setList(List<TvResult> tvResults) {
        this.tvResults = tvResults;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imagetv;
        TextView nametv;
        TextView ratingtv;
        TextView datetv;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.imagetv = (ImageView) itemView.findViewById(R.id.image_tv);
            this.nametv = (TextView) itemView.findViewById(R.id.name_tv);
            this.ratingtv = (TextView) itemView.findViewById(R.id.rating_tv);
            this.datetv = (TextView) itemView.findViewById(R.id.date_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, TvDetails.class);
            TvResult tvResult = tvResults.get(getAdapterPosition());
            intent.putExtra(SELECTED_TV, tvResult);
            context.startActivity(intent);
        }

    }
}
