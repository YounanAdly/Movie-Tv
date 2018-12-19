package com.example.android.movie.Interface;

import com.example.android.movie.Common.Common;
import com.example.android.movie.Model.MovieCast.MovieCast;
import com.example.android.movie.Model.MovieReviews.MovieReviews;
import com.example.android.movie.Model.MovieTrailer.MovieTrailer;
import com.example.android.movie.Model.MoviesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by yuyu on 11-Nov-18.
 */

public interface MovieService {

    @GET("popular?" + Common.API_KEY + "&language=en-US")
    Call<MoviesList> getPopular(@Query("api_key") String api_key);

    @GET("now_playing?" + Common.API_KEY + "&language=en-US")
    Call<MoviesList> getNowPlaying(@Query("api_key") String api_key);


    @GET("top_rated?" + Common.API_KEY + "&language=en-US")
    Call<MoviesList> getTopRated(@Query("api_key") String api_key);


    @GET("upcoming?" + Common.API_KEY + "&language=en-US")
    Call<MoviesList> getUpcoming(@Query("api_key") String api_key);


    //Cast
    @GET( "{movie_id}" +"/credits?" + Common.API_KEY )
    Call<MovieCast> getCast(
            @Path("movie_id") int groupId,
            @Query("api_key") String api_key);

    //Trailer
    @GET("{movie_id}" + "/videos?" + Common.API_KEY + "&lanuage=en-US")
    Call<MovieTrailer> getTrailer(
            @Path("movie_id") int groupId,
            @Query("api_key") String api_key);


    //Reviews
    @GET("{movie_id}" + "/reviews?" + Common.API_KEY + "&lanuage=en-US")
    Call<MovieReviews> getReviews(
            @Path("movie_id") int groupId,
            @Query("api_key") String api_key);


}
