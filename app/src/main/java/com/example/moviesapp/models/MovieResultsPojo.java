package com.example.moviesapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieResultsPojo {
    public String page;

    @SerializedName("results")
    public List<MovieDetailsPojo> movieDetailsPojoList = new ArrayList<>();

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<MovieDetailsPojo> getMovieDetailsPojoNewList() {
        return movieDetailsPojoList;
    }

    public void setMovieDetailsPojoNewList(List<MovieDetailsPojo> movieDetailsPojoList) {
        this.movieDetailsPojoList = movieDetailsPojoList;
    }
}
