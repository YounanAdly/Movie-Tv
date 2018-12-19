package com.example.android.movie;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.movie.Adapter.TV.TvCastAdapter;
import com.example.android.movie.Adapter.TV.TvListSeriesAdapter;
import com.example.android.movie.Adapter.TV.TvReveiwsAdapter;
import com.example.android.movie.Common.Common;
import com.example.android.movie.Controller.TvDetailsController;
import com.example.android.movie.Model.TV.TvList;
import com.example.android.movie.Model.TV.TvResult;
import com.example.android.movie.Model.TvCast.Cast;
import com.example.android.movie.Model.TvCast.TvCast;
import com.example.android.movie.Model.TvReviews.Reviews;
import com.example.android.movie.Model.TvReviews.TvReviews;
import com.example.android.movie.Model.TvTrailer.Result;
import com.example.android.movie.Model.TvTrailer.TvTrailer;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yuyu on 21-Nov-18.
 */

public class TvDetails extends YouTubeBaseActivity {
    TvResult selectedTv;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager reviewsTvLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView mReviewsTv;
    TvCastAdapter tvCastAdapter;
    private TvReveiwsAdapter tvReveiwsAdapter;

    //Views
    private TextView nameTv;
    private ImageView imageTv;
    private TextView dateTv;
    private TextView ratingTv;


    //Array Lists
    private ArrayList<TvResult> tvResults;
    private ArrayList<Result> resultsTv;
    private ArrayList<Cast> casts;
    private ArrayList<Reviews> tvReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_details);

        // new ArrayList
        tvResults = new ArrayList<>();
        resultsTv = new ArrayList<>();
        casts     = new ArrayList<>();
        tvReviews = new ArrayList<>();


        selectedTv = getIntent().getParcelableExtra(TvListSeriesAdapter.SELECTED_TV);

        mRecyclerView = (RecyclerView) findViewById(R.id.cast_tv_recycler);
        mReviewsTv = (RecyclerView) findViewById(R.id.reviews_tv_recycler);

        /// Reviews Adapter
        reviewsTvLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mReviewsTv.setLayoutManager(reviewsTvLayout);

        tvReveiwsAdapter = new TvReveiwsAdapter(tvReviews, TvDetails.this);
        mReviewsTv.setAdapter(tvReveiwsAdapter);

        // Tv Cast Adapter
        mLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        tvCastAdapter = new TvCastAdapter(casts, TvDetails.this);
        mRecyclerView.setAdapter(tvCastAdapter);


        loadTv();
        loadTriler();
        loadCast();
        loadReviews();
    }

    private void loadTv() {
        TvDetailsController.loadTv(new TvDetailsController.TvDetailsResponseListener() {
            @Override
            public void onResponse(TvList tvList) {
                tvResults.clear();
                tvResults.addAll(tvList.getTvResults());

                imageTv = (ImageView) findViewById(R.id.image_tv_det);
                nameTv = (TextView) findViewById(R.id.names_tv_det);
                ratingTv = (TextView) findViewById(R.id.rating_tv_det);
                dateTv = (TextView) findViewById(R.id.date_tv_det);

                nameTv.setText(selectedTv.getName());

                 String image = Common.IMAGE_LOAD + selectedTv.getPosterPath();
                Picasso.with(TvDetails.this)
                        .load(image)
                        .into(imageTv);

                ratingTv.setText(String.valueOf(selectedTv.getVoteAverage()));

                dateTv.setText(selectedTv.getFirstAirDate());
            }

            @Override
            public void onError(String error) {
                Log.d("===LoadMovies", "onResponse: " + error);
            }
        });

    }


    private void loadTriler() {
        TvDetailsController.loadTvTrailer(selectedTv.getId(), new TvDetailsController.TvDetailsTrilerListener() {
            @Override
            public void onResponse(TvTrailer tvTrailer) {
                resultsTv.clear();
                resultsTv.addAll(tvTrailer.getResults());
                YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);

                youTubePlayerView.initialize(Common.YT_API_KEY,
                        new YouTubePlayer.OnInitializedListener() {

                            @Override
                            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                YouTubePlayer youTubePlayer, boolean b) {
                                if (resultsTv.size() > 0) {
                                    youTubePlayer.cueVideo(resultsTv.get(0).getKey());

                                } else {
                                    youTubePlayer.cueVideo("Error");
                                }

                            }

                            @Override
                            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                YouTubeInitializationResult youTubeInitializationResult) {

                                Toast.makeText(TvDetails.this, "Youtube Failed!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void onError(String error) {

            }
        });

    }

    private void loadCast() {
        TvDetailsController.loadTvCast(selectedTv.getId(), new TvDetailsController.TvDetailsCastListener() {
            @Override
            public void onResponse(TvCast cast) {
                casts.clear();
                casts.addAll(cast.getCast());
                mRecyclerView.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void loadReviews() {
        TvDetailsController.loadTvReviews(selectedTv.getId(), new TvDetailsController.TvDetailsReviewsListener() {
            @Override
            public void onResponse(TvReviews reviews) {
                tvReviews.clear();
                tvReviews.addAll(reviews.getReviews());
                mReviewsTv.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {

            }
        });
    }

}


