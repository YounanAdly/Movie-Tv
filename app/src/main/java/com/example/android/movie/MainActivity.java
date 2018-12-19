package com.example.android.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.movie.Adapter.Movie.ListMovieAdapter;
import com.example.android.movie.Common.Common;
import com.example.android.movie.Interface.MovieService;
import com.example.android.movie.Model.MoviesList;
import com.example.android.movie.Model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private ListMovieAdapter movieAdapter;
    private ArrayList<Result> results;
    MovieService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        results = new ArrayList<>();
        movieAdapter = new ListMovieAdapter(results, MainActivity.this);
        mRecyclerView.setAdapter(movieAdapter);


        mService = Common.getMovieService();
        loadMovies();
    }


    private void loadMovies() {


        mService.getPopular(Common.API_KEY).enqueue(new Callback<MoviesList>() {

            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                results.clear();
                results.addAll(response.body().getResults());
                mRecyclerView.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Log.d("===LoadMovies", "onResponse: " + t);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.now_playing:

                mService.getNowPlaying(Common.API_KEY).enqueue(new Callback<MoviesList>() {

                    @Override
                    public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                        results.clear();
                        results.addAll(response.body().getResults());
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<MoviesList> call, Throwable t) {
                        Log.d("===LoadMovies", "onResponse: " + t);
                    }
                });
                return true;
            //////////////////////
            case R.id.popular:
                mService.getPopular(Common.API_KEY).enqueue(new Callback<MoviesList>() {

                    @Override
                    public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                        results.clear();
                        results.addAll(response.body().getResults());
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<MoviesList> call, Throwable t) {
                        Log.d("===LoadMovies", "onResponse: " + t);
                    }
                });
                return true;

            //////////////////////
            case R.id.top_rated:
                mService.getTopRated(Common.API_KEY).enqueue(new Callback<MoviesList>() {

                    @Override
                    public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                        results.clear();
                        results.addAll(response.body().getResults());
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<MoviesList> call, Throwable t) {
                        Log.d("===LoadMovies", "onResponse: " + t);
                    }
                });
                return true;
            //////////////////////////
            case R.id.upcoming:
                mService.getUpcoming(Common.API_KEY).enqueue(new Callback<MoviesList>() {

                    @Override
                    public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                        results.clear();
                        results.addAll(response.body().getResults());
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<MoviesList> call, Throwable t) {
                        Log.d("===LoadMovies", "onResponse: " + t);
                    }
                });
                return true;


        }
        return super.onOptionsItemSelected(item);
    }


}
