
package com.example.android.movie.Model.TvTrailer;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvTrailer {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("results")
    @Expose
    private ArrayList<Result> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

}
