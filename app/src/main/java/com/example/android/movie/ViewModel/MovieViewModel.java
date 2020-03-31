package com.example.android.movie.ViewModel;

import android.util.Log;

import com.example.android.movie.Pojo.MovieCast.MovieCast;
import com.example.android.movie.Pojo.MovieReviews.MovieReviews;
import com.example.android.movie.Pojo.MovieTrailer.MovieTrailer;
import com.example.android.movie.Pojo.MoviesList;
import com.example.android.movie.data.MovieClient;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MovieViewModel extends ViewModel {

    private static final String TAG = "MovieViewModel";

    public static MutableLiveData<MoviesList> moviesMutableLiveData = new MutableLiveData<>();
    public static MutableLiveData<MovieCast> castMutableLiveData = new MutableLiveData<>();
    public static MutableLiveData<MovieReviews> reviewsMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void getPopular(){
        Single<MoviesList> observable = MovieClient.getINSTANCE().getPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(observable.subscribe(o -> moviesMutableLiveData.setValue(o), e -> Log.d(TAG, "getMovies: " + e)));
    }

    public void getNowPlaying(){
        Single<MoviesList> observable = MovieClient.getINSTANCE().getNowPlaying()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(observable.subscribe(o -> moviesMutableLiveData.setValue(o), e -> Log.d(TAG, "getMovies: " + e)));
    }

    public void getTopRated(){
        Single<MoviesList> observable = MovieClient.getINSTANCE().getTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(observable.subscribe(o -> moviesMutableLiveData.setValue(o), e -> Log.d(TAG, "getMovies: " + e)));
    }

    public void getUpcoming(){
        Single<MoviesList> observable = MovieClient.getINSTANCE().getUpcoming()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(observable.subscribe(o -> moviesMutableLiveData.setValue(o), e -> Log.d(TAG, "getMovies: " + e)));
    }

    public void getCast(){
        Single<MovieCast> observable = MovieClient.getINSTANCE().getCast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(observable.subscribe(o-> castMutableLiveData.setValue(o), e -> Log.d(TAG, "getMovies: " + e)));
    }

    public void getReviews(){
        Single<MovieReviews> observable = MovieClient.getINSTANCE().getReviews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(observable.subscribe(o-> reviewsMutableLiveData.setValue(o), e -> Log.d(TAG, "getMovies: " + e)));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

}
