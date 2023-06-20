package com.example.moviesapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.dao.DatabaseHelper;
import com.example.moviesapp.entities.FavouriteDetails;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.example.moviesapp.util.MoviesAppUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class FavouriteRecyclerViewAdapter extends RecyclerView.Adapter<FavouriteRecyclerViewAdapter.ViewHolder> {

    ArrayList<MovieDetailsPojo> movieDetailsPojo;

    ArrayList<FavouriteDetails> favouriteDetails;

    Context context;

//    public FavouriteRecyclerViewAdapter(ArrayList<MovieDetailsPojo> MovieDetailsPojo, Context context){
//        this.movieDetailsPojo = MovieDetailsPojo;
//        this.context = context;
//    }

    public FavouriteRecyclerViewAdapter(ArrayList<FavouriteDetails> favouriteDetails, Context context){
        this.favouriteDetails = favouriteDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public FavouriteRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favourite_card,null,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FavouriteRecyclerViewAdapter.ViewHolder holder,int position) {

//        if(movieDetailsPojo.get(position).getPrimaryImage() != null){
//            Picasso.get().load(movieDetailsPojo.get(position).getPrimaryImage().getUrl()).resize(200,300).into(holder.favImage);
//        }
//        else{
//            Picasso.get().load("https://marketplace.canva.com/EAE_E8rjFrI/1/0/1131w/canva-minimal-mystery-of-forest-movie-poster-ggHwd_WiPcI.jpg").resize(200,300).into(holder.favImage);
//        }
//        holder.favTextView.setText(movieDetailsPojo.get(position).getOriginalTitleText().getText());
//
//        holder.deleteView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HashMap<String, Boolean> map = MoviesAppUtil.moviesMap;
//                map.remove(movieDetailsPojo.get(position).getOriginalTitleText().getText());
//
//                movieDetailsPojo.remove(position);
//                notifyDataSetChanged();
//            }
//        });

        if(favouriteDetails.get(position).getFavouriteImageUrl() != null){
            Picasso.get().load(favouriteDetails.get(position).getFavouriteImageUrl()).resize(200,300).into(holder.favImage);
        }
        else{
            Picasso.get().load("https://marketplace.canva.com/EAE_E8rjFrI/1/0/1131w/canva-minimal-mystery-of-forest-movie-poster-ggHwd_WiPcI.jpg").resize(200,300).into(holder.favImage);
        }
        holder.favTextView.setText(favouriteDetails.get(position).getFavouriteName());

        holder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HashMap<String, Boolean> map = MoviesAppUtil.moviesMap;
//                map.remove(movieDetailsPojo.get(position).getOriginalTitleText().getText());

                DatabaseHelper databaseHelper = DatabaseHelper.getDB(context);
                databaseHelper.favouriteDetailsDao().deleteFavourite(favouriteDetails.get(position));
                favouriteDetails.remove(position);

                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(favouriteDetails == null){
            return 0;
        }
        return favouriteDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView favImage;

        TextView favTextView;

        ImageView deleteView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            favTextView = itemView.findViewById(R.id.favTextView);
            favImage = itemView.findViewById(R.id.favImage);
            deleteView = itemView.findViewById(R.id.deleteView);

        }
    }
}
