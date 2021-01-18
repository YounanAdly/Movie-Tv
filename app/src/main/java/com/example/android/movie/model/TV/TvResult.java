
package com.example.android.movie.model.TV;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvResult implements Parcelable{

    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("popularity")
    @Expose
    private double popularity;
    @SerializedName("origin_country")
    @Expose
    private List<String> originCountry;
    @SerializedName("vote_count")
    @Expose
    private int voteCount;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("vote_average")
    @Expose
    private double voteAverage;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    protected TvResult(Parcel in) {
        originalName = in.readString();
        name = in.readString();
        popularity = in.readDouble();
        originCountry = in.createStringArrayList();
        voteCount = in.readInt();
        firstAirDate = in.readString();
        backdropPath = in.readString();
        originalLanguage = in.readString();
        id = in.readInt();
        voteAverage = in.readDouble();
        overview = in.readString();
        posterPath = in.readString();
    }

    public static final Creator<TvResult> CREATOR = new Creator<TvResult>() {
        @Override
        public TvResult createFromParcel(Parcel in) {
            return new TvResult(in);
        }

        @Override
        public TvResult[] newArray(int size) {
            return new TvResult[size];
        }
    };

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(originalName);
        dest.writeString(name);
        dest.writeDouble(popularity);
        dest.writeStringList(originCountry);
        dest.writeInt(voteCount);
        dest.writeString(firstAirDate);
        dest.writeString(backdropPath);
        dest.writeString(originalLanguage);
        dest.writeInt(id);
        dest.writeDouble(voteAverage);
        dest.writeString(overview);
        dest.writeString(posterPath);
    }
}
