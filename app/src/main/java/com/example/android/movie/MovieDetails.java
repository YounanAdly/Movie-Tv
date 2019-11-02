package com.example.android.movie;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.movie.Adapter.Movie.CastMovieAdapter;
import com.example.android.movie.Adapter.Movie.ListMovieAdapter;
import com.example.android.movie.Adapter.Movie.ReviewsMovieAdapter;
import com.example.android.movie.Common.Common;
import com.example.android.movie.Controller.MovieDetailsController;
import com.example.android.movie.Interface.MovieService;
import com.example.android.movie.Model.MovieCast.Cast;
import com.example.android.movie.Model.MovieCast.MovieCast;
import com.example.android.movie.Model.MovieReviews.MovieReviews;
import com.example.android.movie.Model.MovieReviews.Reviews;
import com.example.android.movie.Model.MovieTrailer.Key;
import com.example.android.movie.Model.MovieTrailer.MovieTrailer;
import com.example.android.movie.Model.MoviesList;
import com.example.android.movie.Model.Result;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by yuyu on 12-Nov-18.
 */

public class MovieDetails extends YouTubeBaseActivity {
    Result selectedMovie;
    private ArrayList<Cast> cast;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager reviewsMovieLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView reviewsMovie;
    private CastMovieAdapter castMovieAdapter;
    private ReviewsMovieAdapter reviewsMovieAdapter;
    private TextView name;
    private ImageView imageMovie;
    private TextView date;
    private TextView rating;
    private TextView overview;
    private ArrayList<Result> results;
    private ArrayList<Key> triler;
    private ArrayList<Reviews> reviews;
    MovieService mService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        triler = new ArrayList<>();

        selectedMovie = getIntent().getParcelableExtra(ListMovieAdapter.SELECTED_MOVIE);


        mRecyclerView = (RecyclerView) findViewById(R.id.cast_recycler);
        reviewsMovie = (RecyclerView) findViewById(R.id.reviews_recycler);


        mLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);


        reviewsMovieLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reviewsMovie.setLayoutManager(reviewsMovieLayout);


        reviews = new ArrayList<>();
        reviewsMovieAdapter = new ReviewsMovieAdapter(reviews, MovieDetails.this);
        reviewsMovie.setAdapter(reviewsMovieAdapter);

        cast = new ArrayList<>();
        castMovieAdapter = new CastMovieAdapter(cast, MovieDetails.this);
        mRecyclerView.setAdapter(castMovieAdapter);


        results = new ArrayList<>();
        mService = Common.getMovieService();


        loadTriler();
        loadMovies();
        loadCast();
        loadReviews();

    }

    private void loadMovies() {
        MovieDetailsController.loadMovies(new MovieDetailsController.MovieDetailsResponseListener() {
            @Override
            public void onResponse(MoviesList movies) {

                results.clear();
                results.addAll(movies.getResults());


                name = (TextView) findViewById(R.id.name_movie);
                overview = (TextView) findViewById(R.id.overview);
                rating = (TextView) findViewById(R.id.rating);
                date = (TextView) findViewById(R.id.date_det);
                imageMovie = (ImageView) findViewById(R.id.imageView);


                date.setText(selectedMovie.getReleaseDate());
                name.setText(selectedMovie.getTitle());
                overview.setText(selectedMovie.getOverview());

                rating.setText(String.valueOf(selectedMovie.getVoteAverage()));

                final String image = Common.IMAGE_LOAD + selectedMovie.getPosterPath();

                Picasso.with(MovieDetails.this)
                        .load(image)
                        .into(imageMovie);
            }

            @Override
            public void onError(String error) {
                Log.d("===LoadMovies", "onResponse: " + error);
            }
        });
    }


    private void loadTriler() {

        MovieDetailsController.loadTriler(selectedMovie.getId(), new MovieDetailsController.MovieDetailsTrilerListener() {
            @Override
            public void onResponse(final MovieTrailer movieTrailer) {
                triler.clear();
                triler.addAll(movieTrailer.getResults());

                YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);

                youTubePlayerView.initialize(Common.YT_API_KEY,
                        new YouTubePlayer.OnInitializedListener() {

                            @Override
                            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                YouTubePlayer youTubePlayer, boolean b) {
                                if (triler.size() > 0 ) {
                                    youTubePlayer.cueVideo(triler.get(0).getKey());

                                } else {
                                    youTubePlayer.cueVideo("Error");
                                }

                            }

                            @Override
                            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                YouTubeInitializationResult youTubeInitializationResult) {

                                Toast.makeText(MovieDetails.this, "Youtube Failed!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void onError(String error) {
                Log.d("===LoadMovies", "onResponse: " + error);
            }
        });
    }


    private void loadCast() {

        MovieDetailsController.loadCast(selectedMovie.getId(), new MovieDetailsController.MovieDetailsCastListener() {
            @Override
            public void onResponse(MovieCast movieCast) {
                cast.clear();
                cast.addAll(movieCast.getCast());
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Log.d("===LoadMovies", "onResponse: " + error);

            }
        });
    }

    private void loadReviews() {
        MovieDetailsController.loadReviews(selectedMovie.getId(), new MovieDetailsController.MovieDetailsReviewsListener() {
            @Override
            public void onResponse(MovieReviews movieReviews) {
                reviews.clear();
                reviews.addAll(movieReviews.getReviews());
                reviewsMovie.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Log.d("===LoadMovies", "onResponse: " + error);
            }
        });
    }
}