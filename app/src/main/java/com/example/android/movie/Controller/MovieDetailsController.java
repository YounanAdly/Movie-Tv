package com.example.android.movie.Controller;

import android.util.Log;

import com.example.android.movie.Common.Common;
import com.example.android.movie.Interface.MovieService;
import com.example.android.movie.Model.MovieCast.MovieCast;
import com.example.android.movie.Model.MovieReviews.MovieReviews;
import com.example.android.movie.Model.MovieTrailer.MovieTrailer;
import com.example.android.movie.Model.MoviesList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuyu on 21-Nov-18.
 */


public class MovieDetailsController {

    static MovieService mService = Common.getMovieService();

    public interface MovieDetailsReviewsListener{
        void onResponse (MovieReviews movieReviews);
        void onError (String error);
    }

    public interface MovieDetailsCastListener{
    void onResponse(MovieCast movieCast);
    void onError (String error);
}

    public interface MovieDetailsResponseListener {
        void onResponse(MoviesList movies);

        void onError(String error);
    }

    public interface MovieDetailsTrilerListener{
        void onResponse(MovieTrailer movieTrailer);
        void onError (String error);
    }

    public static void loadMovies(final MovieDetailsResponseListener listener) {

        mService.getPopular(Common.API_KEY).enqueue(new Callback<MoviesList>() {

            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                if (response.body() != null) {
                    listener.onResponse(response.body());
                } else {
                    listener.onError("Network Error!");
                }
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Log.d("===LoadMovies", "onResponse: " + t);
            }
        });
    }

    public static void loadTriler(int movieID, final  MovieDetailsTrilerListener listener) {


        mService.getTrailer(movieID, Common.API_KEY).enqueue(new Callback<MovieTrailer>() {

            @Override
            public void onResponse(Call<MovieTrailer> call,  Response<MovieTrailer> response) {
                if (response.body() != null) {
                    listener.onResponse(response.body());
                } else {
                    listener.onError("Network Error!");
                }

            }

            @Override
            public void onFailure(Call<MovieTrailer> call, Throwable t) {
                Log.d("===LoadMovies", "onResponse: " + t);

            }
        });
    }

    public static void loadCast(int movieId, final MovieDetailsCastListener listener){
        mService.getCast(movieId, Common.API_KEY).enqueue(new Callback<MovieCast>() {
            @Override
            public void onResponse(Call<MovieCast> call, final Response<MovieCast> response) {
                if (response.body() != null) {
                    listener.onResponse(response.body());
                } else {
                    listener.onError("Network Error!");
                }
            }

            @Override
            public void onFailure(Call<MovieCast> call, Throwable t) {
                Log.d("===LoadMovies", "onResponse: " + t);
            }
        });
    }

    public static void loadReviews(int movieId,final MovieDetailsReviewsListener listener ){
        mService.getReviews(movieId, Common.API_KEY).enqueue(new Callback<MovieReviews>() {
            @Override
            public void onResponse(Call<MovieReviews> call, Response<MovieReviews> response) {
                if (response.body() != null) {
                    listener.onResponse(response.body());
                } else {
                    listener.onError("Network Error!");
                }
            }

            @Override
            public void onFailure(Call<MovieReviews> call, Throwable t) {
                Log.d("===LoadMovies", "onResponse: " + t);
            }
        });

    }
}
