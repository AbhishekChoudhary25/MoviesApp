package com.example.moviesapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moviesapp.entities.UserDetails;



@Dao
public interface UserDetailsDAO {

    @Insert
    void adduser(UserDetails userDetails);

    @Query("SELECT * FROM userdetails WHERE username = :username")
     UserDetails findUserWithName(String username);

    @Query("SELECT * FROM userdetails WHERE userId = :id")
     UserDetails findUserWithId(int id);

}
