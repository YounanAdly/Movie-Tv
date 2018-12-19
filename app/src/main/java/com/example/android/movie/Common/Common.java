package com.example.android.movie.Common;

import com.example.android.movie.Interface.MovieService;
import com.example.android.movie.Interface.TvService;
import com.example.android.movie.Remote.RetrofitClient;

/**
 * Created by yuyu on 11-Nov-18.
 */

public class Common {

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/" ;

    private static final String TV_URL = "https://api.themoviedb.org/3/tv/";

    public static final String IMAGE_LOAD = "https://image.tmdb.org/t/p/w500";

    public static final String API_KEY = "fcad72109732ee8d0f7342d11443cf24";

    public static final String YT_API_KEY = "AIzaSyCd19nXn94j6-r9w1QonOOto72blTMwMfA";



    public static MovieService getMovieService() {
        return RetrofitClient.getClient(BASE_URL).create(MovieService.class);

    }

    public static TvService getTvService(){
        return RetrofitClient.getClient(TV_URL).create(TvService.class);
    }

}
