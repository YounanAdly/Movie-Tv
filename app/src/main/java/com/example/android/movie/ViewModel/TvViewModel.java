package com.example.android.movie.ViewModel;

import android.util.Log;

import com.example.android.movie.Pojo.TV.TvList;
import com.example.android.movie.Pojo.TvCast.TvCast;
import com.example.android.movie.Pojo.TvReviews.TvReviews;
import com.example.android.movie.data.MovieClient;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TvViewModel extends ViewModel {

    private static final String TAG = "TvViewModel";

    public static MutableLiveData<TvList> tvMutableLiveData = new MutableLiveData<>();
    public static MutableLiveData<TvCast> tvCastMutableLiveData = new MutableLiveData<>();
    public static MutableLiveData<TvReviews> tvReviewsMutableLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public void getTvPopular(){
        Single<TvList> tvListSingle = MovieClient.getINSTANCE().getTvPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(tvListSingle.subscribe(o -> tvMutableLiveData.setValue(o), e -> Log.d(TAG, "getMovies: " + e)));
    }

    public void getAiringToday(){
        Single<TvList> tvListSingle = MovieClient.getINSTANCE().getAiringToday()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(tvListSingle.subscribe(o -> tvMutableLiveData.setValue(o), e -> Log.d(TAG, "getMovies: " + e)));
    }

    public void getTvTopRated(){
        Single<TvList> tvListSingle = MovieClient.getINSTANCE().getTvTopRated()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(tvListSingle.subscribe(o -> tvMutableLiveData.setValue(o), e -> Log.d(TAG, "getMovies: " + e)));
    }

    public void getOnAir(){
        Single<TvList> tvListSingle = MovieClient.getINSTANCE().getOnAir()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(tvListSingle.subscribe(o -> tvMutableLiveData.setValue(o), e -> Log.d(TAG, "getMovies: " + e)));
    }

    public void getTvCast(){
        Single<TvCast> castSingle = MovieClient.getINSTANCE().getTvCast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(castSingle.subscribe(o -> tvCastMutableLiveData.setValue(o), e -> Log.d(TAG, "getMovies: " + e)));

    }

    public void getTvReviews(){
        Single<TvReviews> reviewsSingle = MovieClient.getINSTANCE().getTvReviews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(reviewsSingle.subscribe(o -> tvReviewsMutableLiveData.setValue(o), e -> Log.d(TAG, "getMovies: " + e)));

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
