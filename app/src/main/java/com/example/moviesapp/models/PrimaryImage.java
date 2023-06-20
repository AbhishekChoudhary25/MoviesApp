package com.example.moviesapp.models;

import java.io.Serializable;

public class PrimaryImage implements Serializable {
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
