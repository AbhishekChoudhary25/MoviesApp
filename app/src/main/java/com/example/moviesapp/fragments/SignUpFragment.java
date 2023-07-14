package com.example.moviesapp.fragments;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviesapp.R;
import com.example.moviesapp.activities.MainActivity;
import com.example.moviesapp.dao.DatabaseHelper;
import com.example.moviesapp.entities.UserDetails;
import com.example.moviesapp.listeners.TextListener;
import com.example.moviesapp.listeners.UtilTextListener;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpFragment extends Fragment {

    EditText emailEditText;

    EditText passwordEditText;

    Button signUpBtn;

    TextInputLayout emailTextFieldLayout;

    TextInputLayout passwordTextFieldLayout;

    RadioGroup genderRadioGroup;

    Button yesAlertBtn;

    SeekBar seekBar;

    TextView ageText;

    EditText confirmPassword;

    TextView signInTv;

    TextInputLayout confirmTextFieldLayout;


    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(getContext());

        emailEditText = view.findViewById(R.id.emailTextField);

        passwordEditText = view.findViewById(R.id.passwordEditText);

        signUpBtn = view.findViewById(R.id.signUpBtn);

        emailTextFieldLayout = view.findViewById(R.id.emailTextFieldLayout);

        passwordTextFieldLayout = view.findViewById(R.id.passwordTextFieldLayout);

        genderRadioGroup = view.findViewById(R.id.genderRadioGroup);

        seekBar = view.findViewById(R.id.ageSeekbar);

        ageText= view.findViewById(R.id.ageText);

        confirmPassword = view.findViewById(R.id.confirmPassword);

        confirmTextFieldLayout = view.findViewById(R.id.confirmTextFieldLayout);

        signInTv = view.findViewById(R.id.signInTv);

//        emailEditText.addTextChangedListener(new TextListener(emailEditText,passwordEditText,emailTextFieldLayout,passwordTextFieldLayout ,getContext()));

//        passwordEditText.addTextChangedListener(new TextListener(emailEditText,passwordEditText,emailTextFieldLayout,passwordTextFieldLayout ,getContext()));

//        confirmPassword.addTextChangedListener(new UtilTextListener(confirmPassword, confirmTextFieldLayout, getContext()));

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
                if(!validPassword(passwordEditText.getText().toString())){
                    passwordTextFieldLayout.setError("Please enter a valid password");
                }
                else{
                    passwordTextFieldLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!confirmPassword.getText().toString().equals("")){
                    confirmTextFieldLayout.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        seekBar.setProgress(18);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageText.setText(progress + " years");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailEditText.getText().toString().equals("")){
                    emailTextFieldLayout.setError("Email Field Cannot be empty!");
                }
                if(passwordEditText.getText().toString().equals("")){
                    passwordTextFieldLayout.setError("Password field cannot be empty!");
                }
                if(confirmPassword.getText().toString().equals("")){
                    confirmTextFieldLayout.setError("Cannot be empty!");
                }

                if(emailTextFieldLayout.getError() == null && passwordTextFieldLayout.getError() == null && confirmPassword.getText() != null && passwordEditText.getText() != null && emailEditText.getText() != null&& passwordEditText.getText().toString().equals(confirmPassword.getText().toString())){
                    UserDetails user = databaseHelper.userDetailsDAO().findUserWithName(emailEditText.getText().toString());

                    if(user != null){
                        emailTextFieldLayout.setError("User already exists!");
                    }
                }


                String selectedGender = "";

                if(genderRadioGroup.getCheckedRadioButtonId() == R.id.radioMale){
                    selectedGender = "Male";
                }
                else{
                    selectedGender = "Female";
                }

                if(emailTextFieldLayout.getError() == null && passwordTextFieldLayout.getError() == null && passwordEditText.getText().toString().equals(confirmPassword.getText().toString())){
                    databaseHelper.userDetailsDAO().adduser(new UserDetails(emailEditText.getText().toString().toLowerCase(),passwordEditText.getText().toString(),selectedGender,seekBar.getProgress()));

                    Dialog dialog = new Dialog(getContext());

                    dialog.setContentView(R.layout.sucess_layout);

                    dialog.setCancelable(false);

                    yesAlertBtn = dialog.findViewById(R.id.alertYesBtn);

                    yesAlertBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SignInFragment signInFragment = new SignInFragment();
                            getParentFragmentManager().beginTransaction().replace(R.id.signin_singup_containerview,signInFragment).commit();
                            dialog.dismiss();

                        }
                    });

                    dialog.show();
                }
                else{
                    if(confirmTextFieldLayout.getError() == null && emailTextFieldLayout.getError() == null && passwordTextFieldLayout.getError() == null){
                        Toast.makeText(getContext(), "Password Did not match!", Toast.LENGTH_LONG).show();
                    }

                }



            }
        });

        signInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInFragment signInFragment = new SignInFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.signin_singup_containerview,signInFragment).commit();
            }
        });
    }
    private boolean validEmail(String email){
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();


    }

    private boolean validPassword(String password){
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }
}