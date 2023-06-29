package com.example.moviesapp.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviesapp.entities.FavouriteDetails;
import com.example.moviesapp.entities.UserDetails;

@Database(entities = {UserDetails.class, FavouriteDetails.class}, exportSchema = false,version = 3)
public abstract class DatabaseHelper extends RoomDatabase {
    private static final String DB_NAME = "expensedb";
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getDB(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, DatabaseHelper.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract UserDetailsDAO  userDetailsDAO();

    public abstract FavouriteDetailsDao favouriteDetailsDao();


}
