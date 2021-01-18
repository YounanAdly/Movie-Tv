package com.example.android.movie.ui.Tv;

import android.os.Bundle;

import com.example.android.movie.adapters.TV.TvCastAdapter;
import com.example.android.movie.adapters.TV.TvListSeriesAdapter;
import com.example.android.movie.adapters.TV.TvReveiwsAdapter;
import com.example.android.movie.model.TV.TvResult;
import com.example.android.movie.R;
import com.example.android.movie.viewmodel.TvViewModel;
import com.example.android.movie.repository.Repository;
import com.example.android.movie.databinding.TvDetailsBinding;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TvDetails extends AppCompatActivity {

    public static TvResult selectedMovie;
    TvDetailsBinding mBinding;
    TvCastAdapter mAdapterCast;
    TvReveiwsAdapter mAdapterReviews;
    TvViewModel tvViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.tv_details);

        selectedMovie = getIntent().getParcelableExtra(TvListSeriesAdapter.SELECTED_TV);

        tvViewModel = new ViewModelProvider(this).get(TvViewModel.class);
        tvViewModel.getTvPopular();
        tvViewModel.getTvCast();
        tvViewModel.getTvReviews();


        TvViewModel.tvMutableLiveData.observe(this,moviesList ->  {
            mBinding.nameMovie.setText(selectedMovie.getName());
            mBinding.rating.setText(String.valueOf(selectedMovie.getVoteAverage()));
            mBinding.dateDet.setText(selectedMovie.getFirstAirDate());
            mBinding.overview.setText(selectedMovie.getOverview());

            Picasso.with(TvDetails.this)
                    .load(Repository.IMAGE_LOAD + selectedMovie.getPosterPath())
                    .into(mBinding.movieImage);
        });

        tvViewModel.getTvCast();
        TvViewModel.tvCastMutableLiveData.observe(this, tvCast -> mAdapterCast.setList(tvCast.getCast()));

        tvViewModel.getTvReviews();
        TvViewModel.tvReviewsMutableLiveData.observe(this,tvReviews -> mAdapterReviews.setList(tvReviews.getReviews()));

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (mAdapterCast == null) {
            mAdapterCast = new TvCastAdapter(TvDetails.this);
            mBinding.castRecycler.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
            mBinding.castRecycler.setAdapter(mAdapterCast);
            mBinding.castRecycler.setItemAnimator(new DefaultItemAnimator());
            mBinding.castRecycler.setNestedScrollingEnabled(true);
        } else {
            mAdapterCast.notifyDataSetChanged();
        }

        if (mAdapterReviews == null) {
            mAdapterReviews = new TvReveiwsAdapter(TvDetails.this);
            mBinding.reviewsRecycler.setLayoutManager(new LinearLayoutManager(this));
            mBinding.reviewsRecycler.setAdapter(mAdapterReviews);
            mBinding.reviewsRecycler.setItemAnimator(new DefaultItemAnimator());
            mBinding.reviewsRecycler.setNestedScrollingEnabled(true);
        } else {
            mAdapterReviews.notifyDataSetChanged();
        }
    }

}
