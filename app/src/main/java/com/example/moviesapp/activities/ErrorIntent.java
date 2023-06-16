package com.example.moviesapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.moviesapp.R;

public class ErrorIntent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_intent);

        getWindow().setStatusBarColor(getResources().getColor(R.color.main_colour_theme));

    }
}