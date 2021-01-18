
package com.example.android.movie.model.MovieCast;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieCast {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("cast")
    @Expose
    private ArrayList<Cast> cast ;
    @SerializedName("crew")
    @Expose
    private ArrayList<Crew> crew ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }

    public ArrayList<Crew> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<Crew> crew) {
        this.crew = crew;
    }

}
