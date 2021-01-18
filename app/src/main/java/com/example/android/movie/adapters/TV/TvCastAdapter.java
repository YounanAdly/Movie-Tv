package com.example.android.movie.adapters.TV;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movie.model.TvCast.Cast;
import com.example.android.movie.R;
import com.example.android.movie.repository.Repository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TvCastAdapter extends RecyclerView.Adapter<TvCastAdapter.MyViewHolder> {

    private List<Cast> cast = new ArrayList<>();
    private Context context;

    public TvCastAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cast, parent, false);
        return new TvCastAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.name.setText(cast.get(position).getName());
        holder.actorChar.setText(cast.get(position).getCharacter());

        String image = Repository.IMAGE_LOAD + cast.get(position).getProfilePath();
        Picasso.with(context)
                .load(image)
                .into(holder.imageCast);
    }

    @Override
    public int getItemCount() {
        return cast.size();
    }

    public void setList(List<Cast> cast) {
        this.cast = cast;
        notifyDataSetChanged();
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
