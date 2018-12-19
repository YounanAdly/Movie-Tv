package com.example.android.movie.Interface;

import com.example.android.movie.Common.Common;
import com.example.android.movie.Model.TV.TvList;
import com.example.android.movie.Model.TvCast.TvCast;
import com.example.android.movie.Model.TvReviews.TvReviews;
import com.example.android.movie.Model.TvTrailer.TvTrailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by yuyu on 21-Nov-18.
 */

public interface TvService {

    @GET("popular?" + Common.API_KEY + "&language=en-US")
    Call<TvList> getTvPopular(@Query("api_key") String api_key);

    @GET("airing_today?" + Common.API_KEY + "&language=en-US")
    Call<TvList> getAiringToday(@Query("api_key") String api_key);


    @GET("top_rated?" + Common.API_KEY + "&language=en-US")
    Call<TvList> getTopRated(@Query("api_key") String api_key);


    @GET("on_the_air?" + Common.API_KEY + "&language=en-US")
    Call<TvList> getOnAir(@Query("api_key") String api_key);




    //Trailer
    @GET("{tv_id}" + "/videos?" + Common.API_KEY + "&lanuage=en-US")
    Call<TvTrailer> getTvTrailer(
            @Path("tv_id") int groupId,
            @Query("api_key") String api_key);

    //Cast
    @GET( "{tv_id}" +"/credits?" + Common.API_KEY )
    Call<TvCast> getTvCast(
            @Path("tv_id") int groupId,
            @Query("api_key") String api_key);

    //Reviews
    @GET("{tv_id}" + "/reviews?" + Common.API_KEY + "&lanuage=en-US")
    Call<TvReviews> getTvReviews(
            @Path("tv_id") int groupId,
            @Query("api_key") String api_key);

}
