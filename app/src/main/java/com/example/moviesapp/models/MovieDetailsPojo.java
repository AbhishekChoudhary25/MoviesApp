package com.example.moviesapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieDetailsPojo implements Serializable {

    Boolean isFavourite;
    PrimaryImage primaryImage;

    OriginalTitleText originalTitleText;

    ReleaseYear releaseYear;

    public  MovieDetailsPojo(){
        isFavourite = false;
    }

    public PrimaryImage getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(PrimaryImage primaryImage) {
        this.primaryImage = primaryImage;
    }

    public OriginalTitleText getOriginalTitleText() {
        return originalTitleText;
    }

    public void setOriginalTitleText(OriginalTitleText originalTitleText) {
        this.originalTitleText = originalTitleText;
    }

    public ReleaseYear getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(ReleaseYear releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }
}
