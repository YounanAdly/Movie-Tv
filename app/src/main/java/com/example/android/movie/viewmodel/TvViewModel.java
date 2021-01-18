package com.example.android.movie.viewmodel;

import android.util.Log;

import com.example.android.movie.model.TV.TvList;
import com.example.android.movie.model.TvCast.TvCast;
import com.example.android.movie.model.TvReviews.TvReviews;
import com.example.android.movie.repository.Repository;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TvViewModel extends ViewModel {

    private static final String TAG = "TvViewModel";
    private final Repository repository;

    public static MutableLiveData<TvList> tvMutableLiveData = new MutableLiveData<>();
    public static MutableLiveData<TvCast> tvCastMutableLiveData = new MutableLiveData<>();
    public static MutableLiveData<TvReviews> tvReviewsMutableLiveData = new MutableLiveData<>();

    @ViewModelInject
    public TvViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getTvPopular() {
        repository.getTvPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvList -> tvMutableLiveData.setValue(tvList), throwable -> Log.d(TAG, "getTvPopular: " + throwable.getMessage()));
    }

    public void getAiringToday() {
        repository.getAiringToday()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvList -> tvMutableLiveData.setValue(tvList), throwable -> Log.d(TAG, "getAiringToday: " + throwable));
    }

    public void getTvTopRated() {
        repository.getTvTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvList -> tvMutableLiveData.setValue(tvList), throwable -> Log.d(TAG, "getTvTopRated: " + throwable));
    }

    public void getOnAir() {
        repository.getOnAir()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvList -> tvMutableLiveData.setValue(tvList), throwable -> Log.d(TAG, "getOnAir: " + throwable));
    }

    public void getTvCast() {
        repository.getTvCast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvCast -> tvCastMutableLiveData.setValue(tvCast), throwable -> Log.d(TAG, "getTvCast: " + throwable));
    }

    public void getTvReviews() {
        repository.getTvReviews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reviews -> tvReviewsMutableLiveData.setValue(reviews), throwable -> Log.d(TAG, "getTvReviews: " + throwable));
    }

}
