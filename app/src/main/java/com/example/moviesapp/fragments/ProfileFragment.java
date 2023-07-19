package com.example.moviesapp.fragments;

import android.app.Dialog;
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
import android.widget.TextView;

import com.example.moviesapp.R;
import com.example.moviesapp.activities.MainActivity;
import com.example.moviesapp.dao.DatabaseHelper;
import com.example.moviesapp.entities.UserDetails;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    ImageView profileImage;

    Button button;

    TextView email;

    TextView age;

    TextView gender;

    Button yesAlertBtn;

    Button noAlertBtn;
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

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(getContext());

        email = view.findViewById(R.id.email);

        age = view.findViewById(R.id.age);

        gender = view.findViewById(R.id.gender);

        SharedPreferences sharedPreferences;
          if(getActivity() != null){
              sharedPreferences = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
              int userId = sharedPreferences.getInt("username",0);

              UserDetails userDetails = databaseHelper.userDetailsDAO().findUserWithId(userId);

              String emailValue = "Email :    " + userDetails.getUsername();
              String ageValue = "Age :    " +userDetails.getAge();
              String genderValue = "Gender :    " + userDetails.getGender();
              email.setText(emailValue);

              age.setText(ageValue);

              gender.setText(genderValue);

              profileImage = view.findViewById(R.id.profileImage);

              Picasso.get().load("https://w.wallhaven.cc/full/k9/wallhaven-k9x8y1.png").into(profileImage);

              button = view.findViewById(R.id.logoutBtn);
          }

        button.setOnClickListener(v -> {
            SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences1.edit();
            editor.putBoolean("flag", false);
            editor.remove("username");
            editor.apply();

            Dialog dialog = new Dialog(getContext());

            dialog.setContentView(R.layout.signout_screen_dialog);

            dialog.setCancelable(false);

            yesAlertBtn = dialog.findViewById(R.id.alertYesBtn);
            noAlertBtn = dialog.findViewById(R.id.alertNoBtn);

            noAlertBtn.setOnClickListener(v1 -> dialog.dismiss());

            yesAlertBtn.setOnClickListener(v12 -> {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();
            });

            dialog.show();
        });

    }
}