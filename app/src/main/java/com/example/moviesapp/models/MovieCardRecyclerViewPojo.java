package com.example.moviesapp.models;

public class MovieCardRecyclerViewPojo {
    String title;
    int year;
    String genre;
    String director;
    String runtime;
    String language;

    public MovieCardRecyclerViewPojo(String title, int year, String genre, String director, String runtime, String language) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
