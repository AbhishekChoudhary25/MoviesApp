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
import android.widget.EditText;

import com.example.moviesapp.R;
import com.example.moviesapp.activities.HomeScreen;
import com.example.moviesapp.listeners.TextListener;
import com.example.moviesapp.models.MovieDetailsPojo;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class SignInFragment extends Fragment {

    EditText emailEditText;

    EditText passwordEditText;

    Button signInBtn;

    TextInputLayout emailTextFieldLayout;

    TextInputLayout passwordTextFieldLayout;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailEditText = view.findViewById(R.id.emailTextField);

        passwordEditText = view.findViewById(R.id.passwordEditText);

        signInBtn = view.findViewById(R.id.signInBtn);

        emailTextFieldLayout = view.findViewById(R.id.emailTextFieldLayout);

        passwordTextFieldLayout = view.findViewById(R.id.passwordTextFieldLayout);


        emailEditText.addTextChangedListener(new TextListener(emailEditText,passwordEditText,emailTextFieldLayout,passwordTextFieldLayout ,getContext()));

        passwordEditText.addTextChangedListener(new TextListener(emailEditText,passwordEditText,emailTextFieldLayout,passwordTextFieldLayout ,getContext()));









        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailEditText.getText().toString().equals("")){
                    emailTextFieldLayout.setError("Email Field Cannot be null!");
                }
                else if(passwordEditText.getText().toString().equals("")){
                    passwordTextFieldLayout.setError("Password field cannot be null!");
                }
                else if(!passwordEditText.getText().toString().equals("Testing@1234")){
                    passwordTextFieldLayout.setError("Password incorrect!");
                }

                Boolean checkForEmailError =  emailTextFieldLayout.getError() == null;
                Boolean checkForPasswordError = passwordTextFieldLayout.getError() == null;

                if(checkForPasswordError&& checkForEmailError  && emailEditText.getText().toString().equals("abhishek.choudhary@techigai.io") && passwordEditText.getText().toString().equals("Testing@1234")){
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("flag", true);
                    editor.apply();


                    startActivity(new Intent(getActivity(), HomeScreen.class));
                }


            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

}