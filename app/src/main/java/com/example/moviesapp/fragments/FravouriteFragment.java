package com.example.moviesapp.fragments;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.moviesapp.Adapters.FavouriteRecyclerViewAdapter;
import com.example.moviesapp.R;
import com.example.moviesapp.dao.DatabaseHelper;
import com.example.moviesapp.entities.FavouriteDetails;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FravouriteFragment extends Fragment {

    private static final int REQUEST_CODE_CHILD_ACTIVITY = 1;

    RecyclerView favRecyclerView;

    MovieDetailsPojo movieDetailsPojo;

    ArrayList<MovieDetailsPojo> favourites;

    List<FavouriteDetails> favouriteDetails;

    View view;

    ConnectivityManager connectivityManager;

    FavouriteRecyclerViewAdapter favouriteRecyclerViewAdapter;

    public void updateData(ArrayList<MovieDetailsPojo> favourites) {
        this.favourites = favourites;
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
        DatabaseHelper databaseHelper = DatabaseHelper.getDB(getContext());

        favRecyclerView = view.findViewById(R.id.favRecyclerView);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        int userId = sharedPreferences.getInt("username",0);

        favouriteDetails = databaseHelper.favouriteDetailsDao().getAllFavourites(userId);

        favouriteRecyclerViewAdapter = new FavouriteRecyclerViewAdapter((ArrayList<FavouriteDetails>) favouriteDetails, getContext());
        favRecyclerView.setAdapter(favouriteRecyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        favRecyclerView.setLayoutManager(linearLayoutManager);



        if(favourites != null){
            favouriteRecyclerViewAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if(favouriteDetails != null){
            favouriteRecyclerViewAdapter.notifyDataSetChanged();
        }
        favouriteRecyclerViewAdapter.notifyDataSetChanged();

        ConnectivityManager cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();

        if ((nInfo != null) && nInfo.isAvailable() && nInfo.isConnected()){

        }
        else{
            Snackbar snackbar = Snackbar.make(view, "No Internet Connection", Snackbar.LENGTH_LONG);
            View v = snackbar.getView();
            snackbar.setBackgroundTint(Color.BLACK);
            TextView textView = new TextView(v.getContext());
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }



    }
}