package com.example.android.movie.data;

import com.example.android.movie.Pojo.MovieCast.MovieCast;
import com.example.android.movie.Pojo.MovieReviews.MovieReviews;
import com.example.android.movie.Pojo.MovieTrailer.MovieTrailer;
import com.example.android.movie.Pojo.MoviesList;
import com.example.android.movie.Pojo.TV.TvList;
import com.example.android.movie.Pojo.TvCast.TvCast;
import com.example.android.movie.Pojo.TvReviews.TvReviews;
import com.example.android.movie.Pojo.TvTrailer.TvTrailer;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/popular?" + MovieClient.API_KEY + "&language=en-US")
    Single<MoviesList> getPopular(@Query("api_key") String api_key);

    @GET("movie/now_playing?" + MovieClient.API_KEY + "&language=en-US")
    Single<MoviesList> getNowPlaying(@Query("api_key") String api_key);

    @GET("movie/top_rated?" + MovieClient.API_KEY + "&language=en-US")
    Single<MoviesList> getTopRated(@Query("api_key") String api_key);

    @GET("movie/upcoming?" + MovieClient.API_KEY + "&language=en-US")
    Single<MoviesList> getUpcoming(@Query("api_key") String api_key);

    //Cast
    @GET("movie/{movie_id}" + "/credits?" + MovieClient.API_KEY)
    Single<MovieCast> getCast(
            @Path("movie_id") int movieId,
            @Query("api_key") String api_key);

    //Reviews
    @GET("movie/{movie_id}" + "/reviews?" + MovieClient.API_KEY + "&lanuage=en-US")
    Single<MovieReviews> getReviews(
            @Path("movie_id") int movieId,
            @Query("api_key") String api_key);


    //Tv Api
    @GET("tv/popular?" + MovieClient.API_KEY + "&language=en-US")
    Single<TvList> getTvPopular(@Query("api_key") String api_key);

    @GET("tv/airing_today?" + MovieClient.API_KEY + "&language=en-US")
    Single<TvList> getAiringToday(@Query("api_key") String api_key);

    @GET("tv/top_rated?" + MovieClient.API_KEY + "&language=en-US")
    Single<TvList> getTvTopRated(@Query("api_key") String api_key);

    @GET("tv/on_the_air?" + MovieClient.API_KEY + "&language=en-US")
    Single<TvList> getOnAir(@Query("api_key") String api_key);

    //Cast
    @GET("tv/{tv_id}" + "/credits?" + MovieClient.API_KEY)
    Single<TvCast> getTvCast(
            @Path("tv_id") int groupId,
            @Query("api_key") String api_key);

    //Reviews
    @GET("tv/{tv_id}" + "/reviews?" + MovieClient.API_KEY + "&lanuage=en-US")
    Single<TvReviews> getTvReviews(
            @Path("tv_id") int groupId,
            @Query("api_key") String api_key);
}
