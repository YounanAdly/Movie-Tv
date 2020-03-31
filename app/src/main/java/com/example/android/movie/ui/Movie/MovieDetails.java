package com.example.android.movie.ui.Movie;

import android.os.Bundle;

import com.example.android.movie.Adapter.Movie.CastMovieAdapter;
import com.example.android.movie.Adapter.Movie.ListMovieAdapter;
import com.example.android.movie.Adapter.Movie.ReviewsMovieAdapter;
import com.example.android.movie.Pojo.Result;
import com.example.android.movie.R;
import com.example.android.movie.ViewModel.MovieViewModel;
import com.example.android.movie.data.MovieClient;
import com.example.android.movie.databinding.MovieDetailsBinding;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MovieDetails extends AppCompatActivity {
    MovieDetailsBinding mBinding;
    public static Result selectedMovie;
    MovieViewModel movieViewModel;

    CastMovieAdapter mAdapterCast;
    ReviewsMovieAdapter mAdapterReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.movie_details);

        selectedMovie = getIntent().getParcelableExtra(ListMovieAdapter.SELECTED_MOVIE);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);


        MovieViewModel.moviesMutableLiveData.observe(this,moviesList ->  {
                mBinding.nameMovie.setText(selectedMovie.getTitle());
                mBinding.rating.setText(String.valueOf(selectedMovie.getVoteAverage()));
                mBinding.dateDet.setText(selectedMovie.getReleaseDate());
                mBinding.overview.setText(selectedMovie.getOverview());

                Picasso.with(MovieDetails.this)
                        .load(MovieClient.IMAGE_LOAD + selectedMovie.getPosterPath())
                        .into(mBinding.movieImage);
        });

        movieViewModel.getCast();
        MovieViewModel.castMutableLiveData.observe(this, movieCast -> mAdapterCast.setList(movieCast.getCast()));

        movieViewModel.getReviews();
        MovieViewModel.reviewsMutableLiveData.observe(this,movieReviews -> mAdapterReviews.setList(movieReviews.getReviews()));

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (mAdapterCast == null) {
            mAdapterCast = new CastMovieAdapter(MovieDetails.this);
            mBinding.castRecycler.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
            mBinding.castRecycler.setAdapter(mAdapterCast);
            mBinding.castRecycler.setItemAnimator(new DefaultItemAnimator());
            mBinding.castRecycler.setNestedScrollingEnabled(true);
        } else {
            mAdapterCast.notifyDataSetChanged();
        }

        if (mAdapterReviews == null) {
            mAdapterReviews = new ReviewsMovieAdapter(MovieDetails.this);
            mBinding.reviewsRecycler.setLayoutManager(new LinearLayoutManager(this));
            mBinding.reviewsRecycler.setAdapter(mAdapterReviews);
            mBinding.reviewsRecycler.setItemAnimator(new DefaultItemAnimator());
            mBinding.reviewsRecycler.setNestedScrollingEnabled(true);
        } else {
            mAdapterReviews.notifyDataSetChanged();
        }
    }
}
