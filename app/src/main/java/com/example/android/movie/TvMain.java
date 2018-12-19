package com.example.android.movie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.movie.Adapter.TV.TvListSeriesAdapter;
import com.example.android.movie.Common.Common;
import com.example.android.movie.Interface.TvService;
import com.example.android.movie.Model.TV.TvList;
import com.example.android.movie.Model.TV.TvResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuyu on 21-Nov-18.
 */

public class TvMain extends AppCompatActivity {
    private RecyclerView.LayoutManager mTvLayoutManager;
    private RecyclerView mTvRecycler;
    private TvListSeriesAdapter mTvAdapter;
    private ArrayList<TvResult> tvResults;
    TvService mTvService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_main);

        mTvRecycler = (RecyclerView) findViewById(R.id.tv_recycler);


        mTvLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mTvRecycler.setLayoutManager(mTvLayoutManager);

        tvResults = new ArrayList<>();
        mTvAdapter = new TvListSeriesAdapter(tvResults, TvMain.this);
        mTvRecycler.setAdapter(mTvAdapter);


        mTvService = Common.getTvService();
        loadTv();

    }


    private void loadTv() {


        mTvService.getTvPopular(Common.API_KEY).enqueue(new Callback<TvList>() {

            @Override
            public void onResponse(Call<TvList> call, Response<TvList> response) {
                tvResults.clear();
                tvResults.addAll(response.body().getTvResults());
                mTvRecycler.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TvList> call, Throwable t) {
                Log.d("===LoadMovies", "onResponse: " + t);
            }


        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu_tv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.airing_today:

                mTvService.getAiringToday(Common.API_KEY).enqueue(new Callback<TvList>() {

                    @Override
                    public void onResponse(Call<TvList> call, Response<TvList> response) {
                        tvResults.clear();
                        tvResults.addAll(response.body().getTvResults());
                        mTvRecycler.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<TvList> call, Throwable t) {
                        Log.d("===LoadMovies", "onResponse: " + t);
                    }
                });
                return true;
            //////////////////////
            case R.id.popular_tv:
                mTvService.getTvPopular(Common.API_KEY).enqueue(new Callback<TvList>() {

                    @Override
                    public void onResponse(Call<TvList> call, Response<TvList> response) {
                        tvResults.clear();
                        tvResults.addAll(response.body().getTvResults());
                        mTvRecycler.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<TvList> call, Throwable t) {
                        Log.d("===LoadMovies", "onResponse: " + t);
                    }
                });
                return true;

            //////////////////////
            case R.id.top_rated_tv:
                mTvService.getTopRated(Common.API_KEY).enqueue(new Callback<TvList>() {

                    @Override
                    public void onResponse(Call<TvList> call, Response<TvList> response) {
                        tvResults.clear();
                        tvResults.addAll(response.body().getTvResults());
                        mTvRecycler.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<TvList> call, Throwable t) {
                        Log.d("===LoadMovies", "onResponse: " + t);
                    }
                });
                return true;
            //////////////////////////
            case R.id.on_air:
                mTvService.getOnAir(Common.API_KEY).enqueue(new Callback<TvList>() {

                    @Override
                    public void onResponse(Call<TvList> call, Response<TvList> response) {
                        tvResults.clear();
                        tvResults.addAll(response.body().getTvResults());
                        mTvRecycler.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<TvList> call, Throwable t) {
                        Log.d("===LoadMovies", "onResponse: " + t);
                    }
                });
                return true;


        }
        return super.onOptionsItemSelected(item);
    }




}
