package com.example.android.movie.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yuyu on 11-Nov-18.
 */
public class RetrofitClient {
    public static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;

    }
}
