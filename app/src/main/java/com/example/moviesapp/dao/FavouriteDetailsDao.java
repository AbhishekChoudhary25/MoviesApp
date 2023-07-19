package com.example.moviesapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moviesapp.entities.FavouriteDetails;

import java.util.List;

@Dao
public interface FavouriteDetailsDao {

    @Insert
    void addFavourite(FavouriteDetails favouriteDetails);

    @Query("SELECT * FROM favourites WHERE user_id = :userId")
    List<FavouriteDetails> getAllFavourites(int userId);

    @Delete
    void deleteFavourite(FavouriteDetails favouriteDetails);

    @Query("DELETE FROM favourites WHERE user_id = :userId and favourite_name = :favouriteName")
    void deleteFavouriteByUserAndFavName(int userId,String favouriteName);

    @Query("SELECT * FROM favourites WHERE favourite_name = :favouriteName")
    List<FavouriteDetails> checkFavourite(String favouriteName);


}
