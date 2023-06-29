package com.example.moviesapp.listeners;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class UtilTextListener implements TextWatcher {

    EditText confirmEditText;

    TextInputLayout confirmInputLayout;

    Context context;

    public UtilTextListener(EditText confirmEditText, TextInputLayout confirmInputLayout, Context context){
        this.confirmEditText = confirmEditText;
        this.confirmInputLayout = confirmInputLayout;
        this.context = context;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(!confirmEditText.getText().toString().equals("")){
            confirmInputLayout.setError("");
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
