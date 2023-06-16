package com.example.moviesapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.activities.MovieDetails;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieCardRecyclerViewAdapter extends RecyclerView.Adapter<MovieCardRecyclerViewAdapter.ViewHolder> {

    List<MovieDetailsPojo> movieDetailsPojo;
    Context context;

    Activity activity;

    private static final int REQUEST_CODE_CHILD_ACTIVITY = 1;

    public MovieCardRecyclerViewAdapter(List<MovieDetailsPojo> movieDetailsPojo, Context context,Activity activity){
        this.context = context;
        this.movieDetailsPojo = movieDetailsPojo;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MovieCardRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.movies_card_recyclerview,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCardRecyclerViewAdapter.ViewHolder holder, int position) {
        ImageView titleImage = holder.movieImage;

        if(movieDetailsPojo.get(position).getPrimaryImage() != null){
            Picasso.get().load(movieDetailsPojo.get(position).getPrimaryImage().getUrl()).resize(400,600).into(titleImage);
        }
        else{
            Picasso.get().load("https://marketplace.canva.com/EAE_E8rjFrI/1/0/1131w/canva-minimal-mystery-of-forest-movie-poster-ggHwd_WiPcI.jpg").resize(400,600).into(titleImage);
        }

        titleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MovieDetails.class);
                intent.putExtra("movieDetails",movieDetailsPojo.get(position));
                activity.startActivityForResult(intent,REQUEST_CODE_CHILD_ACTIVITY);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movieDetailsPojo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView movieImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.movieImage);

        }
    }

}
