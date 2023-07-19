package com.example.moviesapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviesapp.R;
import com.example.moviesapp.activities.HomeScreen;
import com.example.moviesapp.dao.DatabaseHelper;
import com.example.moviesapp.entities.FavouriteDetails;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class MovieDetailsFragment extends Fragment {

    TextView titleTv;

    ImageView movieImage;

    TextView movieRunTimeTv;

    TextView movieReleaseYearTv;

    TextView ratingTv;

    Button addToFavBtn;

    Button deleteFavBtn;

    MovieDetailsPojo movieDetailsPojo;

    DatabaseHelper databaseHelper;

    SharedPreferences sharedPreferences;

    ImageView backArrow;

    boolean isPresent = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseHelper = DatabaseHelper.getDB(getContext());
        if(getActivity() != null){
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.main_colour_theme));
        }

        titleTv = view.findViewById(R.id.titleTv);

        movieRunTimeTv = view.findViewById(R.id.movieRunTimeTv);

        movieReleaseYearTv = view.findViewById(R.id.movieReleaseYearTv);

        ratingTv = view.findViewById(R.id.ratingTv);

        addToFavBtn = view.findViewById(R.id.addToFavBtn);

        deleteFavBtn = view.findViewById(R.id.deleteFavbtn);

        movieImage = view.findViewById(R.id.movieImage);

        backArrow = view.findViewById(R.id.backArrow);

        Bundle details =  getArguments();

        if(details != null){
            this.movieDetailsPojo = details.getSerializable("movie_det",MovieDetailsPojo.class);
        }

        titleTv.setText(movieDetailsPojo.getOriginalTitleText().getText());

        if(movieDetailsPojo.getPrimaryImage() != null){
            Picasso.get().load(movieDetailsPojo.getPrimaryImage().getUrl()).resize(500,800).into(movieImage);
        }
        else{
            Picasso.get().load("https://marketplace.canva.com/EAE_E8rjFrI/1/0/1131w/canva-minimal-mystery-of-forest-movie-poster-ggHwd_WiPcI.jpg").resize(400,600).into(movieImage);

        }

        sharedPreferences = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        int userId = sharedPreferences.getInt("username",0);

        addToFavBtn.setOnClickListener(v -> {
            if(movieDetailsPojo.getPrimaryImage() != null) {
                databaseHelper.favouriteDetailsDao().addFavourite(new FavouriteDetails(userId, movieDetailsPojo.getOriginalTitleText().getText(), movieDetailsPojo.getPrimaryImage().getUrl()));
            }
            else{
                databaseHelper.favouriteDetailsDao().addFavourite(new FavouriteDetails(userId, movieDetailsPojo.getOriginalTitleText().getText(), "default"));
            }

            HomeScreen homeScreen = (HomeScreen)getActivity();

            homeScreen.changeInFavourites();

            addToFavBtn.setVisibility(View.GONE);
            deleteFavBtn.setVisibility(View.VISIBLE);
        });

        deleteFavBtn.setOnClickListener(v -> {
            databaseHelper.favouriteDetailsDao().deleteFavouriteByUserAndFavName(userId, movieDetailsPojo.getOriginalTitleText().getText());
            deleteFavBtn.setVisibility(View.GONE);
            addToFavBtn.setVisibility(View.VISIBLE);

            HomeScreen homeScreen = (HomeScreen)getActivity();

            homeScreen.changeInFavourites();
        });

        if(getFragmentManager() != null){
            backArrow.setOnClickListener(v -> getFragmentManager().popBackStack());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (movieDetailsPojo.getOriginalTitleText() != null) {
            int userId = sharedPreferences.getInt("username", 0);
            List<FavouriteDetails> favouriteDetails = databaseHelper.favouriteDetailsDao().checkFavourite(movieDetailsPojo.getOriginalTitleText().getText());

            ArrayList<FavouriteDetails> arrayList = (ArrayList<FavouriteDetails>) favouriteDetails;

            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).getFavouriteName().equals(movieDetailsPojo.getOriginalTitleText().getText()) && arrayList.get(i).getUserId() == userId) {
                    isPresent = true;
                    break;
                }

            }

            if (isPresent) {
                addToFavBtn.setVisibility(View.GONE);
            } else {
                deleteFavBtn.setVisibility(View.GONE);
            }
        }

    }

}