package com.example.android.movie.network;

import com.example.android.movie.model.MovieCast.MovieCast;
import com.example.android.movie.model.MovieReviews.MovieReviews;
import com.example.android.movie.model.MoviesList;
import com.example.android.movie.model.TV.TvList;
import com.example.android.movie.model.TvCast.TvCast;
import com.example.android.movie.model.TvReviews.TvReviews;
import com.example.android.movie.repository.Repository;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie/popular?" + Repository.API_KEY + "&language=en-US")
    Single<MoviesList> getPopular(@Query("api_key") String api_key);

    @GET("movie/now_playing?" + Repository.API_KEY + "&language=en-US")
    Single<MoviesList> getNowPlaying(@Query("api_key") String api_key);

    @GET("movie/top_rated?" + Repository.API_KEY + "&language=en-US")
    Single<MoviesList> getTopRated(@Query("api_key") String api_key);

    @GET("movie/upcoming?" + Repository.API_KEY + "&language=en-US")
    Single<MoviesList> getUpcoming(@Query("api_key") String api_key);

    //Cast
    @GET("movie/{movie_id}" + "/credits?" + Repository.API_KEY)
    Single<MovieCast> getCast(
            @Path("movie_id") int movieId,
            @Query("api_key") String api_key);

    //Reviews
    @GET("movie/{movie_id}" + "/reviews?" + Repository.API_KEY + "&lanuage=en-US")
    Single<MovieReviews> getReviews(
            @Path("movie_id") int movieId,
            @Query("api_key") String api_key);


    //Tv Api
    @GET("tv/popular?" + Repository.API_KEY + "&language=en-US")
    Single<TvList> getTvPopular(@Query("api_key") String api_key);

    @GET("tv/airing_today?" + Repository.API_KEY + "&language=en-US")
    Single<TvList> getAiringToday(@Query("api_key") String api_key);

    @GET("tv/top_rated?" + Repository.API_KEY + "&language=en-US")
    Single<TvList> getTvTopRated(@Query("api_key") String api_key);

    @GET("tv/on_the_air?" + Repository.API_KEY + "&language=en-US")
    Single<TvList> getOnAir(@Query("api_key") String api_key);

    //Cast
    @GET("tv/{tv_id}" + "/credits?" + Repository.API_KEY)
    Single<TvCast> getTvCast(
            @Path("tv_id") int groupId,
            @Query("api_key") String api_key);

    //Reviews
    @GET("tv/{tv_id}" + "/reviews?" + Repository.API_KEY + "&lanuage=en-US")
    Single<TvReviews> getTvReviews(
            @Path("tv_id") int groupId,
            @Query("api_key") String api_key);
}
