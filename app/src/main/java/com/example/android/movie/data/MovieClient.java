package com.example.android.movie.data;

import com.example.android.movie.Pojo.MovieCast.MovieCast;
import com.example.android.movie.Pojo.MovieReviews.MovieReviews;
import com.example.android.movie.Pojo.MoviesList;
import com.example.android.movie.Pojo.TV.TvList;
import com.example.android.movie.Pojo.TvCast.TvCast;
import com.example.android.movie.Pojo.TvReviews.TvReviews;
import com.example.android.movie.ui.Movie.MovieDetails;
import com.example.android.movie.ui.Tv.TvDetails;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String IMAGE_LOAD = "https://image.tmdb.org/t/p/w500";
    public static final String API_KEY = "fcad72109732ee8d0f7342d11443cf24";
    private ApiInterface apiInterface;
    private static MovieClient INSTANCE;


    public MovieClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static MovieClient getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new MovieClient();
        }
        return INSTANCE;
    }


    //Movie
    public Single<MoviesList> getPopular() {
        return apiInterface.getPopular(API_KEY);
    }

    public Single<MoviesList> getNowPlaying() {
        return apiInterface.getNowPlaying(API_KEY);
    }

    public Single<MoviesList> getTopRated() {
        return apiInterface.getTopRated(API_KEY);
    }

    public Single<MoviesList> getUpcoming() {
        return apiInterface.getUpcoming(API_KEY);
    }

    public Single<MovieCast> getCast() {
        return apiInterface.getCast(MovieDetails.selectedMovie.getId(), API_KEY);
    }

    public Single<MovieReviews> getReviews() {
        return apiInterface.getReviews(MovieDetails.selectedMovie.getId(), API_KEY);
    }

    //TV
    public Single<TvList> getTvPopular() {
        return apiInterface.getTvPopular(API_KEY);
    }

    public Single<TvList> getAiringToday() {
        return apiInterface.getAiringToday(API_KEY);
    }

    public Single<TvList> getTvTopRated() {
        return apiInterface.getTvTopRated(API_KEY);
    }

    public Single<TvList> getOnAir() {
        return apiInterface.getOnAir(API_KEY);
    }

    public Single<TvCast> getTvCast() {
        return apiInterface.getTvCast(TvDetails.selectedMovie.getId(), API_KEY);
    }

    public Single<TvReviews> getTvReviews() {
        return apiInterface.getTvReviews(TvDetails.selectedMovie.getId(), API_KEY);
    }

}
