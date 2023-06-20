package com.example.moviesapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "userdetails")
public class UserDetails {
    @PrimaryKey(autoGenerate = true)
    private int userId;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @ColumnInfo(name = "gender")
    private String gender;

    public UserDetails(int userId, String username, String password, String gender) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.gender = gender;
    }

    @Ignore
    public UserDetails(String username, String password, String gender) {

        this.username = username;
        this.password = password;
        this.gender = gender;
    }
}