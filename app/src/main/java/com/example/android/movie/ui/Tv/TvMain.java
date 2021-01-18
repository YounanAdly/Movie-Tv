package com.example.android.movie.ui.Tv;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.movie.adapters.TV.TvListSeriesAdapter;
import com.example.android.movie.R;
import com.example.android.movie.viewmodel.TvViewModel;
import com.example.android.movie.databinding.TvMainBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TvMain extends AppCompatActivity {
    TvViewModel tvViewModel;
    TvMainBinding mBinding;
    TvListSeriesAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.tv_main);


        tvViewModel = new ViewModelProvider(this).get(TvViewModel.class);
        tvViewModel.getTvPopular();

        TvViewModel.tvMutableLiveData.observe(this,tvList -> mAdapter.setList(tvList.getTvResults()));

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (mAdapter == null) {
            mAdapter = new TvListSeriesAdapter(TvMain.this);
            mBinding.tvRecycler.setLayoutManager(new LinearLayoutManager(this));
            mBinding.tvRecycler.setAdapter(mAdapter);
            mBinding.tvRecycler.setItemAnimator(new DefaultItemAnimator());
            mBinding.tvRecycler.setNestedScrollingEnabled(true);
        } else {
            mAdapter.notifyDataSetChanged();
        }
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
                tvViewModel.getAiringToday();
                return true;

            case R.id.on_air:
                tvViewModel.getOnAir();
                return true;

            case R.id.popular_tv:
                tvViewModel.getTvPopular();
                return true;

            case R.id.top_rated_tv:
                tvViewModel.getTvTopRated();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
