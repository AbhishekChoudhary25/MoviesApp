package com.example.moviesapp.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.moviesapp.Adapters.FavouriteRecyclerViewAdapter;
import com.example.moviesapp.R;
import com.example.moviesapp.activities.HomeScreen;
import com.example.moviesapp.dao.DatabaseHelper;
import com.example.moviesapp.entities.FavouriteDetails;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FravouriteFragment extends Fragment {

    private static final int REQUEST_CODE_CHILD_ACTIVITY = 1;

    RecyclerView favRecyclerView;


    List<FavouriteDetails> favouriteDetails;

    View view;

    ConnectivityManager connectivityManager;

    FavouriteRecyclerViewAdapter favouriteRecyclerViewAdapter;

    DatabaseHelper databaseHelper;

    SharedPreferences sharedPreferences;

    Button yesAlertBtn;



    public void receiveDataFromParent() {
        // Process the received data in the child Fragment
        sharedPreferences = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("username",0);
        favouriteDetails = databaseHelper.favouriteDetailsDao().getAllFavourites(userId);
            favouriteRecyclerViewAdapter = new FavouriteRecyclerViewAdapter((ArrayList<FavouriteDetails>) favouriteDetails, getContext());
            favRecyclerView.setAdapter(favouriteRecyclerViewAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            favRecyclerView.setLayoutManager(linearLayoutManager);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fravourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

        loadFragment();
    }

    public void  loadFragment(){
        databaseHelper = DatabaseHelper.getDB(getContext());

        favRecyclerView = view.findViewById(R.id.favRecyclerView);

        sharedPreferences = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        int userId = sharedPreferences.getInt("username",0);
        favouriteDetails = databaseHelper.favouriteDetailsDao().getAllFavourites(userId);
        favouriteRecyclerViewAdapter = new FavouriteRecyclerViewAdapter((ArrayList<FavouriteDetails>) favouriteDetails, getContext());
        favRecyclerView.setAdapter(favouriteRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        favRecyclerView.setLayoutManager(linearLayoutManager);
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        checkConnection();
    }

    public void checkConnection(){
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        Dialog dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.error_layout);

        dialog.setCancelable(false);

        yesAlertBtn = dialog.findViewById(R.id.alertYesBtn);

        yesAlertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment();
                dialog.dismiss();

            }
        });

        if (networkInfo == null) {
            dialog.show();
        }

    }
}