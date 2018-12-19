package com.example.android.movie.Adapter.TV;

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
import com.example.android.movie.Model.TV.TvResult;
import com.example.android.movie.R;
import com.example.android.movie.TvDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yuyu on 21-Nov-18.
 */

public class TvListSeriesAdapter extends RecyclerView.Adapter<TvListSeriesAdapter.MyViewHolder> {

    private ArrayList<TvResult> tvResults;
    private Context context;

    public static final String SELECTED_TV = "selected_tv";
    private int lastPosition = -1;

    public TvListSeriesAdapter(ArrayList<TvResult> tvResults, Context context) {
        this.tvResults = tvResults;
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
        //////////////////////

        String imagee = Common.IMAGE_LOAD + tvResults.get(position).getPosterPath();

        Picasso.with(context)
                .load(imagee)
                .into(holder.imagetv);

        holder.nametv.setText(tvResults.get(position).getName());

        holder.ratingtv.setText(String.valueOf(tvResults.get(position).getVoteAverage()));




        holder.datetv.setText(tvResults.get(position).getFirstAirDate());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, TvDetails.class);
                TvResult tvResult = tvResults.get(position);


                intent.putExtra(SELECTED_TV, tvResult);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return tvResults.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imagetv;
        TextView nametv;
        TextView ratingtv;
        TextView datetv;
        ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.imagetv = (ImageView) itemView.findViewById(R.id.image_tv);
            this.nametv = (TextView) itemView.findViewById(R.id.name_tv);
            this.ratingtv = (TextView) itemView.findViewById(R.id.rating_tv);
            this.datetv = (TextView) itemView.findViewById(R.id.date_tv);
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
