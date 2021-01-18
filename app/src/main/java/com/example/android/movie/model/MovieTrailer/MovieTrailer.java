
package com.example.android.movie.model.MovieTrailer;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieTrailer  implements Parcelable{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("results")
    @Expose
    private ArrayList<Key> results;


    protected MovieTrailer(Parcel in) {
        id = in.readInt();
        results = in.createTypedArrayList(Key.CREATOR);
    }

    public static final Creator<MovieTrailer> CREATOR = new Creator<MovieTrailer>() {
        @Override
        public MovieTrailer createFromParcel(Parcel in) {
            return new MovieTrailer(in);
        }

        @Override
        public MovieTrailer[] newArray(int size) {
            return new MovieTrailer[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Key> getResults() {
        return results;
    }

    public void setResults(ArrayList<Key> results) {
        this.results = results;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeTypedList(results);
    }
}
