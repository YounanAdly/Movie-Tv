package com.example.android.movie.ui.Movie;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.movie.Adapter.Movie.ListMovieAdapter;
import com.example.android.movie.R;
import com.example.android.movie.ViewModel.MovieViewModel;
import com.example.android.movie.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ListMovieAdapter mAdapter;
    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getPopular();

        MovieViewModel.moviesMutableLiveData.observe(this, moviesList -> mAdapter.setList(moviesList.getResults()));

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new ListMovieAdapter(MainActivity.this);
            binding.movieRecycler.setLayoutManager(new LinearLayoutManager(this));
            binding.movieRecycler.setAdapter(mAdapter);
            binding.movieRecycler.setItemAnimator(new DefaultItemAnimator());
            binding.movieRecycler.setNestedScrollingEnabled(true);
        } else {
            mAdapter.notifyDataSetChanged();

        }
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
                movieViewModel.getNowPlaying();
                MovieViewModel.moviesMutableLiveData.observe(this, moviesList -> mAdapter.setList(moviesList.getResults()));
                return true;

            case R.id.popular:
                movieViewModel.getPopular();
                MovieViewModel.moviesMutableLiveData.observe(this, moviesList -> mAdapter.setList(moviesList.getResults()));
                return true;

            case R.id.top_rated:
                movieViewModel.getTopRated();
                MovieViewModel.moviesMutableLiveData.observe(this, moviesList -> mAdapter.setList(moviesList.getResults()));
                return true;

            case R.id.upcoming:
                movieViewModel.getUpcoming();
                MovieViewModel.moviesMutableLiveData.observe(this, moviesList -> mAdapter.setList(moviesList.getResults()));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

