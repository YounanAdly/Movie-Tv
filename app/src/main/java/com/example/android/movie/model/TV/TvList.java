
package com.example.android.movie.model.TV;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvList implements Parcelable{

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("results")
    @Expose
    private ArrayList<TvResult> tvResults ;

    protected TvList(Parcel in) {
        page = in.readInt();
        totalResults = in.readInt();
        totalPages = in.readInt();
    }

    public static final Creator<TvList> CREATOR = new Creator<TvList>() {
        @Override
        public TvList createFromParcel(Parcel in) {
            return new TvList(in);
        }

        @Override
        public TvList[] newArray(int size) {
            return new TvList[size];
        }
    };

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<TvResult> getTvResults() {
        return tvResults;
    }

    public void setTvResults(ArrayList<TvResult> tvResults) {
        this.tvResults = tvResults;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(totalResults);
        dest.writeInt(totalPages);
    }
}
