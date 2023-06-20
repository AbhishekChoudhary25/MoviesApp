package com.example.moviesapp.Api;

import com.example.moviesapp.models.MovieResultsPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {

    String BASE_URL = "https://moviesdatabase.p.rapidapi.com/";
    String ApiKey = "a09477845emshb119a05df232c8dp19788bjsn314c2158426a";

    @GET("titles/x/upcoming")
    Call<MovieResultsPojo> getUpcomingMovies();


    @GET("titles")
    Call<MovieResultsPojo> getComedyMovies(@Query("genre") String genre);


}
