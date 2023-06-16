package com.example.moviesapp.models;

import java.util.List;

public class MoviesRowRecyclerViewPojo {
    public String title;

    public List<MovieDetailsPojo> childModels;


    public MoviesRowRecyclerViewPojo(String title, List<MovieDetailsPojo> childModels) {
        this.title = title;
        this.childModels = childModels;
    }
}
