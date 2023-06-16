package com.example.moviesapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.moviesapp.Adapters.FavouriteRecyclerViewAdapter;
import com.example.moviesapp.R;
import com.example.moviesapp.models.MovieDetailsPojo;

import java.util.ArrayList;

public class FravouriteFragment extends Fragment {

    private static final int REQUEST_CODE_CHILD_ACTIVITY = 1;

    RecyclerView favRecyclerView;

    MovieDetailsPojo movieDetailsPojo;

    ArrayList<MovieDetailsPojo> favourites;

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

        favRecyclerView = view.findViewById(R.id.favRecyclerView);

        favouriteRecyclerViewAdapter = new FavouriteRecyclerViewAdapter(favourites, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        favRecyclerView.setLayoutManager(linearLayoutManager);

        favRecyclerView.setAdapter(favouriteRecyclerViewAdapter);

        if(favourites != null){
            favouriteRecyclerViewAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if(favourites != null){
            favouriteRecyclerViewAdapter.notifyItemChanged(favourites.size());
        }


    }
}