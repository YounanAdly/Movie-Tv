package com.example.android.movie.Controller;

import android.util.Log;

import com.example.android.movie.Common.Common;
import com.example.android.movie.Interface.TvService;
import com.example.android.movie.Model.TV.TvList;
import com.example.android.movie.Model.TvCast.TvCast;
import com.example.android.movie.Model.TvReviews.TvReviews;
import com.example.android.movie.Model.TvTrailer.TvTrailer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuyu on 21-Nov-18.
 */

public class TvDetailsController {
    static TvService mService = Common.getTvService();

    public interface TvDetailsResponseListener {
        void onResponse(TvList tvList);

        void onError(String error);
    }

    public interface TvDetailsTrilerListener {
        void onResponse(TvTrailer tvTrailer);

        void onError(String error);
    }

    public interface TvDetailsCastListener {
        void onResponse(TvCast cast);

        void onError(String error);
    }

    public interface TvDetailsReviewsListener {
        void onResponse(TvReviews reviews);

        void onError(String error);
    }

    public static void loadTv(final TvDetailsController.TvDetailsResponseListener listener) {

        mService.getTvPopular(Common.API_KEY).enqueue(new Callback<TvList>() {

            @Override
            public void onResponse(Call<TvList> call, Response<TvList> response) {
                if (response.body() != null) {
                    listener.onResponse(response.body());
                } else {
                    listener.onError("Network Error!");
                }
            }

            @Override
            public void onFailure(Call<TvList> call, Throwable t) {
                Log.d("===LoadMovies", "onResponse: " + t);
            }
        });

    }

    public static void loadTvTrailer(int movieId, final TvDetailsController.TvDetailsTrilerListener listener) {
        mService.getTvTrailer(movieId, Common.API_KEY).enqueue(new Callback<TvTrailer>() {
            @Override
            public void onResponse(Call<TvTrailer> call, Response<TvTrailer> response) {
                if (response.body() != null) {
                    listener.onResponse(response.body());
                } else {
                    listener.onError("Network Error!");
                }
            }

            @Override
            public void onFailure(Call<TvTrailer> call, Throwable t) {
                Log.d("===LoadMovies", "onResponse: " + t);
            }
        });
    }

    public static void loadTvCast(int movieId, final TvDetailsController.TvDetailsCastListener listener) {
        mService.getTvCast(movieId, Common.API_KEY).enqueue(new Callback<TvCast>() {

            @Override
            public void onResponse(Call<TvCast> call, Response<TvCast> response) {

                if (response.body() != null) {
                    listener.onResponse(response.body());
                } else {
                    listener.onError("Network Error!");
                }

            }

            @Override
            public void onFailure(Call<TvCast> call, Throwable t) {
                Log.d("===LoadMovies", "onResponse: " + t);
            }
        });
    }

    public static void loadTvReviews(int movieId, final TvDetailsController.TvDetailsReviewsListener listener) {
        mService.getTvReviews(movieId, Common.API_KEY).enqueue(new Callback<TvReviews>() {
            @Override
            public void onResponse(Call<TvReviews> call, Response<TvReviews> response) {
                if (response.body() != null) {
                    listener.onResponse(response.body());
                } else {
                    listener.onError("Network Error!");
                }
            }

            @Override
            public void onFailure(Call<TvReviews> call, Throwable t) {

            }
        });
    }
}