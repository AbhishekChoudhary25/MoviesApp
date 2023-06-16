package com.example.moviesapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.moviesapp.R;
import com.example.moviesapp.activities.HomeScreen;
import com.example.moviesapp.activities.MainActivity;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    ImageView profileImage;

    Button button;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileImage = view.findViewById(R.id.profileImage);

        Picasso.get().load("https://w.wallhaven.cc/full/k9/wallhaven-k9x8y1.png").into(profileImage);

        button = view.findViewById(R.id.logoutBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("flag", false);
                editor.apply();


                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

    }
}