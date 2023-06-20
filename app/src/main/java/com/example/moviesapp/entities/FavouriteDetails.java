package com.example.moviesapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourites")
public class FavouriteDetails {

    @PrimaryKey(autoGenerate = true)
    private int favouriteId;

    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "favourite_name")
    private String favouriteName;

    @ColumnInfo(name="favourite_image_url")
    private String favouriteImageUrl;

    @Ignore
    public FavouriteDetails(int favouriteId, int userId, String favouriteName, String favouriteImageUrl) {
        this.favouriteId = favouriteId;
        this.userId = userId;
        this.favouriteName = favouriteName;
        this.favouriteImageUrl = favouriteImageUrl;
    }

    public int getFavouriteId() {
        return favouriteId;
    }

    public FavouriteDetails(int userId, String favouriteName, String favouriteImageUrl) {
        this.userId = userId;
        this.favouriteName = favouriteName;
        this.favouriteImageUrl = favouriteImageUrl;
    }

    public void setFavouriteId(int favouriteId) {
        this.favouriteId = favouriteId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFavouriteName() {
        return favouriteName;
    }

    public void setFavouriteName(String favouriteName) {
        this.favouriteName = favouriteName;
    }

    public String getFavouriteImageUrl() {
        return favouriteImageUrl;
    }

    public void setFavouriteImageUrl(String favouriteImageUrl) {
        this.favouriteImageUrl = favouriteImageUrl;
    }
}
