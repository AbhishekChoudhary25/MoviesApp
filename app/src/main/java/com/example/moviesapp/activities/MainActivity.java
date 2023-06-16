package com.example.moviesapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import com.example.moviesapp.R;
import com.example.moviesapp.fragments.FravouriteFragment;
import com.example.moviesapp.fragments.SignInFragment;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.example.moviesapp.util.NetworkReciever;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FragmentContainerView fragmentContainerView;

    String email;

    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getWindow().setStatusBarColor(getResources().getColor(R.color.main_colour_theme));

        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        Boolean isLoggedIn = sharedPreferences.getBoolean("flag",false);

        if(isLoggedIn){
            startActivity(new Intent(MainActivity.this, HomeScreen.class));
        }

        fragmentContainerView = findViewById(R.id.signin_singup_containerview);

        Fragment signInFragment = new SignInFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.signin_singup_containerview,signInFragment).commit();










    }
}