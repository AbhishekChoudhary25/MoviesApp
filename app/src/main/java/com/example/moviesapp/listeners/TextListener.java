package com.example.moviesapp.listeners;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.TextKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextListener implements TextWatcher {

    EditText email;

    EditText password;

    Context context;

    TextInputLayout textInputLayout;

    TextInputLayout passwordTextFieldLayout;

    public TextListener(EditText email, EditText password,TextInputLayout textInputLayout,TextInputLayout passwordTextFieldLayout,Context context){
        this.email = email;
        this.password = password;
        this.context = context;
        this.passwordTextFieldLayout = passwordTextFieldLayout;
        this.textInputLayout = textInputLayout;
    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!validEmail(email.getText().toString())){
                textInputLayout.setError("Enter Valid Email Address");
            }
            else{
                textInputLayout.setError("");
            }

            if(!validPassword(password.getText().toString())){
                passwordTextFieldLayout.setError("Please enter a valid password");
            }
            else{
                passwordTextFieldLayout.setError("");
            }


    }

    @Override
    public void afterTextChanged(Editable s) {

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
