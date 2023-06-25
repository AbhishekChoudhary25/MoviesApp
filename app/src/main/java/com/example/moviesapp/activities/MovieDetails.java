package com.example.moviesapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviesapp.R;
import com.example.moviesapp.dao.DatabaseHelper;
import com.example.moviesapp.entities.FavouriteDetails;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.example.moviesapp.util.MoviesAppUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MovieDetails extends AppCompatActivity {

    TextView titleTv;

    ImageView movieImage;

    TextView movieRunTimeTv;

    TextView movieReleaseYearTv;

    TextView ratingTv;

    Button addToFavBtn;

    MovieDetailsPojo movieDetailsPojo;

    HashMap<String, Boolean> moviesMap;

    DatabaseHelper databaseHelper;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        databaseHelper = DatabaseHelper.getDB(this);

        getWindow().setStatusBarColor(getResources().getColor(R.color.main_colour_theme));

        moviesMap = MoviesAppUtil.moviesMap;

        titleTv = findViewById(R.id.titleTv);

        movieRunTimeTv = findViewById(R.id.movieRunTimeTv);

        movieReleaseYearTv = findViewById(R.id.movieReleaseYearTv);

        ratingTv = findViewById(R.id.ratingTv);

        addToFavBtn = findViewById(R.id.addToFavBtn);

        movieImage = findViewById(R.id.movieImage);

        MovieDetailsPojo movieDetailsPojo = (MovieDetailsPojo) getIntent().getSerializableExtra("movieDetails");

        titleTv.setText(movieDetailsPojo.getOriginalTitleText().getText());


        this.movieDetailsPojo = movieDetailsPojo;
        movieReleaseYearTv.setText(movieDetailsPojo.getReleaseYear().getYear() + "");

        if(movieDetailsPojo.getPrimaryImage() != null){
            Picasso.get().load(movieDetailsPojo.getPrimaryImage().getUrl()).resize(500,800).into(movieImage);
        }
        else{
            Picasso.get().load("https://marketplace.canva.com/EAE_E8rjFrI/1/0/1131w/canva-minimal-mystery-of-forest-movie-poster-ggHwd_WiPcI.jpg").resize(400,600).into(movieImage);

        }

        sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);


        addToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                MoviesAppUtil.moviesMap.put(movieDetailsPojo.getOriginalTitleText().getText(), true);
                resultIntent.putExtra("card", movieDetailsPojo);
                setResult(RESULT_OK, resultIntent);
                addToFavBtn.setText("Added to favourites!");
                addToFavBtn.setClickable(false);



                 int userId = sharedPreferences.getInt("username",0);

                 if(movieDetailsPojo.getPrimaryImage() != null) {
                     databaseHelper.favouriteDetailsDao().addFavourite(new FavouriteDetails(userId, movieDetailsPojo.getOriginalTitleText().getText(), movieDetailsPojo.getPrimaryImage().getUrl()));
                 }
                 else{
                     databaseHelper.favouriteDetailsDao().addFavourite(new FavouriteDetails(userId, movieDetailsPojo.getOriginalTitleText().getText(), "default"));
                 }
            }
        });







    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(movieDetailsPojo.getOriginalTitleText() != null) {
            int userId = sharedPreferences.getInt("username",0);
            List<FavouriteDetails> favouriteDetails = databaseHelper.favouriteDetailsDao().checkFavourite(movieDetailsPojo.getOriginalTitleText().getText());

            ArrayList<FavouriteDetails> arrayList = (ArrayList<FavouriteDetails>) favouriteDetails;

            boolean isPresent = false;

            for(int i = 0; i < arrayList.size(); i++){
                if(arrayList.get(i).getFavouriteName().equals(movieDetailsPojo.getOriginalTitleText().getText()) && arrayList.get(i).getUserId() == userId){
                    isPresent = true;
                    break;
                }

            }

            if (favouriteDetails!= null && isPresent){
                addToFavBtn.setText("Added to favourties!");
                addToFavBtn.setClickable(false);
            }
        }
        else{
            addToFavBtn.setText("Add to favourite");
        }
    }


}