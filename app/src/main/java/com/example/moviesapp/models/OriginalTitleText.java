package com.example.moviesapp.models;

import java.io.Serializable;

public class OriginalTitleText implements Serializable {
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
