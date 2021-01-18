package com.example.android.movie.viewmodel;

import android.util.Log;

import com.example.android.movie.model.MovieCast.MovieCast;
import com.example.android.movie.model.MovieReviews.MovieReviews;
import com.example.android.movie.model.MoviesList;
import com.example.android.movie.repository.Repository;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieViewModel extends ViewModel {

    private static final String TAG = "MovieViewModel";

    private final Repository repository;

    public static MutableLiveData<MoviesList> moviesMutableLiveData = new MutableLiveData<>();
    public static MutableLiveData<MovieCast> castMutableLiveData = new MutableLiveData<>();
    public static MutableLiveData<MovieReviews> reviewsMutableLiveData = new MutableLiveData<>();


    @ViewModelInject
    public MovieViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getPopular() {
        repository.getPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> moviesMutableLiveData.setValue(moviesList), throwable -> Log.d(TAG, "getPopular: " + throwable));
    }

    public void getNowPlaying() {
        repository.getNowPlaying()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> moviesMutableLiveData.setValue(moviesList), throwable -> Log.d(TAG, "getNowPlaying: " + throwable));
    }

    public void getTopRated() {
        repository.getTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> moviesMutableLiveData.setValue(moviesList), throwable -> Log.d(TAG, "getTopRated: " + throwable));
    }

    public void getUpcoming() {
        repository.getUpcoming()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> moviesMutableLiveData.setValue(moviesList), throwable -> Log.d(TAG, "getUpcoming: " + throwable));
    }

    public void getCast() {
        repository.getCast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> castMutableLiveData.setValue(moviesList), throwable -> Log.d(TAG, "getCast: " + throwable));
    }

    public void getReviews() {
        repository.getReviews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesList -> reviewsMutableLiveData.setValue(moviesList), throwable -> Log.d(TAG, "getReviews: " + throwable));
    }

}
