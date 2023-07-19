package com.example.moviesapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.models.MoviesRowRecyclerViewPojo;

import java.util.List;

public class MovieRowRecyclerViewAdapter extends RecyclerView.Adapter<MovieRowRecyclerViewAdapter.ViewHolder> {

    List<MoviesRowRecyclerViewPojo> moviesRowRecyclerViewPojo;
    Context context;

    Activity activity;

    public MovieRowRecyclerViewAdapter(List<MoviesRowRecyclerViewPojo> moviesRowRecyclerViewPojo, Context context,Activity activity){
        this.moviesRowRecyclerViewPojo = moviesRowRecyclerViewPojo;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MovieRowRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movies_row_recyclerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRowRecyclerViewAdapter.ViewHolder holder, int position) {
        if(moviesRowRecyclerViewPojo.get(position).childModels.size() > 0){
            holder.childTextView.setVisibility(View.VISIBLE);
        }
        else{
            holder.childTextView.setVisibility(View.GONE);
        }
        holder.childTextView.setText(moviesRowRecyclerViewPojo.get(position).title);
        MovieCardRecyclerViewAdapter movieCardRecyclerViewAdapter = new MovieCardRecyclerViewAdapter(moviesRowRecyclerViewPojo.get(position).childModels,context,activity);
        holder.movieCardRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        holder.movieCardRecyclerView.setAdapter(movieCardRecyclerViewAdapter);
    }

    @Override
    public int getItemCount() {
        return moviesRowRecyclerViewPojo.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView movieCardRecyclerView;
        TextView childTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieCardRecyclerView = itemView.findViewById(R.id.childRecyclerView);
            childTextView = itemView.findViewById(R.id.childTextView);




        }
    }

}
