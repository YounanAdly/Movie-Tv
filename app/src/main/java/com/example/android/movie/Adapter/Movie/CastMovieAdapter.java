package com.example.android.movie.Adapter.Movie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movie.Common.Common;
import com.example.android.movie.Model.MovieCast.Cast;

import com.example.android.movie.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yuyu on 15-Nov-18.
 */

public class CastMovieAdapter extends RecyclerView.Adapter<CastMovieAdapter.MyViewHolder> {

    private ArrayList<Cast> cast;

    private Context context;

    public CastMovieAdapter(ArrayList<Cast> cast, Context context) {
        this.cast = cast;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cast, parent, false);
        return new CastMovieAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.name.setText(cast.get(position).getName());

        holder.actorChar.setText(cast.get(position).getCharacter());

         String image = Common.IMAGE_LOAD + cast.get(position).getProfilePath();

        Picasso.with(context)
                .load(image)
                .into(holder.imageCast);

    }

    @Override
    public int getItemCount() {
        return cast.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageCast;
        TextView actorChar;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.imageCast = (ImageView) itemView.findViewById(R.id.cast_image);
            this.actorChar = (TextView) itemView.findViewById(R.id.actor_char);
        }
    }
}
