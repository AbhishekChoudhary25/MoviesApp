package com.example.moviesapp.fragments;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.moviesapp.Adapters.MovieRowRecyclerViewAdapter;
import com.example.moviesapp.Api.RetrofitClient;
import com.example.moviesapp.R;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.example.moviesapp.models.MoviesRowRecyclerViewPojo;
import com.example.moviesapp.models.MovieResultsPojo;
import com.example.moviesapp.util.UserAccessor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesScreenFragment extends Fragment {

    RecyclerView parentRecyclerView;

    RetrofitClient retrofitClient;

    ProgressBar progressBar;

    private static final int REQUEST_CODE_CHILD_ACTIVITY = 1;

    MovieDetailsPojo movieDetailsPojo;


    public MoviesScreenFragment() {
        // Required empty public constructor
        retrofitClient = new RetrofitClient();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_screen, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parentRecyclerView = view.findViewById(R.id.parentRecyclerView);

        progressBar = view.findViewById(R.id.progressBar);

        MovieResultsPojo movieResultsPojo;

        ArrayList<MoviesRowRecyclerViewPojo> moviesRowRecyclerViewPojos = new ArrayList<>();



        ArrayList<MovieDetailsPojo> upcomingMovies = new ArrayList<>();

        ArrayList<MovieDetailsPojo> comedyMovies = new ArrayList<>();

        ArrayList<MovieDetailsPojo> actionMovies = new ArrayList<>();

        ArrayList<MovieDetailsPojo> dramaMovies = new ArrayList<>();

        moviesRowRecyclerViewPojos.add(new MoviesRowRecyclerViewPojo("Upcoming Movies", upcomingMovies));
        moviesRowRecyclerViewPojos.add(new MoviesRowRecyclerViewPojo("Comedy Movies",comedyMovies));
        moviesRowRecyclerViewPojos.add(new MoviesRowRecyclerViewPojo("Action Movies", actionMovies));
        moviesRowRecyclerViewPojos.add(new MoviesRowRecyclerViewPojo("Drama Movies", dramaMovies));


        MovieRowRecyclerViewAdapter movieRowRecyclerViewAdapter = new MovieRowRecyclerViewAdapter(moviesRowRecyclerViewPojos,getContext(),getActivity());
        parentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        parentRecyclerView.setAdapter(movieRowRecyclerViewAdapter);
        movieRowRecyclerViewAdapter.notifyDataSetChanged();


        upComingMovies(upcomingMovies,movieRowRecyclerViewAdapter);
        getComedyMovies(comedyMovies, movieRowRecyclerViewAdapter);
        getActionMovies(actionMovies,movieRowRecyclerViewAdapter);
        getDramaMovies(dramaMovies,movieRowRecyclerViewAdapter);

        System.out.println(UserAccessor.username + "jnjn");




    }

    void getDramaMovies(List<MovieDetailsPojo> dramaMovies,MovieRowRecyclerViewAdapter movieRowRecyclerViewAdapter){
        progressBar.setVisibility(View.VISIBLE);
        Call<MovieResultsPojo> call = retrofitClient.getMoviesApi().getComedyMovies("Drama");

        call.enqueue(new Callback<MovieResultsPojo>() {
            @Override
            public void onResponse(Call<MovieResultsPojo> call, Response<MovieResultsPojo> response) {
                MovieResultsPojo movieResultsPojo = response.body();

                dramaMovies.addAll(movieResultsPojo.movieDetailsPojoList);
                movieRowRecyclerViewAdapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<MovieResultsPojo> call, Throwable t) {

            }
        });
    }

    void getComedyMovies(List<MovieDetailsPojo> comedyMovies,MovieRowRecyclerViewAdapter movieRowRecyclerViewAdapter){
        progressBar.setVisibility(View.VISIBLE);
        Call<MovieResultsPojo> call = retrofitClient.getMoviesApi().getComedyMovies("Comedy");

        call.enqueue(new Callback<MovieResultsPojo>() {
            @Override
            public void onResponse(Call<MovieResultsPojo> call, Response<MovieResultsPojo> response) {
                MovieResultsPojo movieResultsPojo = response.body();

                comedyMovies.addAll(movieResultsPojo.movieDetailsPojoList);
                movieRowRecyclerViewAdapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<MovieResultsPojo> call, Throwable t) {

            }
        });
    }

    void getActionMovies(List<MovieDetailsPojo> actionMovies,MovieRowRecyclerViewAdapter movieRowRecyclerViewAdapter){
        progressBar.setVisibility(View.VISIBLE);
        Call<MovieResultsPojo> call = retrofitClient.getMoviesApi().getComedyMovies("Action");

        call.enqueue(new Callback<MovieResultsPojo>() {
            @Override
            public void onResponse(Call<MovieResultsPojo> call, Response<MovieResultsPojo> response) {
                MovieResultsPojo movieResultsPojo = response.body();

                actionMovies.addAll(movieResultsPojo.movieDetailsPojoList);
                movieRowRecyclerViewAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<MovieResultsPojo> call, Throwable t) {

            }
        });
    }

    void upComingMovies(List<MovieDetailsPojo> movieDetailsPojos, MovieRowRecyclerViewAdapter movieRowRecyclerViewAdapter){

        progressBar.setVisibility(View.VISIBLE);

        Call<MovieResultsPojo> call = retrofitClient.getMoviesApi().getUpcomingMovies();

        call.enqueue(new Callback<MovieResultsPojo>() {
            @Override
            public void onResponse(Call<MovieResultsPojo> call, Response<MovieResultsPojo> response) {
                MovieResultsPojo movieResultsPojo = response.body();

                movieDetailsPojos.addAll(movieResultsPojo.movieDetailsPojoList);
                progressBar.setVisibility(View.GONE);
                movieRowRecyclerViewAdapter.notifyDataSetChanged();
                System.out.println(movieResultsPojo.movieDetailsPojoList.get(0).getPrimaryImage().getUrl());

            }

            @Override
            public void onFailure(Call<MovieResultsPojo> call, Throwable t) {
                System.out.println("Bt");
            }
        });
    }


}