package com.example.moviesapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moviesapp.R;
import com.example.moviesapp.activities.HomeScreen;
import com.example.moviesapp.dao.DatabaseHelper;
import com.example.moviesapp.entities.UserDetails;
import com.example.moviesapp.util.UserAccessor;
import com.google.android.material.textfield.TextInputLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignInFragment extends Fragment {

    EditText emailEditText;

    EditText passwordEditText;

    Button signInBtn;

    TextInputLayout emailTextFieldLayout;

    TextInputLayout passwordTextFieldLayout;

    TextView signUpTv;



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

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!validEmail(emailEditText.getText().toString())){
                    emailTextFieldLayout.setError("Enter Valid Email Address");
                }
                else{
                    emailTextFieldLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!passwordEditText.getText().toString().equals("")){
                    passwordTextFieldLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signUpTv = view.findViewById(R.id.signUpTv);

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(getContext());

        signInBtn.setOnClickListener(v -> {
            if(emailEditText.getText().toString().equals("")){
                emailTextFieldLayout.setError("Email Field Cannot be empty!");
            }
            if(passwordEditText.getText().toString().equals("")){
                passwordTextFieldLayout.setError("Password field cannot be empty!");
            }


            if(!emailEditText.getText().toString().equals("") && emailTextFieldLayout.getError() == null && passwordTextFieldLayout.getError() == null){
                UserDetails userDetails = databaseHelper.userDetailsDAO().findUserWithName(emailEditText.getText().toString().toLowerCase());

                if(userDetails == null){
                    emailTextFieldLayout.setError("Email Does not exist, Please sign up!");
                }

                if(userDetails != null && !userDetails.getPassword().equals(passwordEditText.getText().toString())){
                    passwordTextFieldLayout.setError("Incorrect Password!");
                }
                boolean checkForEmailError =  emailTextFieldLayout.getError() == null;
                boolean checkForPasswordError = passwordTextFieldLayout.getError() == null;

                if(checkForPasswordError && checkForEmailError) {
                    if (userDetails!= null && emailEditText.getText().toString().equals(userDetails.getUsername()) && passwordEditText.getText().toString().equals(userDetails.getPassword())) {
                        SharedPreferences sharedPreferences;

                        if (getActivity() != null) {
                            sharedPreferences = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("flag", true);
                            editor.putInt("username", userDetails.getUserId());
                            editor.apply();

                            UserAccessor.id = userDetails.getUserId();

                            startActivity(new Intent(getActivity(), HomeScreen.class));
                        }


                    }
                }
            }
        });

        signUpTv.setOnClickListener(v -> {
            SignUpFragment signUpFragment = new SignUpFragment();
            getParentFragmentManager().beginTransaction().addToBackStack("something").replace(R.id.signin_singup_containerview,signUpFragment).commit();
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    private boolean validEmail(String email){
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();


    }

}