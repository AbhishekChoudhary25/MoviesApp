package com.example.moviesapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.activities.HomeScreen;
import com.example.moviesapp.fragments.MovieDetailsFragment;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieCardRecyclerViewAdapter extends RecyclerView.Adapter<MovieCardRecyclerViewAdapter.ViewHolder> {

    List<MovieDetailsPojo> movieDetailsPojo;
    Context context;

    Activity activity;

    public MovieCardRecyclerViewAdapter(List<MovieDetailsPojo> movieDetailsPojo, Context context,Activity activity){
        this.context = context;
        this.movieDetailsPojo = movieDetailsPojo;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MovieCardRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.movies_card_recyclerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCardRecyclerViewAdapter.ViewHolder holder, int position) {
        ImageView titleImage = holder.movieImage;

        if(movieDetailsPojo.get(position).getPrimaryImage() != null){
            Picasso.get().load(movieDetailsPojo.get(position).getPrimaryImage().getUrl()).placeholder(R.drawable.tv).resize(400,600).into(titleImage);
        }
        else{
            Picasso.get().load("https://marketplace.canva.com/EAE_E8rjFrI/1/0/1131w/canva-minimal-mystery-of-forest-movie-poster-ggHwd_WiPcI.jpg").placeholder(R.drawable.tv).resize(400,600).into(titleImage);
        }

        titleImage.setOnClickListener(v -> {
            MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
            HomeScreen homeScreen = (HomeScreen)activity;
            homeScreen.changeFragment(movieDetailsFragment,movieDetailsPojo.get(position));

        });


    }

    @Override
    public int getItemCount() {
        return movieDetailsPojo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView movieImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.movieImage);

        }
    }

}
