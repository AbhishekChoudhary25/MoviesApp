package com.example.moviesapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviesapp.R;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.example.moviesapp.util.MoviesAppUtil;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class MovieDetails extends AppCompatActivity {

    TextView titleTv;

    ImageView movieImage;

    TextView movieRunTimeTv;

    TextView movieReleaseYearTv;

    TextView ratingTv;

    Button addToFavBtn;

    MovieDetailsPojo movieDetailsPojo;

    HashMap<String, Boolean> moviesMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

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


        addToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                MoviesAppUtil.moviesMap.put(movieDetailsPojo.getOriginalTitleText().getText(), true);
                resultIntent.putExtra("card", movieDetailsPojo);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });





    }


    @Override
    protected void onResume() {
        super.onResume();
        if(movieDetailsPojo.getOriginalTitleText() != null && moviesMap.containsKey(movieDetailsPojo.getOriginalTitleText().getText())){
            addToFavBtn.setVisibility(View.GONE);
        }
        else{
            addToFavBtn.setVisibility(View.VISIBLE);
        }
    }


}