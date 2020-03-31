
package com.example.android.movie.Pojo.TvCast;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvCast {

    @SerializedName("cast")
    @Expose
    private ArrayList<Cast> cast ;
    @SerializedName("id")
    @Expose
    private int id;

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
