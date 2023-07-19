package com.example.moviesapp.fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moviesapp.Adapters.MovieRowRecyclerViewAdapter;
import com.example.moviesapp.Api.RetrofitClient;
import com.example.moviesapp.R;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.example.moviesapp.models.MoviesRowRecyclerViewPojo;
import com.example.moviesapp.models.MovieResultsPojo;
import com.example.moviesapp.util.NetworkCallBack;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("deprecation")
public class MoviesScreenFragment extends Fragment {

    RecyclerView parentRecyclerView;

    RetrofitClient retrofitClient;

    ProgressBar progressBar;

    NetworkCallBack networkCallback;


    Button yesAlertBtn;


    public MoviesScreenFragment() {
        // Required empty public constructor
        retrofitClient = new RetrofitClient();
    }

    View view;


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

        this.view = view;
        loadFragment();

        if(getActivity() != null){
            networkCallback = new NetworkCallBack(getActivity()) {
                @Override
                public void onSuccess() {
                    Toast.makeText(getContext(), "Back Online!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(String message) {
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                }
            };

            networkCallback.register(networkCallback);
        }



    }

    public void loadFragment(){

        checkConnection();

        parentRecyclerView = view.findViewById(R.id.parentRecyclerView);

        progressBar = view.findViewById(R.id.progressBar);

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
//        movieRowRecyclerViewAdapter.notifyDataSetChanged();

        try{
            upComingMovies(upcomingMovies,movieRowRecyclerViewAdapter);
            getComedyMovies(comedyMovies, movieRowRecyclerViewAdapter);
            getActionMovies(actionMovies,movieRowRecyclerViewAdapter);
            getDramaMovies(dramaMovies,movieRowRecyclerViewAdapter);
        }
        catch (Exception e){
            e.printStackTrace();

        }
    }

    void getDramaMovies(List<MovieDetailsPojo> dramaMovies,MovieRowRecyclerViewAdapter movieRowRecyclerViewAdapter){
        progressBar.setVisibility(View.VISIBLE);
        Call<MovieResultsPojo> call = retrofitClient.getMoviesApi().getComedyMovies("Drama");

        call.enqueue(new Callback<MovieResultsPojo>() {
            @Override
            public void onResponse(@NonNull Call<MovieResultsPojo> call, @NonNull Response<MovieResultsPojo> response) {
                MovieResultsPojo movieResultsPojo = response.body();

                if(movieResultsPojo != null){
                    dramaMovies.addAll(movieResultsPojo.movieDetailsPojoList);
//                    movieRowRecyclerViewAdapter.notifyDataSetChanged();
                    movieRowRecyclerViewAdapter.notifyItemRangeChanged(0,dramaMovies.size() - 1);
                }


                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(@NonNull Call<MovieResultsPojo> call, @NonNull Throwable t) {

            }
        });
    }

    void getComedyMovies(List<MovieDetailsPojo> comedyMovies,MovieRowRecyclerViewAdapter movieRowRecyclerViewAdapter){
        progressBar.setVisibility(View.VISIBLE);
        Call<MovieResultsPojo> call = retrofitClient.getMoviesApi().getComedyMovies("Comedy");

        call.enqueue(new Callback<MovieResultsPojo>() {
            @Override
            public void onResponse(@NonNull Call<MovieResultsPojo> call, @NonNull Response<MovieResultsPojo> response) {
                MovieResultsPojo movieResultsPojo = response.body();

                if(movieResultsPojo != null){
                    comedyMovies.addAll(movieResultsPojo.movieDetailsPojoList);
//                    movieRowRecyclerViewAdapter.notifyDataSetChanged();
                    movieRowRecyclerViewAdapter.notifyItemRangeChanged(0, comedyMovies.size() - 1);
                }


                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(@NonNull Call<MovieResultsPojo> call, @NonNull Throwable t) {

            }
        });
    }

    void getActionMovies(List<MovieDetailsPojo> actionMovies,MovieRowRecyclerViewAdapter movieRowRecyclerViewAdapter){
        progressBar.setVisibility(View.VISIBLE);
        Call<MovieResultsPojo> call = retrofitClient.getMoviesApi().getComedyMovies("Action");

        call.enqueue(new Callback<MovieResultsPojo>() {
            @Override
            public void onResponse(@NonNull Call<MovieResultsPojo> call, @NonNull Response<MovieResultsPojo> response) {
                MovieResultsPojo movieResultsPojo = response.body();

                if(movieResultsPojo != null){
                    actionMovies.addAll(movieResultsPojo.movieDetailsPojoList);
//                    movieRowRecyclerViewAdapter.notifyDataSetChanged();
                    movieRowRecyclerViewAdapter.notifyItemRangeChanged(0, actionMovies.size());
                }

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(@NonNull Call<MovieResultsPojo> call, @NonNull Throwable t) {

            }
        });
    }

    void upComingMovies(List<MovieDetailsPojo> movieDetailsPojos, MovieRowRecyclerViewAdapter movieRowRecyclerViewAdapter){

        progressBar.setVisibility(View.VISIBLE);

        Call<MovieResultsPojo> call = retrofitClient.getMoviesApi().getUpcomingMovies();

        call.enqueue(new Callback<MovieResultsPojo>() {
            @Override
            public void onResponse(@NonNull Call<MovieResultsPojo> call, @NonNull Response<MovieResultsPojo> response) {
                MovieResultsPojo movieResultsPojo = response.body();

                if(movieResultsPojo != null){
                    movieDetailsPojos.addAll(movieResultsPojo.movieDetailsPojoList);
//                    movieRowRecyclerViewAdapter.notifyDataSetChanged();
                    movieRowRecyclerViewAdapter.notifyItemRangeChanged(0,movieDetailsPojos.size() - 1);

                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(@NonNull Call<MovieResultsPojo> call, @NonNull Throwable t) {
                System.out.println("Bt");
            }
        });
    }


     public void checkConnection(){
         ConnectivityManager cm;
         NetworkInfo networkInfo;

         if(getContext() != null){
             cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
             networkInfo = cm.getActiveNetworkInfo();
             Dialog dialog = new Dialog(getContext());

             dialog.setContentView(R.layout.error_layout);

             dialog.setCancelable(false);

             yesAlertBtn = dialog.findViewById(R.id.alertYesBtn);

             yesAlertBtn.setOnClickListener(v -> {
                 loadFragment();
                 dialog.dismiss();

             });

             if (networkInfo == null && !networkInfo.isConnectedOrConnecting()) {
                 dialog.show();
             }
         }
     }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        networkCallback.unRegister(networkCallback);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        checkConnection();
    }
}