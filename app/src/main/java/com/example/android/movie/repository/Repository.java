package com.example.android.movie.repository;

import com.example.android.movie.model.MovieCast.MovieCast;
import com.example.android.movie.model.MovieReviews.MovieReviews;
import com.example.android.movie.model.MoviesList;
import com.example.android.movie.model.TV.TvList;
import com.example.android.movie.model.TvCast.TvCast;
import com.example.android.movie.model.TvReviews.TvReviews;
import com.example.android.movie.network.ApiService;
import com.example.android.movie.ui.Movie.MovieDetails;
import com.example.android.movie.ui.Tv.TvDetails;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class Repository {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String IMAGE_LOAD = "https://image.tmdb.org/t/p/w500";
    public static final String API_KEY = "fcad72109732ee8d0f7342d11443cf24";
    private final ApiService apiService;

    @Inject
    public Repository(ApiService apiService) {
        this.apiService = apiService;
    }

    //Movie
    public Single<MoviesList> getPopular() {
        return apiService.getPopular(API_KEY);
    }

    public Single<MoviesList> getNowPlaying() {
        return apiService.getNowPlaying(API_KEY);
    }

    public Single<MoviesList> getTopRated() {
        return apiService.getTopRated(API_KEY);
    }

    public Single<MoviesList> getUpcoming() {
        return apiService.getUpcoming(API_KEY);
    }

    public Single<MovieCast> getCast() {
        return apiService.getCast(MovieDetails.selectedMovie.getId(), API_KEY);
    }

    public Single<MovieReviews> getReviews() {
        return apiService.getReviews(MovieDetails.selectedMovie.getId(), API_KEY);
    }

    //TV
    public Single<TvList> getTvPopular() {
        return apiService.getTvPopular(API_KEY);
    }

    public Single<TvList> getAiringToday() {
        return apiService.getAiringToday(API_KEY);
    }

    public Single<TvList> getTvTopRated() {
        return apiService.getTvTopRated(API_KEY);
    }

    public Single<TvList> getOnAir() {
        return apiService.getOnAir(API_KEY);
    }

    public Single<TvCast> getTvCast() {
        return apiService.getTvCast(TvDetails.selectedMovie.getId(), API_KEY);
    }

    public Single<TvReviews> getTvReviews() {
        return apiService.getTvReviews(TvDetails.selectedMovie.getId(), API_KEY);
    }

}
